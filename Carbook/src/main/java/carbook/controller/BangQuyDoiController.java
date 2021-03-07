package carbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import carbook.dao.QuyDoiDiemDao;
import carbook.entity.QuyDoiDiem;
import carbook.request.BenRequest;
import carbook.response.BaseResponse;
import carbook.response.QuyDoiDiemCustomResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/quy-doi")
public class BangQuyDoiController {

	@Autowired
	private QuyDoiDiemDao quyDoiDiemDao;
	
	// lấy danh sách điểm quy đổi
	@RequestMapping(value="",method= RequestMethod.GET)
	public ResponseEntity<BaseResponse>getAll(){
		BaseResponse response= new BaseResponse();
		List<QuyDoiDiem> quyDoiDiem = quyDoiDiemDao.getAllList();
		List<QuyDoiDiemCustomResponse> listData = new QuyDoiDiemCustomResponse().mapToList(quyDoiDiem);
		response.setData(listData);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}

}
