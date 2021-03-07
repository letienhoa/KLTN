package carbook.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carbook.dao.GiuongDao;
import carbook.entity.GiuongModelData;
import carbook.entity.ListGiuongModelData;
import carbook.response.BaseResponse;
import carbook.service.UtilsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/web/giuong")
public class GiuongController {

	@Autowired
	private GiuongDao giuongDao;
	
	//Lấy danh sách giường nằm của xe và tuyến
	@RequestMapping(value ="/get-list-giuong-for-xe", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> spGetGiuongByTuyenXeId(
			@RequestParam(name = "tuyen_xe_id", required = false, defaultValue = "1") int tuyenXeId,
			@RequestParam(name = "gio", required = false, defaultValue = "7") int gio,
			@RequestParam(name = "ngay", required = false, defaultValue = "01/01/2001") String ngay) {
		BaseResponse response= new BaseResponse();
		Date ngay1 =UtilsService.changeStringToDate(ngay);
		
		ListGiuongModelData data =giuongDao.spGetGiuongByTuyenXeId(tuyenXeId, gio,ngay1);
		response.setData(data);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);

	}
	
	
}
