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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Refund;
import com.paypal.api.payments.Sale;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import carbook.dao.BenDao;
import carbook.dao.TuyenXeDao;
import carbook.dao.VeDao;
import carbook.dao.XeDao;
import carbook.entity.Ben;
import carbook.entity.TuyenOff;
import carbook.entity.TuyenXe;
import carbook.entity.TuyenXeModelData;
import carbook.entity.TuyenXePhoBienDataModel;
import carbook.entity.Ve;
import carbook.entity.VeForCustomerByCodeDataModelFinal;
import carbook.entity.VeOverviewDataModel;
import carbook.entity.Xe;
import carbook.request.TuyenOffRequest;
import carbook.request.TuyenXeRequest;
import carbook.response.BaseResponse;
import carbook.response.GioChayResponse;
import carbook.response.TuyenXeCustomerResponse;
import carbook.service.EmailService;
import carbook.service.PaypalServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tuyenxe")
public class TuyenXeController {
	
	
	 @SuppressWarnings("unused")
	@Autowired 
	  private PaypalServices paypalService;
	  
	  @SuppressWarnings("unused")
	@Autowired 
	  private APIContext apiContext1;
	  
	  
	  @Autowired
	  private EmailService emailService; 
	  
	  @Value("${paypal.client.app}") private String clientId;
	  
	  @Value("${paypal.client.secret}") private String clientSecret;
	  
	  @Value("${paypal.mode}") private String mode;
	  
	   public Map<String, String> paypalSdkConfig(){ Map<String, String>
	  sdkConfig = new HashMap<>(); sdkConfig.put("mode", mode); return sdkConfig; }
	  
