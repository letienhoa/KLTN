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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carbook.dao.TramDungDao;
import carbook.entity.ResponseStatusEnum;
import carbook.entity.TramDung;
import carbook.request.TramDungRequest;
import carbook.response.BaseResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tram-dung")
public class TramDungController {

	@Autowired
	private TramDungDao tramDungDao;
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> create(@RequestBody TramDungRequest wrraper) {
		BaseResponse response= new BaseResponse();
		TramDung tramDung =new TramDung();
		tramDung.setId(wrraper.getId());
		tramDung.setTenTram(wrraper.getTenTram());
		tramDung.setDiaChi(wrraper.getDiaChi());
		tramDungDao.create(tramDung);
		response.setData(tramDung);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{id}/update", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> update(@RequestBody TramDungRequest wrraper,
			@PathVariable(name="id")int id) {
		BaseResponse response= new BaseResponse();
		TramDung tramDung =tramDungDao.findOne(id);
		if(tramDung!=null) {
			tramDung.setId(wrraper.getId());
			tramDung.setTenTram(wrraper.getTenTram());
			tramDung.setDiaChi(wrraper.getDiaChi());
			tramDungDao.update(tramDung);
			response.setData(tramDung);
		} else {
			response.setStatus(ResponseStatusEnum.DATA_INVALID);
			response.setMessageError("Không tìm thấy trạm dừng =((");
		}
		
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/get-list-tram-dung-tuyen-xe", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> sptuyenxe(
			@RequestParam(name = "diem_di", required = false, defaultValue = "f") int diemdi,
			@RequestParam(name = "diem_toi", required = false, defaultValue = "7") int diemtoi) {
		BaseResponse response= new BaseResponse();
		List<TramDung> data = tramDungDao.spGetTramDungForTuyenXe(diemdi, diemtoi);
		response.setData(data);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	

	@RequestMapping(value ="/get", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> test() {
		BaseResponse response= new BaseResponse();
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
}
