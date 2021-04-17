package carbook.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import carbook.entity.ThongKeDoanhThuModelData;
import carbook.entity.Ve;
import carbook.entity.VeCustomerDataModel;

import carbook.entity.VeForCustomerByCodeDataModelFinal;
import carbook.entity.VeThongKeModelDate;
import carbook.request.VeHoiKhuRequest;
import carbook.request.VeRequest;
import carbook.response.BaseResponse;
import carbook.response.VeCustomerDataModelResponse;
import carbook.service.EmailService;
import carbook.service.EmailService2;
import carbook.service.GenerateCode;
import carbook.service.UtilsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ve")
public class VeController {

	@Autowired
	private EmailService emailService; 
	
	@Autowired 
	private EmailService2 emailser;
	
	@Autowired
	private VeDao veDao;
	
	@Autowired
	private PointDao pointDao;
	
	//Thống kê theo tuyến
	@RequestMapping(value ="thong-ke-theo-tuyen", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spGetTotalRevenueTuyenXe(
			@RequestParam(name = "time", required = false) Date time,
			@RequestParam(name = "selecter", required = false, defaultValue = "7") Integer selecter){
		BaseResponse response = new BaseResponse();
		List<VeThongKeModelDate> list = veDao.spGetTotalRevenueTuyenXe(time, selecter);
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}	
	
	//Thống kê doanh thu tổng
	@RequestMapping(value ="thong-ke-doanh-thu", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spGetTotalRevenueTiket(
			@RequestParam(name = "time", required = false, defaultValue = "2020/01/01") Date time,
			@RequestParam(name = "selecter", required = false, defaultValue = "7") Integer selecter){
		BaseResponse response = new BaseResponse();
		Calendar times= Calendar.getInstance();
		times.setTime(time);
		if(selecter==1)
		{
			times.set(Calendar.DAY_OF_MONTH,1);
		}
		else if(selecter==2) {
			 times.set(Calendar.MONTH,1);
			 times.set(Calendar.DAY_OF_MONTH,1);
			 times.add(Calendar.MONTH, -1);
		} else {
			response.setMessageError("yêu cầu lỗi khi nhập mốc chọn, mời chọn lại");
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
		List<ThongKeDoanhThuModelData> list = veDao.spGetTotalRevenueTiket(times, selecter);
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="thong-ke-theo-khach-hang", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spGetVeForCustomer(
			@RequestParam(name = "khach_hang_id", required = false) Integer khachHang){
		BaseResponse response = new BaseResponse();
		List<VeCustomerDataModel> list = veDao.spGetVeForCustomer(khachHang);
		List<VeCustomerDataModelResponse> listResponse =new VeCustomerDataModelResponse().mapToList(list);
		response.setData(listResponse);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/create", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> create(
			@RequestBody VeRequest wrapper){
		BaseResponse response = new BaseResponse();
		String code = GenerateCode.generateStringToEmail(new String(""));
		String slots =UtilsService.convertListObjectToJsonArrayt(wrapper.getSlot());
		List<String> slotMail = new ArrayList<String>();
		 for(int i=0;i<wrapper.getSlot().size();i++) { 
					  String g="";
					  if(wrapper.getSlot().get(i).getSoGhe()<=22) {
						  if(wrapper.getSlot().get(i).getSoGhe()<10){
							  g= "A0"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe());
						  }
						  else {
							  g= "A"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe());
						  }
					  }else {
						  if(wrapper.getSlot().get(i).getSoGhe()<32) {
							  g= "B0"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe()-22);
						  }else {
							  g= "B"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe()-22);
						  }
					  }
					 // g= g+"---"+wrapper.getSlot().get(i).getTen()+"--- Điểm xuống:"+wrapper.getSlot().get(i).getNoiXuong()+" :::: ";
					  slotMail.add(g); 
		} 
		String slotMails =slotMail.stream().collect(Collectors.joining(String.valueOf(",")));
		String tenTuyen = veDao.spGetNameTuyenXe(wrapper.getIdTuyenXe());
		String ngay=UtilsService.changeDateToString(UtilsService.changeStringToDate(wrapper.getDate()));
		
		emailser.sendMail(wrapper.getEmail(), code,tenTuyen,wrapper.getGioChay(),slotMails,+wrapper.getGiaVe(),ngay);
		/*
		 * emailService.sendEmail(wrapper.getEmail(),"MÃ CODE XÁC THỰC"
		 * ,"QUÝ KHÁCH VUI LÒNG GIỮ MÃ CODE NÀY ĐỂ XÁC THỰC KHI XUẤT PHÁT TẠI BẾN: "
		 * +"Mã Code: "+code+"                                    "
		 * +"Tuyến xe: "+tenTuyen
		 * +"       ---      "+"Giờ xuất phát: "+wrapper.getGioChay()+"giờ"
		 * +"    ---   "+"Thông tin giường nằm: ["+slotMails
		 * +"]       ---     "+"Giá tiền :"+wrapper.getGiaVe()+"vnd"+"     ---     "
		 * +"Ngày khởi hành: "+ngay);
		 */
		Double parde =wrapper.getGiaVe()/4000;
		
		int point =parde.intValue();
		Long message1=pointDao.spCreateHistoryPoint(wrapper.getEmail(), point, 0);
		System.out.print(message1);
		Long messageSQL=veDao.create(wrapper, slots,code);
		System.out.print(messageSQL);
		if(messageSQL==1||message1==1) {
			response.setMessageError("Lỗi khi thêm dữ liệu dưới database");
		} else {
			response.setData(wrapper);
		}
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/create2", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> create2(
			@RequestBody VeHoiKhuRequest wrapper){
		BaseResponse response = new BaseResponse();
		VeRequest wrapper1 = new VeRequest();
		
		//Ve 1
		
		// Đổ dữ liệu cho vé 1
		wrapper1.setGioChay(wrapper.getGioChay());
		wrapper1.setGioKetThuc(wrapper.getGioKetThuc());
		wrapper1.setIdTuyenXe(wrapper.getIdTuyenXe());
		wrapper1.setSdt(wrapper.getSdt());
		wrapper1.setEmail(wrapper.getEmail());
		wrapper1.setDate(wrapper.getDate());
		wrapper1.setGiaVe(wrapper.getGiaVe());
		wrapper1.setSlot(wrapper.getSlot());
		wrapper1.setPaypalId(wrapper.getPaypalId());
		wrapper1.setVnpayId(wrapper.getVnpayId());
		wrapper1.setDiemXuong(wrapper.getDiemXuong());
		String tuyenXe1 = veDao.spGetNameTuyenXe(wrapper.getIdTuyenXe());
		String code = GenerateCode.generateStringToEmail(wrapper.getEmail());
		String slots =UtilsService.convertListObjectToJsonArrayt(wrapper.getSlot());
		List<String> slotMail = new ArrayList<String>();
		 for(int i=0;i<wrapper.getSlot().size();i++) { 
					  String g="";
					  if(wrapper.getSlot().get(i).getSoGhe()<=22) {
						  if(wrapper.getSlot().get(i).getSoGhe()<10){
							  g= "A0"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe());
						  }
						  else {
							  g= "A"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe());
						  }
					  }else {
						  if(wrapper.getSlot().get(i).getSoGhe()<32) {
							  g= "B0"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe()-22);
						  }else {
							  g= "B"+ String.valueOf(wrapper.getSlot().get(i).getSoGhe()-22);
						  }
					  }
					  //g= g+"---"+wrapper.getSlot().get(i).getTen()+"--- Điểm xuống:"+wrapper.getSlot().get(i).getNoiXuong()+" :::: ";
					  slotMail.add(g); 
		} 
		String slotMails =slotMail.stream().collect(Collectors.joining(String.valueOf(",")));
		
		String ngay=UtilsService.getDateFormatVN(UtilsService.changeStringToDate(wrapper.getDate()));
		emailser.sendMail(wrapper.getEmail(), code,tuyenXe1,wrapper.getGioChay(),slotMails,+wrapper.getGiaVe(),ngay);
		/*
		 * emailService.sendEmail(wrapper.getEmail(),"MÃ CODE XÁC THỰC"
		 * ,"QUÝ KHÁCH VUI LÒNG GIỮ MÃ CODE NÀY ĐỂ XÁC THỰC KHI XUẤT PHÁT TẠI BẾN: "
		 * +"Mã Code: "+code+"                                    " +
		 * "Tuyến xe: "+tuyenXe1
		 * +"       ---      "+"Giờ xuất phát: "+wrapper.getGioChay()+"giờ"
		 * +"    ---   "+"Thông tin giường nằm: "+slotMails
		 * +"       ---     "+"Giá tiền :"+wrapper.getGiaVe()+"vnd"+"     ---     "
		 * +"Ngày khởi hành: "+ngay);
		 */
		Double parde =wrapper.getGiaVe()/4000;
		int point =parde.intValue();
		Long message1=pointDao.spCreateHistoryPoint(wrapper.getEmail(), point, 0);
		Long messageSQL=veDao.create(wrapper1, slots,code);
		
		
		// Vé 2
		VeRequest wrapper2 = new VeRequest();
		wrapper2.setGioChay(wrapper.getGioChay2());
		wrapper2.setGioKetThuc(wrapper.getGioKetThuc2());
		wrapper2.setIdTuyenXe(wrapper.getIdTuyenXe2());
		wrapper2.setSdt(wrapper.getSdt());
		wrapper2.setEmail(wrapper.getEmail());
		wrapper2.setDate(wrapper.getDate2());
		wrapper2.setGiaVe(wrapper.getGiaVe2());
		wrapper2.setSlot(wrapper.getSlot2());
		wrapper2.setPaypalId(wrapper.getPaypalId());
		wrapper2.setVnpayId(wrapper.getVnpayId());
		wrapper2.setDiemXuong(wrapper.getDiemXuong2());
		List<String> slotMail2 = new ArrayList<String>();
		 for(int i=0;i<wrapper.getSlot2().size();i++) { 
					  String g="";
					  if(wrapper.getSlot2().get(i).getSoGhe()<=22) {
						  if(wrapper.getSlot2().get(i).getSoGhe()<10){
							  g= "A0"+ String.valueOf(wrapper.getSlot2().get(i).getSoGhe());
						  }
						  else {
							  g= "A"+ String.valueOf(wrapper.getSlot2().get(i).getSoGhe());
						  }
					  }else {
						  if(wrapper.getSlot2().get(i).getSoGhe()<32) {
							  g= "B0"+ String.valueOf(wrapper.getSlot2().get(i).getSoGhe()-22);
						  }else {
							  g= "B"+ String.valueOf(wrapper.getSlot2().get(i).getSoGhe()-22);
						  }
					  }
					//  g= g+"---"+wrapper.getSlot2().get(i).getTen()+"--- Điểm xuống:"+wrapper.getSlot2().get(i).getNoiXuong()+" :::: ";
					  slotMail2.add(g); 
		} 
		String slotMails2 =slotMail2.stream().collect(Collectors.joining(String.valueOf(",")));
		
		
		String tuyenXe2 = veDao.spGetNameTuyenXe(wrapper.getIdTuyenXe2());
		String code2 = GenerateCode.generateStringToEmail(wrapper.getEmail());
		String slots2 =UtilsService.convertListObjectToJsonArrayt(wrapper.getSlot2());
		String ngay2=UtilsService.getDateFormatVN(UtilsService.changeStringToDate(wrapper.getDate2()));
		emailser.sendMail(wrapper.getEmail(), code2,tuyenXe2,wrapper2.getGioChay(),slotMails2,+wrapper2.getGiaVe(),ngay2);
		/*
		 * emailService.sendEmail(wrapper.getEmail(),"MÃ CODE XÁC THỰC"
		 * ,"QUÝ KHÁCH VUI LÒNG GIỮ MÃ CODE NÀY ĐỂ XÁC THỰC KHI XUẤT PHÁT TẠI BẾN: "
		 * +"Mã Code: "+code2+"                                    "
		 * +"Tuyến xe: "+tuyenXe2
		 * +"       ---      "+"Giờ xuất phát: "+wrapper2.getGioChay()+"giờ"
		 * +"    ---   "+"Thông tin giường nằm: "+slotMails2
		 * +"       ---     "+"Giá tiền :"+wrapper2.getGiaVe()+"vnd"+"     ---     "
		 * +"Ngày khởi hành: "+ngay2);
		 */
		Double parde2 =wrapper2.getGiaVe()/4000;
		int point2 =parde2.intValue();
		Long message2=pointDao.spCreateHistoryPoint(wrapper.getEmail(), point2, 0);
		Long messageSQL2=veDao.create(wrapper2, slots2,code2);
		if(messageSQL==1||message1==1||message2==1||messageSQL2==1) {
			response.setMessageError("Lỗi khi thêm dữ liệu dưới database");
		} else {
			response.setMessageError("Đặt vé thành công");
		}
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
		
	
	@RequestMapping(value ="get-ve-by-code", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spGetVeForCustomerByCode(
			@RequestParam(name = "code", required = false) String code){
		BaseResponse response = new BaseResponse();
		VeForCustomerByCodeDataModelFinal datas= veDao.spGetVeForCustomerByCode(code);
		if(datas ==null) {
			response.setMessageError("Nhập mã tào lao nhe Đỉnh =))))");
		}else {
			response.setData(datas);
		}

		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}

	
	@RequestMapping(value ="cancel-ticket", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> huyVe(
			@RequestParam(name = "id", required = false) Integer id){
		BaseResponse response = new BaseResponse();
		Ve ve = veDao.findOne(id);
		if(ve ==null) {
			response.setMessageError("Không tồn tại vé này!!!");
		}else {
			Date now= new Date();
			Date h= ve.getDate();
			Calendar c = Calendar.getInstance();
			c.setTime(h);
			int t1 =c.get(Calendar.DAY_OF_MONTH);
			c.setTime(now);
			int t2= c.get(Calendar.DAY_OF_MONTH);
			if(ve.getTrangThai()==3||ve.getTrangThai()==1||t1-t2<2)
			{
				response.setMessageError("Không thể hủy vé này!!!");
			}else {
				ve.setTrangThai(3);
				veDao.update(ve);
				veDao.spDeleteGiuong(id);
				response.setData(ve);
			}
		
		}

		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
}
