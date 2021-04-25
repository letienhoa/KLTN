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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.base.rest.APIContext;

import carbook.dao.VeDao;
import carbook.request.SlotRequest;
import carbook.request.VeHoiKhuRequest;
import carbook.request.VeRequest;
import carbook.response.BaseResponse;
import carbook.service.EmailService2;
import carbook.service.GenerateCode;
import carbook.service.UtilsService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payVn")
public class VnPayConfig {

	public static Map<String, List<SlotRequest>> listSlotVnPay = new HashMap<String, List<SlotRequest>>();
	@Autowired
	private VeDao veDao;
	@Autowired
	private EmailService2 emailser;

	public static String linkURL = "";
	Double vnp_Version = 2.0;
	String vnp_Command = "pay";
	String vnp_TmnCode = "BZKHFB2U";
	Double vnp_Amount = 100000000.0;
	String vnp_BankCode = "NCB";
	String vnp_CreateDate;
	String vnp_CurrCode = "VND";
	String vnp_IpAddr = "10.86.152.50";
	String vnp_Locale = "vn";
	String vnp_Merchant = "DEMO";
	String vnp_OrderInfo = "HiHiHiHi";
	String vnp_OrderType = "topup";
	String vnp_ReturnUrl = "http://localhost:8082/api/payVn/submit";
	String vnp_TxnRef = "HoaLeTien";
	String vnp_SecureHashType = "SHA256";
	String vnp_SecureHash;
	String vnp_SupChecksum = "DVDGIHLTSKCAWWBRXVRKMJXTFDJHOQCP";

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public String post(@RequestParam(name = "vnp_Amount", required = false, defaultValue = "null") String vnp_Amount,
			@RequestParam(name = "vnp_BankCode", required = false, defaultValue = "null") String vnp_BankCode,
			@RequestParam(name = "vnp_BankTranNo", required = false, defaultValue = "null") String vnp_BankTranNo,
			@RequestParam(name = "vnp_CardType", required = false, defaultValue = "null") String vnp_CardType,
			@RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnp_OrderInfo,
			@RequestParam(name = "vnp_PayDate", required = false, defaultValue = "null") String vnp_PayDate,
			@RequestParam(name = "vnp_ResponseCode", required = false, defaultValue = "null") String vnp_ResponseCode,
			@RequestParam(name = "vnp_TmnCode", required = false, defaultValue = "null") String vnp_TmnCode,
			@RequestParam(name = "vnp_TransactionNo", required = false, defaultValue = "null") String vnp_TransactionNo,
			@RequestParam(name = "vnp_TxnRef", required = false, defaultValue = "null") String vnp_TxnRef,
			@RequestParam(name = "vnp_SecureHashType", required = false, defaultValue = "null") String vnp_SecureHashType,
			@RequestParam(name = "vnp_SecureHash", required = false, defaultValue = "null") String vnp_SecureHash,
			@RequestParam(name = "gio_chay", required = false, defaultValue = "null") Integer gio_chay,
			@RequestParam(name = "gio_ket_thuc", required = false, defaultValue = "null") String gio_ket_thuc,
			@RequestParam(name = "id_tuyen_xe", required = false, defaultValue = "null") Integer id_tuyen_xe,
			@RequestParam(name = "sdt", required = false, defaultValue = "null") String sdt,
			@RequestParam(name = "email", required = false, defaultValue = "null") String email,
			@RequestParam(name = "date", required = false, defaultValue = "null") String date,
			@RequestParam(name = "diem_xuong", required = false, defaultValue = "null") String diem_xuong,
			@RequestParam(name = "gio_chay2", required = false, defaultValue = "null") Integer gio_chay2,
			@RequestParam(name = "gio_ket_thuc2", required = false, defaultValue = "null") String gio_ket_thuc2,
			@RequestParam(name = "id_tuyen_xe2", required = false, defaultValue = "null") Integer id_tuyen_xe2,
			@RequestParam(name = "date2", required = false, defaultValue = "null") String date2,
			@RequestParam(name = "code_slot1", required = false, defaultValue = "null") String code_slot1,
			@RequestParam(name = "code_slot2", required = false, defaultValue = "null") String code_slot2)
			throws UnsupportedEncodingException {

		if (code_slot2 == "null") {
			List<SlotRequest> valueOfCodeSlot = listSlotVnPay.get(code_slot1);
			String code = GenerateCode.generateStringToEmail(new String(""));
			String slots = UtilsService.convertListObjectToJsonArrayt(valueOfCodeSlot);
			List<String> slotMail = new ArrayList<String>();
			for (int i = 0; i < valueOfCodeSlot.size(); i++) {
				String g = "";
				if (valueOfCodeSlot.get(i).getSoGhe() <= 22) {
					if (valueOfCodeSlot.get(i).getSoGhe() < 10) {
						g = "A0" + String.valueOf(valueOfCodeSlot.get(i).getSoGhe());
					} else {
						g = "A" + String.valueOf(valueOfCodeSlot.get(i).getSoGhe());
					}
				} else {
					if (valueOfCodeSlot.get(i).getSoGhe() < 32) {
						g = "B0" + String.valueOf(valueOfCodeSlot.get(i).getSoGhe() - 22);
					} else {
						g = "B" + String.valueOf(valueOfCodeSlot.get(i).getSoGhe() - 22);
					}
				}
				slotMail.add(g);
			}
			String slotMails = slotMail.stream().collect(Collectors.joining(String.valueOf(",")));
			String tenTuyen = veDao.spGetNameTuyenXe(id_tuyen_xe);
			String ngay = UtilsService.changeDateToString(UtilsService.changeStringToDate(date));
			emailser.sendMail(email, code, tenTuyen, gio_chay, slotMails, 100000.0, ngay);
		} else {

		}

		String button = "<a href=\r\n" + linkURL + ">\r\n"
				+ "<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n"
				+ "  border-radius: 4px;\r\n" + "  background-color: #f4511e;\r\n" + "  border: none;\r\n"
				+ "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n" + "  font-size: 22px;\r\n"
				+ "  padding: 20px;\r\n" + "  width: 700px;\r\n" + "  transition: all 0.5s;\r\n"
				+ "  cursor: pointer;\r\n" + "  margin-left: 350px;\">" + "OK" + "</button>" + "</a>";
		String responses = "<div><h1 style=\"margin-left:500px;	\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
		return responses;
	}

