
package carbook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*  import com.paypal.api.payments.Links; 
	  import com.paypal.api.payments.Payment;
	  import com.paypal.base.rest.PayPalRESTException;
	  import com.paypal.base.rest.APIContext;
	  import com.paypal.api.payments.Refund;
	  import carbook.config.PaypalPaymentIntent;
	  import carbook.config.PaypalPaymentMethod; 
	  import carbook.config.Utils*/
//import carbook.service.PaypalServices;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Refund;
import com.paypal.api.payments.Sale;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import carbook.dao.VeDao;
import carbook.entity.Ve;

import carbook.entity.VeForCustomerByCodeDataModelFinal;
import carbook.response.BaseResponse;
import carbook.service.EmailService;
import carbook.service.EmailService2;
import carbook.service.PaypalServices;
import carbook.service.UtilsService;

import com.paypal.base.rest.OAuthTokenCredential;

@CrossOrigin(origins = "http://localhost:4200")

@RestController

@RequestMapping("/api/paypal")
public class Paypalcontroller {

	@SuppressWarnings("unused")
	@Autowired
	private PaypalServices paypalService;

	@Autowired
	private APIContext apiContext1;

	@Autowired
	private VeDao veDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private EmailService2 emailService2;

	@Value("${paypal.client.app}")
	private String clientId;

	@Value("${paypal.client.secret}")
	private String clientSecret;

	@Value("${paypal.mode}")
	private String mode;

	public Map<String, String> paypalSdkConfig() {
		Map<String, String> sdkConfig = new HashMap<>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}

	public OAuthTokenCredential authTokenCredential() {
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}

	public APIContext apiContext() throws PayPalRESTException {
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}

	@RequestMapping(value = "/pay/get-token", method = RequestMethod.GET)
	public APIContext getTokenPayPal() {

		return apiContext1;
		// return null;
	}

