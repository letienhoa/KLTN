package carbook.vnpay;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import carbook.dao.PointDao;
import carbook.dao.VeDao;
import carbook.request.SlotRequest;
import carbook.request.VeHoiKhuRequest;
import carbook.request.VeRequest;
import carbook.response.BaseResponse;
import carbook.service.EmailService2;
import carbook.service.GenerateCode;
import carbook.service.UtilsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payVn")
public class VnPayController {

	public static Map<String, List<SlotRequest>> listSlotVnPay = new HashMap<String, List<SlotRequest>>();

	@Autowired
	private VeDao veDao;

	@Autowired
	private EmailService2 emailser;

	@Autowired
	private PointDao pointDao;

	public static String linkURL = "";

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public String post(@RequestParam(name = "vnp_Amount", required = false, defaultValue = "null") Double vnp_Amount,
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
			@RequestParam(name = "gio_ket_thuc", required = false, defaultValue = "null") Integer gio_ket_thuc,
			@RequestParam(name = "id_tuyen_xe", required = false, defaultValue = "null") Integer id_tuyen_xe,
			@RequestParam(name = "sdt", required = false, defaultValue = "null") String sdt,
			@RequestParam(name = "email", required = false, defaultValue = "null") String email,
			@RequestParam(name = "date", required = false, defaultValue = "null") String date,
			@RequestParam(name = "diem_xuong", required = false, defaultValue = "null") String diem_xuong,
			@RequestParam(name = "gio_chay2", required = false, defaultValue = "null") Integer gio_chay2,
			@RequestParam(name = "gio_ket_thuc2", required = false, defaultValue = "null") Integer gio_ket_thuc2,
			@RequestParam(name = "id_tuyen_xe2", required = false, defaultValue = "null") Integer id_tuyen_xe2,
			@RequestParam(name = "date2", required = false, defaultValue = "null") String date2,
			@RequestParam(name = "code_slot1", required = false, defaultValue = "null") String code_slot1,
			@RequestParam(name = "code_slot2", required = false, defaultValue = "null") String code_slot2,
			@RequestParam(name = "gia_ve", required = false, defaultValue = "0.0") Double gia_ve,
			@RequestParam(name = "gia_ve2", required = false, defaultValue = "0.0") Double gia_ve2)
			throws UnsupportedEncodingException {

		String button = "<a href=\r\n" + linkURL + ">\r\n"
				+ "<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n"
				+ "  border-radius: 4px;\r\n" + "  background-color: #f4511e;\r\n" + "  border: none;\r\n"
				+ "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n" + "  font-size: 22px;\r\n"
				+ "  padding: 20px;\r\n" + "  width: 700px;\r\n" + "  transition: all 0.5s;\r\n"
				+ "  cursor: pointer;\r\n" + "  margin-left: 350px;\">" + "OK" + "</button>" + "</a>";
		String responses = "<div><h1 style=\"margin-left:500px;	\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
		Long messageSQL1 = null;
		Long message1 = null;
		// Sercond ticket
		if (code_slot2 != "null") {
			List<SlotRequest> valueOfCodeSlot2 = listSlotVnPay.get(code_slot2);
			String code2 = GenerateCode.generateStringToEmail(new String(""));
			String slots2 = UtilsService.convertListObjectToJsonArrayt(valueOfCodeSlot2);
			List<String> slotMail2 = new ArrayList<String>();
			for (int i = 0; i < valueOfCodeSlot2.size(); i++) {
				String g = "";
				if (valueOfCodeSlot2.get(i).getSoGhe() <= 22) {
					if (valueOfCodeSlot2.get(i).getSoGhe() < 10) {
						g = "A0" + String.valueOf(valueOfCodeSlot2.get(i).getSoGhe());
					} else {
						g = "A" + String.valueOf(valueOfCodeSlot2.get(i).getSoGhe());
					}
				} else {
					if (valueOfCodeSlot2.get(i).getSoGhe() < 32) {
						g = "B0" + String.valueOf(valueOfCodeSlot2.get(i).getSoGhe() - 22);
					} else {
						g = "B" + String.valueOf(valueOfCodeSlot2.get(i).getSoGhe() - 22);
					}
				}
				slotMail2.add(g);
			}
			String slotMails2 = slotMail2.stream().collect(Collectors.joining(String.valueOf(",")));
			String tenTuyen2 = veDao.spGetNameTuyenXe(id_tuyen_xe2);
			String ngay2 = UtilsService.changeDateToString(UtilsService.changeStringToDate(date2));
			emailser.sendMailBookingTicket(email, code2, tenTuyen2, gio_chay2, slotMails2, gia_ve, ngay2);
			Double parde2 = gia_ve2 / 4000;
			int point2 = parde2.intValue();
			message1 = pointDao.spCreateHistoryPoint(email, point2, 0);
			System.out.print(message1);
			// tạo model để insert
			VeRequest wrapper2 = new VeRequest();
			wrapper2.setGioChay(gio_chay2);
			wrapper2.setGioKetThuc(gio_ket_thuc2);
			wrapper2.setIdTuyenXe(id_tuyen_xe2);
			wrapper2.setSdt(sdt);
			wrapper2.setEmail(email);
			wrapper2.setDate(date2);
			wrapper2.setGiaVe(gia_ve2);
			wrapper2.setSlot(valueOfCodeSlot2);
			wrapper2.setPaypalId("null");
			wrapper2.setVnpayId(vnp_TransactionNo);
			wrapper2.setDiemXuong(diem_xuong);
			messageSQL1 = veDao.create(wrapper2, slots2, code2);
			System.out.print(messageSQL1);
		}
		// fisrt ticket
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
		emailser.sendMailBookingTicket(email, code, tenTuyen, gio_chay, slotMails, gia_ve, ngay);
		Double parde = gia_ve / 4000;
		int point = parde.intValue();
		Long message2 = pointDao.spCreateHistoryPoint(email, point, 0);
		System.out.print(message1);
		// tạo model để insert
		VeRequest wrapper = new VeRequest();
		wrapper.setGioChay(gio_chay);
		wrapper.setGioKetThuc(gio_ket_thuc);
		wrapper.setIdTuyenXe(id_tuyen_xe);
		wrapper.setSdt(sdt);
		wrapper.setEmail(email);
		wrapper.setDate(date);
		wrapper.setGiaVe(gia_ve);
		wrapper.setSlot(valueOfCodeSlot);
		wrapper.setPaypalId("null");
		wrapper.setVnpayId(vnp_TransactionNo);
		wrapper.setDiemXuong(diem_xuong);
		Long messageSQL2 = veDao.create(wrapper, slots, code);
		System.out.print(messageSQL2);
		if (messageSQL1 == 1 || message1 == 1 || message2 == 1 || messageSQL2 == 1) {
			responses = "<div><h1 style=\"margin-left:500px;	\">THANH TOÁN THẤT BẠI</h1></div>" + button;
		} else {
			responses = "<div><h1 style=\"margin-left:500px;	\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
		}
		return responses;
	}

