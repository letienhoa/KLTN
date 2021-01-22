package carbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import carbook.dao.QuyDoiDiemDao;
import carbook.entity.QuyDoiDiem;
import carbook.request.QuyDoiDiemRequest;
import carbook.response.BaseResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/quy-doi-diem")
public class QuyDoiDiemController {

	@Autowired
	private QuyDoiDiemDao quyDoiDiemDao;
	
	@RequestMapping(value="/create",method= RequestMethod.POST)
	public ResponseEntity<BaseResponse>create(@RequestBody QuyDoiDiemRequest wrapper){
		BaseResponse response= new BaseResponse();
		QuyDoiDiem qdd= new QuyDoiDiem();
		qdd.setTitle(wrapper.getTitle());
		qdd.setPoint(wrapper.getPoint());
		qdd.setDiscount(wrapper.getDiscount());
		quyDoiDiemDao.create(qdd);
		response.setData(qdd);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/update",method= RequestMethod.POST)
	public ResponseEntity<BaseResponse>update(
			@PathVariable(name="id")int id,
			@RequestBody QuyDoiDiemRequest wrapper){
		BaseResponse response= new BaseResponse();
		QuyDoiDiem qdd= quyDoiDiemDao.findOne(id);
		qdd.setPoint(wrapper.getPoint());
		qdd.setDiscount(wrapper.getDiscount());
		qdd.setTitle(wrapper.getTitle());
		quyDoiDiemDao.update(qdd);
		response.setData(qdd);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/get-all",method= RequestMethod.POST)
	public ResponseEntity<BaseResponse>getAll(){
		BaseResponse response= new BaseResponse();
		List<QuyDoiDiem> list= quyDoiDiemDao.getAllList();

		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
}
