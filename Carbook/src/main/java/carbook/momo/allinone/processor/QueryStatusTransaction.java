package carbook.momo.allinone.processor;

import carbook.momo.allinone.models.QueryStatusTransactionRequest;
import carbook.momo.allinone.models.QueryStatusTransactionResponse;
import carbook.momo.shared.constants.Parameter;
import carbook.momo.shared.constants.RequestType;
import carbook.momo.shared.exception.MoMoException;
import carbook.momo.shared.sharedmodels.AbstractProcess;
import carbook.momo.shared.sharedmodels.Environment;
import carbook.momo.shared.sharedmodels.HttpResponse;
import carbook.momo.shared.utils.Encoder;
import carbook.momo.shared.utils.LogUtils;

/**
 * @author hainguyen
 * Documention: https://developers.momo.vn
 */
public class QueryStatusTransaction extends AbstractProcess<QueryStatusTransactionRequest, QueryStatusTransactionResponse> {

    public QueryStatusTransaction(Environment environment) {
        super(environment);
    }

    /**
     * Check Query Transaction Status on Payment Gateway
     *
     * @param orderId   MoMo's order ID
     * @param requestId request ID
     **/
    public static QueryStatusTransactionResponse process(Environment env, String orderId, String requestId) {
        try {
            QueryStatusTransaction queryStatusTransaction = new QueryStatusTransaction(env);

            QueryStatusTransactionRequest queryStatusRequest = queryStatusTransaction.createQueryRequest(orderId, requestId);
            QueryStatusTransactionResponse queryStatusResponse = queryStatusTransaction.execute(queryStatusRequest);

            return queryStatusResponse;
        } catch (Exception e) {
            LogUtils.error("[QueryStatusProcess] "+ e);
        }
        return null;
    }

    @Override
    public QueryStatusTransactionResponse execute(QueryStatusTransactionRequest request) throws MoMoException {

        try {
            String payload = getGson().toJson(request, QueryStatusTransactionRequest.class);

            HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint(), payload);

            if (response.getStatus() != 200) {
                throw new MoMoException("[QueryStatusTransactionResponse] [" + request.getOrderId() + "] -> Error API");
            }

            QueryStatusTransactionResponse queryStatusResponse = getGson().fromJson(response.getData(), QueryStatusTransactionResponse.class);

//            errorMoMoProcess(queryStatusResponse.getErrorCode());

            String rawData = Parameter.PARTNER_CODE + "=" + queryStatusResponse.getPartnerCode() +
                    "&" + Parameter.ACCESS_KEY + "=" + queryStatusResponse.getAccessKey() +
                    "&" + Parameter.REQUEST_ID + "=" + queryStatusResponse.getRequestId() +
                    "&" + Parameter.ORDER_ID + "=" + queryStatusResponse.getOrderId() +
                    "&" + Parameter.ERROR_CODE + "=" + queryStatusResponse.getErrorCode() +
                    "&" + Parameter.TRANS_ID + "=" + queryStatusResponse.getTransId() +
                    "&" + Parameter.AMOUNT + "=" + queryStatusResponse.getAmount() +
                    "&" + Parameter.MESSAGE + "=" + queryStatusResponse.getMessage() +
                    "&" + Parameter.LOCAL_MESSAGE + "=" + queryStatusResponse.getLocalMessage() +
                    "&" + Parameter.REQUEST_TYPE + "=" + RequestType.TRANSACTION_STATUS +
                    "&" + Parameter.PAY_TYPE + "=" + queryStatusResponse.getPayType() +
                    "&" + Parameter.EXTRA_DATA + "=" + queryStatusResponse.getExtraData();

            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            LogUtils.info("[QueryStatusTransactionResponse] rawData: " + rawData + ", [Signature] -> " + signature + ", [MoMoSignature] -> " + queryStatusResponse.getSignature());

            if (signature.equals(queryStatusResponse.getSignature())) {
                return queryStatusResponse;
            } else {
                throw new MoMoException("Wrong signature from MoMo side - please contact with us");
            }

        } catch (Exception e) {
            LogUtils.error("[QueryStatusTransactionResponse] "+ e);
        }
        return null;

    }

    public QueryStatusTransactionRequest createQueryRequest(String orderId, String requestId) {
        String signature = "";
        try {
            String rawData =
                    Parameter.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                            "&" + Parameter.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                            "&" + Parameter.REQUEST_ID + "=" + requestId +
                            "&" + Parameter.ORDER_ID + "=" + orderId +
                            "&" + Parameter.REQUEST_TYPE + "=" + RequestType.TRANSACTION_STATUS;
            signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            LogUtils.debug("[QueryStatusTransactionRequest] rawData: " + rawData + ", [Signature] -> " + signature);

        } catch (Exception e) {
            LogUtils.error("[QueryStatusTransactionRequest] "+ e);
        }

        QueryStatusTransactionRequest request = new QueryStatusTransactionRequest(partnerInfo.getPartnerCode(),orderId,partnerInfo.getAccessKey(),signature,requestId,RequestType.TRANSACTION_STATUS);

        return request;
    }

}