	@RequestMapping(value = "/pay/{id}/refund", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> refund(@PathVariable(name = "id") int id) throws PayPalRESTException {
		BaseResponse response = new BaseResponse();

		Refund refund = new Refund();
		APIContext apiContext = apiContext();
		Ve ve = veDao.findOne(id);
		Integer h = veDao.spGetCountGiuong(id);
		if (ve != null && ve.getTrangThai() == 0) {
			VeForCustomerByCodeDataModelFinal ve2 = veDao.spGetVeForCustomerByCode(ve.getCode());
			Double tienDoi = (double) ve.getGiaVe() / h;
			Double moneyRefund = (tienDoi / 22000) - (tienDoi / 22000) * 0.1;
			moneyRefund = Math.round(moneyRefund * 100.0) / 100.0;
			Double moneyRefundmail = tienDoi - tienDoi * 0.1;
			String moneyRefundmailString = String.valueOf(moneyRefundmail);
			String moneyRefundString = String.valueOf(moneyRefund);
			Amount amount = new Amount();
			amount.setTotal(moneyRefundString);
			amount.setCurrency("USD");

			// amount.
			refund.setAmount(amount);

			Sale sale = new Sale();
			sale.setId(ve.getPaypalId().toString());

			try {
				// String apiContext= null;
				// Refund sale
				sale.refund(apiContext, refund);

			} catch (PayPalRESTException e) {
				System.err.println(e.getDetails());
				response.setMessageError("Fail rồi bạn ơi");
				return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
			}
			ve.setTrangThai(3);
			veDao.update(ve);
			veDao.spDeleteGiuong(ve.getId());
			emailService2.sendMailRefundTicket(ve.getEmail(), ve2.getTuyenXe(), ve2.getGioChay().toString(),
					UtilsService.getDateFormatVN(ve.getDate()), moneyRefundmailString);
			emailService.sendEmail(ve.getEmail(), "HỦY VÉ",
					"Nhà xe đã hủy tuyến " + ve2.getTuyenXe() + "-- giờ chạy: " + ve2.getGioChay() + "-- ngày chạy"
							+ ve.getDate()
							+ "-- hồi lại tiền cho quý khách và đã lấy 10% giá trị, số tiền được hồi lại là"
							+ moneyRefundmailString + " vnd");
			response.setMessageError("Thành công rồi nè");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		} else {
			response.setMessageError("Không tìm thấy vé =((");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/cancel-tuyen-xe", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> cancelTuyenXe(
			@RequestParam(name = "id_tuyen_xe", required = false) Integer idTuyenXe,
			@RequestParam(name = "gio", required = false, defaultValue = "7") Integer gio,
			@RequestParam(name = "ngay", required = false, defaultValue = "01/01/2020") String ngay)
			throws PayPalRESTException {
		BaseResponse response = new BaseResponse();
		List<Ve> list = veDao.getVeToCancel(idTuyenXe, gio, ngay, 1);
		if (list != null) {
			for (Ve x : list) {
				Refund refund = new Refund();
				APIContext apiContext = apiContext();
				Integer h = veDao.spGetCountGiuong(x.getId());
				VeForCustomerByCodeDataModelFinal ve2 = veDao.spGetVeForCustomerByCode(x.getCode());
				Double tienDoi = (double) x.getGiaVe() / h;
				Double moneyRefund = (tienDoi / 22000) - (tienDoi / 22000) * 0.1 + (tienDoi / 22000) * 0.1;
				moneyRefund = Math.round(moneyRefund * 100.0) / 100.0;
				Double moneyRefundmail = tienDoi - tienDoi * 0.1 + (tienDoi / 22000) * 0.1;
				String moneyRefundmailString = String.valueOf(moneyRefundmail);
				String moneyRefundString = String.valueOf(moneyRefund);
				Amount amount = new Amount();
				amount.setTotal(moneyRefundString);
				amount.setCurrency("USD");

				refund.setAmount(amount);
				Sale sale = new Sale();
				sale.setId(x.getPaypalId().toString());

				try {
					sale.refund(apiContext, refund);
				} catch (PayPalRESTException e) {
					System.err.println(e.getDetails());

				}
				x.setTrangThai(3);
				veDao.update(x);
				veDao.spDeleteGiuong(x.getId());
				emailService2.sendMailCancelTuyenXe(x.getEmail(), ve2.getTuyenXe(), ve2.getGioChay().toString(),
						UtilsService.getDateFormatVN(x.getDate()), moneyRefundmailString);
				emailService.sendEmail(x.getEmail(), "HỦY VÉ",
						"Vì một số lý do kỹ thuật,nhà xe đã hủy tuyến " + ve2.getTuyenXe() + "-- giờ chạy: "
								+ ve2.getGioChay() + "-- ngày chạy" + x.getDate()
								+ "-- hồi lại tiền cho quý khách, số tiền được hồi lại là" + moneyRefundmailString
								+ " vnd" + "  . Nhà xe chân thành xin lỗi quý khách");

			}
		}

		response.setMessageError("Thành công rồi nè");
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/pay/get-city", method = RequestMethod.GET)
	public String getCity() {
		final String uri = "https://thongtindoanhnghiep.co/api/city";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		return result;
	}

	@RequestMapping(value = "/pay/get-count", method = RequestMethod.GET)
	public Integer getcount() {
		Integer h = veDao.spGetCountGiuong(101);

		return h;
	}

	/*
	 * @RequestMapping(value ="/pay/cancel", method = RequestMethod.GET) public
	 * String cancelPay(){ Refund refund = new Refund();
	 * 
	 * Amount amount = new Amount(); amount.setTotal("10.31");
	 * amount.setCurrency("USD");
	 * 
	 * //amount. refund.setAmount(amount);
	 * 
	 * Sale sale = new Sale(); sale.setId("9XH58575YG898820G");
	 * 
	 * try { // String apiContext= null; // Refund sale sale.refund(apiContext,
	 * refund); } catch (PayPalRESTException e) {
	 * System.err.println(e.getDetails()); } return "cancel"; }
	 */

	/*
	 * public static final String URL_PAYPAL_SUCCESS =
	 * "/api/paypal/pay/success?email="; public static final String
	 * URL_PAYPAL_CANCEL = "/api/paypal/pay/cancel";
	 * 
	 * @Autowired private PaypalServices paypalService;
	 * 
	 * 
	 * @RequestMapping(value ="/pay", method = RequestMethod.POST) public String
	 * pay(HttpServletRequest request,@RequestParam("price") double price,
	 * 
	 * @RequestParam("email") String email){ String cancelUrl =
	 * Utils.getBaseURL(request) + URL_PAYPAL_CANCEL; String successUrl =
	 * Utils.getBaseURL(request) + URL_PAYPAL_SUCCESS+email; try { Payment payment =
	 * paypalService.createPayment( price, "USD", PaypalPaymentMethod.paypal,
	 * PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
	 * for(Links links : payment.getLinks()){
	 * if(links.getRel().equals("approval_url")){ return "redirect:/" +
	 * links.getHref(); } } } catch (PayPalRESTException e) {
	 * 
	 * } return "redirect:/"; }
	 * 
	 * @RequestMapping(value ="/pay/cancel", method = RequestMethod.GET) public
	 * String cancelPay(){ return "cancel"; }
	 * 
	 * @RequestMapping(value ="/pay/success", method = RequestMethod.GET) public
	 * String successPay(
	 * 
	 * @RequestParam("paymentId") String paymentId,
	 * 
	 * @RequestParam("PayerID") String payerId,
	 * 
	 * @RequestParam("email") String email){ try { Payment payment =
	 * paypalService.executePayment(paymentId, payerId);
	 * if(payment.getState().equals("approved")){ System.out.print(email); return
	 * "success"; } } catch (PayPalRESTException e) {
	 * 
	 * } return "redirect:/"; }
	 */

}