	   public OAuthTokenCredential authTokenCredential(){ return new
	  OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig()); }
	  
	   
	  public APIContext apiContext() throws PayPalRESTException{ APIContext
		  apiContext = new APIContext(authTokenCredential().getAccessToken());
	  	apiContext.setConfigurationMap(paypalSdkConfig()); return apiContext; }
	
	
	
	

	@Autowired
	private TuyenXeDao tuyenXeDao;
	
	@Autowired
	private BenDao benDao;
	
	@Autowired
	private XeDao xeDao;
	
	@Autowired
	private VeDao veDao;
	
	
	//Tạo tuyến xe
	@RequestMapping(value ="/create", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> create(
			@RequestBody TuyenXeRequest wrapper){
		BaseResponse response = new BaseResponse();
		TuyenXe tuyenXe= new TuyenXe();
		tuyenXe.setDiemDiId(wrapper.getDiemDiId());
		tuyenXe.setDiemToiId(wrapper.getDiemToiId());
		tuyenXe.setKhoangCach(wrapper.getKhoangCach());
		tuyenXe.setGiaCa(wrapper.getGiaCa());
		tuyenXe.setKhoangThoiGian(wrapper.getThoiGianChay());
		
		tuyenXeDao.create(tuyenXe);
		response.setData(tuyenXe);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	//Cập nhập tuyến xe
	@RequestMapping(value ="/{id}/update", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> update(
			@PathVariable(name="id")int id,
			@RequestBody TuyenXeRequest wrapper){
		BaseResponse response = new BaseResponse();
		TuyenXe tuyenXe =tuyenXeDao.findOne(id);
		if(tuyenXe==null)
		{
			response.setMessageError("không tồn tại tuyến xe này =((");
			return new ResponseEntity<BaseResponse>(response,HttpStatus.BAD_REQUEST);
			
		} else {
			
			tuyenXe.setDiemDiId(wrapper.getDiemDiId());
			tuyenXe.setDiemToiId(wrapper.getDiemToiId());
			tuyenXe.setKhoangCach(wrapper.getKhoangCach());
			tuyenXe.setGiaCa(wrapper.getGiaCa());
			tuyenXe.setKhoangThoiGian(wrapper.getThoiGianChay());
			tuyenXeDao.update(tuyenXe);
			response.setData(tuyenXe);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
	}
	
	
	//Lấy những tuyến xe khi đã có điểm đi ,điểm đến
	@RequestMapping(value ="/get-tuyen-xe", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> sptuyenxe(
			@RequestParam(name = "diem_di_id", required = false, defaultValue = "f") int diemDiId,
			@RequestParam(name = "diem_toi_id", required = false, defaultValue = "7") int diemToiId) {
		BaseResponse response= new BaseResponse();
		TuyenXe tuyenXe = tuyenXeDao.spGetByDiemDiDiemToi(diemDiId, diemToiId);
		if(tuyenXe!=null) {
		Ben ben = benDao.findOne(diemToiId);
		TuyenXeCustomerResponse responsedate = new TuyenXeCustomerResponse(ben,tuyenXe);
		response.setData(responsedate);
		}else {
			response.setMessageError("Không tìm thấy tuyến xe phù hợp");
		}
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	//Lấy danh sách giờ chạy của 1 tuyến xe
	@RequestMapping(value ="/{id}/gio-chay", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spgetAll(
			@PathVariable(name= "id") int id,
			@RequestParam(name="ngay",defaultValue = "01/01/2021")String ngay){
		BaseResponse response = new BaseResponse();
		List<Xe> list = xeDao.getXeByTuyenXe(id,ngay);
		List<GioChayResponse> listData = new GioChayResponse().mapToList(list);
		response.setData(listData);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	// lấy tất cả tuyến xe không lọc
	@RequestMapping(value ="/", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> getAll(){
		
		BaseResponse response = new BaseResponse();
		List<TuyenXeModelData> list = tuyenXeDao.findAll();
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	//Lấy danh sách những tuyến xe phổ biến
	@RequestMapping(value ="/get-tuyen-xe-pho-bien", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spGetListTuyenXePhoBien(){
		
		BaseResponse response = new BaseResponse();
		List<TuyenXePhoBienDataModel> list = tuyenXeDao.spGetListTuyenXePhoBien();
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	//Lấy số lượng vé chạy trọng ngày theo từng tuyến xe
	@RequestMapping(value ="/list-tuyen-xe-theo-ve", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> spGetTuyenXeTrongNgay(
			 ){
		BaseResponse response = new BaseResponse();
		List<VeOverviewDataModel> list= veDao.spGetTuyenXeTrongNgay();
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	
	}
	
	//Mở khóa tuyến xe cho hoạt động trở lại bình thường 
	@RequestMapping(value ="/{id}/bat-tuyen-xe", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> spBatTuyeXe(
			@PathVariable(name="id")int idTuyenXe
			 ){
		BaseResponse response = new BaseResponse();
		TuyenXe tuyenXe= tuyenXeDao.findOne(idTuyenXe);
		if(tuyenXe!=null && tuyenXe.getStatus()==0) {
			tuyenXe.setStatus(1);
			tuyenXeDao.update(tuyenXe);
			response.setData(tuyenXe);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
		 response.setMessageError("Không tìm thấy tuyến hoặc tuyến đã hoạt động");
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	
	}
	
	// Tạm ngưng chạy một tuyến xe
	@RequestMapping(value ="/tao-tuyen-off", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> createTuyenOff(@RequestBody TuyenOffRequest wrapper
			 ) throws PayPalRESTException{
		BaseResponse response = new BaseResponse();
		TuyenOff tuyenOff = new TuyenOff(wrapper);
		tuyenOff= tuyenXeDao.createTuyenOff(tuyenOff);
		
		List<Ve> list =veDao.getVeToCancel(wrapper.getIdTuyenXe(), wrapper.getGio(), wrapper.getNgay(), 0);
		if(list!=null) {
			for(Ve x: list) {
				Refund refund = new Refund();
				APIContext apiContext = apiContext();
				Integer h =veDao.spGetCountGiuong(x.getId());	
				VeForCustomerByCodeDataModelFinal ve2 = veDao.spGetVeForCustomerByCode(x.getCode());
				Double tienDoi= (double)x.getGiaVe()/h;
				Double moneyRefund = (tienDoi/22000)-(tienDoi/22000)*0.1+(tienDoi/22000)*0.1;
				moneyRefund = Math.round(moneyRefund * 100.0) / 100.0;
				Double moneyRefundmail = tienDoi-tienDoi*0.1+(tienDoi/22000)*0.1;
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
				emailService.sendEmail(x.getEmail(),"HỦY VÉ","Vì một số vấn đề kỹ thuật,nhà xe đã hủy tuyến "+ve2.getTuyenXe()+"-- giờ chạy: "+ve2.getGioChay()+ "-- ngày chạy"+x.getDate()+"-- hồi lại tiền cho quý khách và đã lấy 10% giá trị, số tiền được hồi lại là"+ moneyRefundmailString +" vnd"+" Chân thành xin lỗi quý khách rất nhiều");
				
			}
		} 

		response.setMessageError("Thành công rồi nè");
		response.setData(wrapper);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		
	}
	

}
