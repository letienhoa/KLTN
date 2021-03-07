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

import carbook.dao.ChuyenXeDao;
import carbook.entity.ChuyenXe;
import carbook.entity.DemoFpt;
import carbook.response.BaseResponse;
import carbook.response.ChuyenXeResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/home")
public class ChuyenXeController {
	
	@Autowired
	public ChuyenXeDao dao;
	
	@RequestMapping(value ="", method = RequestMethod.GET)
	public List<ChuyenXeResponse> example() {

		List<ChuyenXe> data =dao.findAll();
		List<ChuyenXeResponse> dataResponse =new ChuyenXeResponse().maptolist(data);
        return dataResponse;
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ChuyenXe findOne1(@PathVariable("id") int id) {
		
		ChuyenXe data =dao.spGetCarBook(id);
		//ChuyenXeResponse dataResponse = new ChuyenXeResponse(data);
        return data;
	}
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test() {
		return("test client thanh cong");		
	}
	
	
	@RequestMapping(value="/demo-fpt",method=RequestMethod.POST)
	public ResponseEntity<BaseResponse> demoFPT(@RequestBody DemoFpt demo) {
		BaseResponse response= new BaseResponse();
		int h=0;
		try {
			System.out.print(demo.getName());
			h=1;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		System.out.print(demo.getName());
		if(h==0) {
			response.setData("ko thanh cong");
			}else response.setData("thanh cong");
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
				
	}

}
