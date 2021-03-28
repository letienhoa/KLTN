package carbook.vnpay;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.base.rest.APIContext;

import carbook.response.BaseResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payVn")
public class VnPayConfig {
	
	public static String linkURL ="";
	Double vnp_Version=2.0;
	String vnp_Command="pay";
	String vnp_TmnCode="BZKHFB2U";
	Double vnp_Amount=100000000.0;
	String vnp_BankCode="NCB";
	String vnp_CreateDate;
	String vnp_CurrCode="VND";
	String vnp_IpAddr="10.86.152.50";
	String vnp_Locale="vn";
	String vnp_Merchant="DEMO";
	String vnp_OrderInfo="HiHiHiHi";
	String vnp_OrderType="topup";
	String vnp_ReturnUrl="http://localhost:8082/api/payVn/submit";
	String vnp_TxnRef="HoaLeTien";
	String vnp_SecureHashType="SHA256";
	String vnp_SecureHash;
	String vnp_SupChecksum="DVDGIHLTSKCAWWBRXVRKMJXTFDJHOQCP";
	
	
	@RequestMapping(value ="/submit", method = RequestMethod.GET)
	public String post() throws UnsupportedEncodingException {

		String button="<a href=\r\n"+linkURL+">\r\n"+
				"<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n" + 
					"  border-radius: 4px;\r\n" + 
					"  background-color: #f4511e;\r\n" + 
					"  border: none;\r\n" + 
					"  color: #FFFFFF;\r\n" + 
					"  text-align: center;\r\n" + 
					"  font-size: 22px;\r\n" + 
					"  padding: 20px;\r\n" + 
					"  width: 700px;\r\n" + 
					"  transition: all 0.5s;\r\n" + 
					"  cursor: pointer;\r\n" + 
					"  margin-left: 350px;\">"+"OK"
				+ "</button>"+
				"</a>";
		String responses ="<div><h1 style=\"margin-left:500px;	\">THANH TOÁN THÀNH CÔNG</h1></div>"+button;
		return responses;
		
			
	}
	
	@RequestMapping(value ="/get-code", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse>  createStringQuery(
			@RequestParam(name = "link", required = false, defaultValue = "f") String link
			) throws UnsupportedEncodingException {
		BaseResponse response= new BaseResponse();
		VnPayConfig.linkURL=link;
		System.out.print(linkURL);
		String vnp_Version="2";
		String vnp_Command="pay";
		String vnp_TmnCode="BZKHFB2U";
		String vnp_Amount="10000000";
		String vnp_BankCode="NCB";
		String vnp_CreateDate;
		String vnp_CurrCode="VND";
		String vnp_IpAddr="192.168.3.7";
		String vnp_Locale="vn";
		String vnp_Merchant="DEMO";
		String vnp_OrderInfo="HiHiHi";
		String vnp_OrderType="topussp";
		String vnp_ReturnUrl="http://localhost:8082/api/payVn/submit";
		String vnp_TxnRef="HoaLeTiesss2";
		String vnp_SecureHashType="SHA256";
		String vnp_SupChecksumt="DVDGIHLTSKCAWWBRXVRKMJXTFDJHOQCP";
		String a ="";
		//
		Date dt = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    String dateString = formatter.format(dt);
	    vnp_CreateDate = dateString;
        String vnp_TransDate = vnp_CreateDate;
        vnp_TxnRef=vnp_CreateDate;
		
		
		Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount",vnp_Amount);
        vnp_Params.put("vnp_CurrCode",vnp_CurrCode);
        vnp_Params.put("vnp_BankCode", vnp_BankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", vnp_OrderType);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Sha256(vnp_SupChecksumt + hashData.toString());
        queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
        response.setData(queryUrl);
        return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	public static String Sha256(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(message.getBytes("UTF-8"));

            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (UnsupportedEncodingException ex) {
            digest = "";
            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
            // null, ex);
        } catch (NoSuchAlgorithmException ex) {
            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
            // null, ex);
            digest = "";
        }
        return digest;
    }
	
	public static String md5(String message) {
	        String digest = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] hash = md.digest(message.getBytes("UTF-8"));
	            // converting byte array to Hexadecimal String
	            StringBuilder sb = new StringBuilder(2 * hash.length);
	            for (byte b : hash) {
	                sb.append(String.format("%02x", b & 0xff));
	            }
	            digest = sb.toString();
	        } catch (UnsupportedEncodingException ex) {
	            digest = "";
	            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
	            // null, ex);
	        } catch (NoSuchAlgorithmException ex) {
	            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
	            // null, ex);
	            digest = "";
	        }
	        return digest;
	}
	
}
