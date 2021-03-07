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

import carbook.dao.XeDao;
import carbook.entity.Xe;
import carbook.request.XeRequest;
import carbook.response.BaseResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/xe")
public class XeController {

	@Autowired
	private XeDao xeDao;
	
	//Tạo xe mới
	@RequestMapping(value ="/create", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> create(@RequestBody XeRequest wrraper){
		BaseResponse response = new BaseResponse();
		Xe xe= new Xe();
		xe.setTenXe(wrraper.getTenXe());
		xe.setHangXe(wrraper.getHangXe());
		xe.setTuyenSanSangId(wrraper.getTuyenSanSangId());
		xe.setTuyenOffId(wrraper.getTuyenOffId());
		xe.setGioChay(wrraper.getGioChay());
		xe.setFloor(wrraper.getFloor());
		xe.setRow(wrraper.getRow());
		xe.setSrow(wrraper.getSrow());
		
		xeDao.create(xe);		
		
		response.setData(xe);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}	
	
	//Cập nhật xe theo ID
	@RequestMapping(value ="/update/{id}", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> update(@RequestBody XeRequest wrraper,
			@PathVariable(name="id")int id){
		BaseResponse response = new BaseResponse();
		Xe xe= xeDao.findOne(id);
		if(xe==null) {
			response.setMessageError("Xe không tồn tại");
		} else {
		xe.setTenXe(wrraper.getTenXe());
		xe.setHangXe(wrraper.getHangXe());
		xe.setTuyenSanSangId(wrraper.getTuyenSanSangId());
		xe.setTuyenOffId(wrraper.getTuyenOffId());
		xe.setGioChay(wrraper.getGioChay());
		xe.setFloor(wrraper.getFloor());
		xe.setRow(wrraper.getRow());
		xe.setSrow(wrraper.getSrow());
		
		
		xeDao.update(xe);		
		
		response.setData(xe);
		}
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
	//Lấy tất cả danh sách xe
	@RequestMapping(value ="/", method = RequestMethod.GET )
	public ResponseEntity<BaseResponse> getAll(){ 
		BaseResponse response = new BaseResponse();
		List<Xe> list= xeDao.findAll();
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
}