	@RequestMapping(value = "/get-code", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> createStringQuery(
			@RequestParam(name = "link", required = false, defaultValue = "null") String link,
			@RequestParam(name = "vnp_IpAddr", required = false, defaultValue = "null") String vnp_IpAddr,
			@RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnp_OrderInfo,
			@RequestParam(name = "vnp_OrderType", required = false, defaultValue = "null") String vnp_OrderType,
			@RequestBody VeHoiKhuRequest wrapper) throws UnsupportedEncodingException {
		BaseResponse response = new BaseResponse();
		VnPayController.linkURL = link;
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
		Double vnp_Amount = wrapper.getGiaVe();
		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(dt);
		vnp_CreateDate = dateString;
		listSlotVnPay.put(vnp_CreateDate, wrapper.getSlot());
		String codeSlot1 = dateString;
		String codeSlot2 = "null";
		String vnp_ReturnUrl = "";
		// Nếu là vé 2 chiều
		if (wrapper.getGioChay2() != null) {
			vnp_Amount = wrapper.getGiaVe() + wrapper.getGiaVe2();
			codeSlot2 = formatter.format(new Date());
			listSlotVnPay.put(codeSlot2, wrapper.getSlot2());
		} else {
			
		}
		vnp_Amount*=100;
		vnp_ReturnUrl = "http://localhost:8082/api/payVn/submit?" + "gio_chay=" + wrapper.getGioChay().toString()
				+ "&gio_ket_thuc=" + wrapper.getGioKetThuc().toString() + "&sdt=" + wrapper.getSdt().toString()
				+ "&email=" + wrapper.getEmail().toString() + "&date=" + wrapper.getDate().toString() + "&id_tuyen_xe"
				+ wrapper.getIdTuyenXe() + "&code_slot1" + codeSlot1 + "&code_slot2" + codeSlot2.toString()
				+ "gio_chay2=" + wrapper.getGioChay2() + "&gio_ket_thuc2="
				+ wrapper.getGioKetThuc2() + "&id_tuyen_xe2" + wrapper.getIdTuyenXe2() + "&date2="
				+ wrapper.getDate2() + "gia_ve=" + wrapper.getGiaVe() + "gia_ve2="
				+ wrapper.getGiaVe2();
		vnp_TxnRef = vnp_CreateDate;
		double vnp_Amountd = vnp_Amount;
		Integer vnp_Amounti = (int)vnp_Amountd;
		vnp_ReturnUrl = "http://localhost:8082/api/payVn/submit?" + "gio_chay=" + wrapper.getGioChay().toString()
				+ "&gio_ket_thuc=" + wrapper.getGioKetThuc().toString() + "&sdt=" + wrapper.getSdt().toString()
				+ "&email=" + wrapper.getEmail().toString() + "&date=" + wrapper.getDate().toString() + "&id_tuyen_xe"
				+ wrapper.getIdTuyenXe() + "&code_slot1" + codeSlot1 + "&code_slot2" + codeSlot2.toString()
				+ "gio_chay2=" + wrapper.getGioChay2() + "&gio_ket_thuc2="
				+ wrapper.getGioKetThuc2();
		//vnp_ReturnUrl = "http://localhost:8082/api/payVn/submit?";
		// Tạo mã map links
		
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", vnp_Amounti.toString());
		vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
		vnp_Params.put("vnp_BankCode", vnp_BankCode);
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
		vnp_Params.put("vnp_OrderType", vnp_OrderType);
		vnp_Params.put("vnp_Locale", vnp_Locale);
		vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
		String queryUrl = VnPayConfig.generateQueryUrl(vnp_Params, vnp_SupChecksumt);
		response.setData(queryUrl);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> refundVnpay(

	) throws UnsupportedEncodingException {
		BaseResponse response = new BaseResponse();

		String vnp_Command = "pay";
		String vnp_TmnCode = "BZKHFB2U";
		String vnp_TransactionNo = "13494893";
		Double vnp_Amount = 200000.0;
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

		String queryUrl = "";

		response.setData(queryUrl);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

}