	@RequestMapping(value = "/get-code", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> createStringQuery(
			@RequestParam(name = "link", required = false, defaultValue = "f") String link,
			@RequestParam(name = "vnp_Amount", required = false, defaultValue = "f") String vnp_Amount,
			@RequestParam(name = "vnp_IpAddr", required = false, defaultValue = "f") String vnp_IpAddr,
			@RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "f") String vnp_OrderInfo,
			@RequestParam(name = "vnp_OrderType", required = false, defaultValue = "f") String vnp_OrderType,
			@RequestBody VeHoiKhuRequest wrapper

	) throws UnsupportedEncodingException {
		BaseResponse response = new BaseResponse();
		String tenTuyen = veDao.spGetNameTuyenXe(wrapper.getIdTuyenXe());
		String slots = UtilsService.convertListObjectToJsonArrayt(wrapper.getSlot());
		VnPayConfig.linkURL = link;
		System.out.print(linkURL);
		String vnp_Version = "2";
		String vnp_Command = "pay";
		String vnp_TmnCode = "BZKHFB2U";
		String vnp_BankCode = "NCB";
		String vnp_CreateDate;
		String vnp_CurrCode = "VND";
		String vnp_Locale = "vn";
		String vnp_TxnRef = "HoaLeTiesss3";
		String vnp_SupChecksumt = "DVDGIHLTSKCAWWBRXVRKMJXTFDJHOQCP";
		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(dt);
		vnp_CreateDate = dateString;
		listSlotVnPay.put(vnp_CreateDate, wrapper.getSlot());
		String codeSlot1 = dateString;
		String codeSlot2 = "null";
		String vnp_ReturnUrl = "http://localhost:8082/api/payVn/submit?" + "gio_chay=" + wrapper.getGioChay().toString()
				+ "&gio_ket_thuc=" + wrapper.getGioKetThuc().toString() + "&sdt=" + wrapper.getSdt().toString()
				+ "&email=" + wrapper.getEmail().toString() + "&date=" + wrapper.getDate().toString() + "&id_tuyen_xe"
				+ wrapper.getIdTuyenXe() + "&code_slot1" + codeSlot1 + "&code_slot2" + codeSlot2;
		// Nếu là vé 2 chiều
		if (wrapper.getGioChay2() != null) {
			codeSlot2 = formatter.format(new Date());
			listSlotVnPay.put(codeSlot2, wrapper.getSlot2());

			vnp_ReturnUrl = "http://localhost:8082/api/payVn/submit?" + "gio_chay=" + wrapper.getGioChay().toString()
					+ "&gio_ket_thuc=" + wrapper.getGioKetThuc().toString() + "&sdt=" + wrapper.getSdt().toString()
					+ "&email=" + wrapper.getEmail().toString() + "&date=" + wrapper.getDate().toString()
					+ "&id_tuyen_xe" + wrapper.getIdTuyenXe() + "&code_slot1" + codeSlot1 + "&code_slot2" + codeSlot2
					+ "gio_chay2=" + wrapper.getGioChay2().toString() + "&gio_ket_thuc2="
					+ wrapper.getGioKetThuc2().toString() + "&id_tuyen_xe2" + wrapper.getIdTuyenXe2() + "&date2="
					+ wrapper.getDate2().toString();
		}
		vnp_TxnRef = vnp_CreateDate;
		
		vnp_ReturnUrl = "https://www.google.com/";
		// Tạo mã map links
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", vnp_Amount + "000");
		vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
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
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(fieldValue);
				// Build query
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
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> refundVnpay(


	) throws UnsupportedEncodingException {
		BaseResponse response = new BaseResponse();
		
		
		String vnp_Command = "pay";
		String vnp_TmnCode = "BZKHFB2U";
		String vnp_SupChecksumt = "DVDGIHLTSKCAWWBRXVRKMJXTFDJHOQCP";
		String vnp_TransactionNo="13494893";
		Double vnp_Amount=200000.0;
		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(dt);
		String vnp_TransDate = dateString;
		
		
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_TransactionNo", vnp_TransactionNo);
		vnp_Params.put("vnp_TransDate", vnp_TransDate);
		vnp_Params.put("vnp_Amount", vnp_Amount + "000");
		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(fieldValue);
				// Build query
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
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
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
