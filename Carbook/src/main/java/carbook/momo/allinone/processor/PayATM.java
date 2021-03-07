package carbook.momo.allinone.processor;

import carbook.momo.allinone.models.PayATMRequest;
import carbook.momo.allinone.models.PayATMResponse;
import carbook.momo.shared.constants.Parameter;
import carbook.momo.shared.constants.RequestType;
import carbook.momo.shared.exception.MoMoException;
import carbook.momo.shared.sharedmodels.AbstractProcess;
import carbook.momo.shared.sharedmodels.Environment;
import carbook.momo.shared.sharedmodels.HttpResponse;
import carbook.momo.shared.sharedmodels.PartnerInfo;
import carbook.momo.shared.utils.Encoder;
import carbook.momo.shared.utils.LogUtils;

/**
 * @author hainguyen
 * Documention: https://developers.momo.vn
 */
public class PayATM extends AbstractProcess<PayATMRequest, PayATMResponse> {

    public PayATM(Environment environment) {
        super(environment);
    }

    public static PayATMResponse process(Environment env, String requestId, String orderId, String bankCode, String amount, String orderInfo, String returnUrl, String notifyUrl, String extra) throws Exception {
        try {
            PayATM atmProcess = new PayATM(env);

            PayATMRequest payATMRequest = atmProcess.createPayWithATMRequest(requestId, orderId, bankCode, amount, orderInfo, returnUrl, notifyUrl, extra, env.getPartnerInfo());
            PayATMResponse payATMResponse = atmProcess.execute(payATMRequest);
            return payATMResponse;

        } catch (Exception e) {
            LogUtils.error("PayATMProcess"+ e);
        }
        return null;
    }

    @Override
    public PayATMResponse execute(PayATMRequest request) throws MoMoException {
        try {
            String payload = getGson().toJson(request, PayATMRequest.class);

            HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint(), payload);

            if (response.getStatus() != 200) {
                throw new MoMoException("[PayATMResponse] [" + request.getOrderId() + "] -> Error API");
            }

            PayATMResponse payATMResponse = getGson().fromJson(response.getData(), PayATMResponse.class);
//            errorMoMoProcess(payATMResponse.getErrorCode());

            String rawData =
                    Parameter.PARTNER_CODE + "=" + payATMResponse.getPartnerCode() +
                            "&" + Parameter.ACCESS_KEY + "=" + payATMResponse.getAccessKey() +
                            "&" + Parameter.REQUEST_ID + "=" + payATMResponse.getRequestId() +
                            "&" + Parameter.PAY_URL + "=" + payATMResponse.getPayUrl() +
                            "&" + Parameter.ERROR_CODE + "=" + payATMResponse.getErrorCode() +
                            "&" + Parameter.ORDER_ID + "=" + payATMResponse.getOrderId() +
                            "&" + Parameter.MESSAGE + "=" + payATMResponse.getMessage() +
                            "&" + Parameter.LOCAL_MESSAGE + "=" + payATMResponse.getLocalMessage() +
                            "&" + Parameter.REQUEST_TYPE + "=" + RequestType.PAY_WITH_ATM;

            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());

            LogUtils.info("[PayATMResponse] rawData: "+ rawData + ", [Signature] -> " + signature + ", [MoMoSignature] -> " + payATMResponse.getSignature());

            if (signature.equals(payATMResponse.getSignature()) || payATMResponse.getSignature() == null) {
                return payATMResponse;
            } else {
                throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
            }

        } catch (Exception e) {
            LogUtils.error("[PayATMResponse] "+ e);
        }
        return null;
    }

    public PayATMRequest createPayWithATMRequest(String requestId, String orderId, String bankCode, String amount, String orderInfo, String returnUrl,
                                                 String notifyUrl, String extra, PartnerInfo partnerInfo) {

        try {
            String dataCryption
                    = Parameter.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() + "&"
                    + Parameter.ACCESS_KEY + "=" + partnerInfo.getAccessKey() + "&"
                    + Parameter.REQUEST_ID + "=" + requestId + "&"
                    + Parameter.BANK_CODE + "=" + bankCode + "&"
                    + Parameter.AMOUNT + "=" + amount + "&"
                    + Parameter.ORDER_ID + "=" + orderId + "&"
                    + Parameter.ORDER_INFO + "=" + orderInfo + "&"
                    + Parameter.RETURN_URL + "=" + returnUrl + "&"
                    + Parameter.NOTIFY_URL + "=" + notifyUrl + "&"
                    + Parameter.EXTRA_DATA + "=" + extra + "&"
                    + Parameter.REQUEST_TYPE + "=" + RequestType.PAY_WITH_ATM;
            String signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());

            LogUtils.debug("[PayATMRequest] rawData: " + dataCryption + ", [Signature] -> " + signature);

            PayATMRequest payATMRequest =   new PayATMRequest(
                    partnerInfo.getPartnerCode(),
                    partnerInfo.getAccessKey(),
                    amount,
                    requestId,
                    orderId,
                    returnUrl,
                    notifyUrl,
                    orderInfo,
                    bankCode,
                    extra,
                    RequestType.PAY_WITH_ATM,
                    signature);
            return payATMRequest;
        } catch (Exception e) {
            LogUtils.error("[PayATMRequest] "+ e);
            throw new IllegalArgumentException("Invalid params ATM Request");
        }
    }

}
