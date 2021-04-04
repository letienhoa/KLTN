package carbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import carbook.response.BaseResponse;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/momo")
public class MomoController {
 	@SuppressWarnings("unused")
	@RequestMapping(value ="/test", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> tesst(HttpServletRequest req) {
		BaseResponse response= new BaseResponse();
		 String signRequest ="";
		
		
		
			long amount = 10000;
	        String partnerRefId = String.valueOf(System.currentTimeMillis());
	        String requestId ="12"; //String.valueOf(System.currentTimeMillis());
	        String paymentCode = "MM515023896957011876";
	       String requestType= "captureMoMoWallet";
	       String  notifyUrl= "https://www.facebook.com/";
	       String returnUrl= "https://www.google.com/";
	       String orderId="12";
	       String orderInfo= "Hoateam";
	       String extraData = "";
	       String secretKey="lYU4imKDCkYmoO4HVuUQE1chLdhc9wiX";
	  
			/*
			 * try { String requestRawData = new StringBuilder()
			 * .append(Parameter.PARTNER_CODE).append("=").append("MOMOO6A120210122").append
			 * ("&")
			 * .append(Parameter.ACCESS_KEY).append("=").append("9IHou4YiFiOD5NfO").append(
			 * "&") .append(Parameter.REQUEST_ID).append("=").append(requestId).append("&")
			 * .append(Parameter.AMOUNT).append("=").append(amount).append("&")
			 * .append(Parameter.ORDER_ID).append("=").append(orderId).append("&")
			 * .append(Parameter.ORDER_INFO).append("=").append(orderInfo).append("&")
			 * .append(Parameter.RETURN_URL).append("=").append(returnUrl).append("&")
			 * .append(Parameter.NOTIFY_URL).append("=").append(notifyUrl).append("&")
			 * .append(Parameter.EXTRA_DATA).append("=").append(extraData) .toString();
			 * 
			 * signRequest = Encoder.signHmacSHA256(requestRawData, secretKey);
			 * 
			 * //signRequest= HmacSHA256(requestRawData,secretKey); } catch (Exception e) {
			 * System.out.print("Lỗi"); }
			 */
		
			response.setData(signRequest);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
}
