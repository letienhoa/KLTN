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

import carbook.dao.BenDao;
import carbook.dao.DiemDonDao;
import carbook.entity.Ben;
import carbook.entity.DiemDon;
import carbook.entity.QuyTacIdBenXe;
import carbook.request.BenRequest;
import carbook.response.BaseResponse;
import carbook.response.BenResponse;
import carbook.response.BenToiResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ben")
public class BenController {

	@Autowired
	private BenDao benDao;
	
	@Autowired
	private DiemDonDao diemDonDao;
	
	@RequestMapping(value="/create",method= RequestMethod.POST)
	public ResponseEntity<BaseResponse>create(@RequestBody BenRequest wrraper){
		BaseResponse response= new BaseResponse();
		Ben ben1= benDao.findOneByThanhPho(wrraper.getThanhPho());
		if(ben1!=null) {
			response.setMessageError("Bến này đã tồn tại, vui lòng chọn thành phố khác !!!");
		} else {
			Ben ben= new Ben();
			ben.setId(-1);
			List<QuyTacIdBenXe> listQuyTac= benDao.getAllQuyTacId();
			for(QuyTacIdBenXe x: listQuyTac) {
				if(x.getThanhPho().equals(wrraper.getThanhPho())){
					ben.setId(x.getId());
				}
				
			}
			if(ben.getId()==-1)
			{
				response.setMessageError("Không có thành phố này ,vui lòng nhập lại !!!");
				response.setData(null);
			} else {
				ben.setPicture(wrraper.getPicture());
				ben.setThanhPho(wrraper.getThanhPho());
				ben.setTenBen(wrraper.getTenBen());
				ben.setDiaChi(wrraper.getDiaChi());
				benDao.create(ben);
				response.setData(ben);
			}	
		}
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/update",method= RequestMethod.POST)
	public ResponseEntity<BaseResponse>update(
			@PathVariable (name="id") int id,
			@RequestBody BenRequest wrraper){
		BaseResponse response= new BaseResponse();
		Ben ben =new Ben();
		try {
			ben= benDao.findOne(id);
			if(ben==null)
			{
				response.setMessageError("Không tìm thấy bến =((");
				return new ResponseEntity<BaseResponse>(response,HttpStatus.BAD_REQUEST);
			} else {
				ben.setPicture(wrraper.getPicture());
				ben.setTenBen(wrraper.getTenBen());
				ben.setDiaChi(wrraper.getDiaChi());
				benDao.update(ben);
				response.setData(ben);
				return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
			}
		}catch (Exception e) {
			ben=null;
			return new ResponseEntity<BaseResponse>(response,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value ="/get-list-ben-toi", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> spGetBenToi(
			@RequestParam(name = "ben_di_id", required = false, defaultValue = "0") int benDiId){
		BaseResponse response = new BaseResponse();
		List<Ben> data = benDao.spGetBenToi(benDiId);
		List<BenToiResponse> dataResponse = new BenToiResponse().mapTolist(data);
        response.setData(dataResponse);
        return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> getAll(){
		List<Ben> data = benDao.findAll();
		List<BenResponse> dataResponse = new BenResponse().mapToList(data);
		BaseResponse response = new BaseResponse();
		
		response.setData(dataResponse);
        return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{id}/get-list-diem-don", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> spGetDiemDon(
			@PathVariable(name= "id") int id){
		BaseResponse response = new BaseResponse();
		List<DiemDon> data = diemDonDao.getListForBen(id);
		
		response.setData(data);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/get-all-diem-don", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> spgetAll(){
		BaseResponse response = new BaseResponse();	
		List<DiemDon> data = diemDonDao.findAll();
		
		response.setData(data);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/get-detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findOne(@PathVariable(name="id")int id) {
		BaseResponse response= new BaseResponse();
		Ben ben= benDao.findOne(id);
		if(ben==null) {
			response.setMessageError("Bến không tồn tại !!!");
		} else {
			response.setData(ben);
		}
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
}
