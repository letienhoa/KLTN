package carbook.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

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

import carbook.dao.KhachHangDao;
import carbook.dao.RoleDetalDao;
import carbook.entity.ResponseStatusEnum;
import carbook.entity.RoleDetal;
import carbook.entity.User;
import carbook.request.KhachHangRequest;
import carbook.request.KhachHangUpdateRequest;
import carbook.response.BaseResponse;
import carbook.security.PasswordEncryption;
import carbook.service.JwtService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private KhachHangDao khachHangdao;
	
	@Autowired
	private RoleDetalDao roleDetalDaol;
	
	//@Autowired
	  //private JwtService jwtService;
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> create(@RequestBody KhachHangRequest wrapper) {
		BaseResponse response= new BaseResponse();
		String matKhauMK = null;
		User admin =new User();
		admin = khachHangdao.findByUsername(wrapper.getTaiKhoan());
		
		if(admin!=null) {
			response.setMessageError("Tên tài khoản đã tồn tại, vui lòng nhập lại tên khác <3.");
			response.setStatus(ResponseStatusEnum.DATA_INVALID);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.BAD_REQUEST);
		} else {
			admin= new User();
			PasswordEncryption pe = new PasswordEncryption();
			try {
				matKhauMK= pe.convertHashToString(wrapper.getMatKhau());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			admin.setTaiKhoan(wrapper.getTaiKhoan());
			admin.setSdt(wrapper.getSdt());
			admin.setPassword(matKhauMK);
			admin.setTenKh(wrapper.getTen());
			admin.setDiaChi(wrapper.getDiaChi());
			admin.setCmnd(wrapper.getCmnd());
			admin.setEmail(wrapper.getEmail());
			admin.setQuanHuyen(wrapper.getQuanHuyen());
			admin.setThanhPho(wrapper.getThanhPho());
			admin.setStatus(1);
			admin.setConfirm(1);
			khachHangdao.create(admin);
			admin= khachHangdao.findByUsername(admin.getTaiKhoan());
			
			RoleDetal role =new RoleDetal(); 
			role.setIdUser(admin.getId());
			role.setRoleId("ROLE_ADMIN");
			roleDetalDaol.create(role);
			response.setData(admin);
			
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}

	}
	
	@RequestMapping(value ="/change-password", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> changepassword(
			HttpServletRequest req,
			@RequestParam(name = "user_name", required = false, defaultValue = "") String userName,
			@RequestParam(name = "password", required = false, defaultValue = "") String passWord) {
		BaseResponse response= new BaseResponse();
		String authorization = req.getHeader("Authorization"); 
		String matKhauMK = null;
		User admin =khachHangdao.findByUsername(userName);
		if(admin!=null)
		{
			PasswordEncryption pe = new PasswordEncryption();
			try {
				matKhauMK= pe.convertHashToString(passWord);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0; i<=JwtService.listToken.size();i++) {
				if(JwtService.listToken.get(i)== authorization)
				{
					JwtService.listToken.remove(i);
				}
			}
			admin.setPassword(matKhauMK);
			khachHangdao.update(admin);
			response.setMessageError("Đổi mật khẩu thành công");
		} else {
			response.setMessageError("Tài khoản không tồn tại");
		}	
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
	
	@RequestMapping(value ="/update/{id}/", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> update(
			@PathVariable(name="id")int id,
			@RequestBody KhachHangUpdateRequest wrapper) {
		BaseResponse response= new BaseResponse();
		User khachHang =new User();
		khachHang = khachHangdao.getById(id);
		
		if(khachHang==null) {
			response.setMessageError("Tài khoản không tồn tại, vui lòng nhập lại.");
			response.setStatus(ResponseStatusEnum.DATA_INVALID);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.BAD_REQUEST);
		} else {
			
			khachHang.setSdt(wrapper.getSdt());
			khachHang.setTenKh(wrapper.getTen());
			khachHang.setDiaChi(wrapper.getDiaChi());
			khachHang.setCmnd(wrapper.getCmnd());
			khachHang.setEmail(wrapper.getEmail());
			khachHang.setQuanHuyen(wrapper.getQuanHuyen());
			khachHang.setThanhPho(wrapper.getThanhPho());
			
			khachHangdao.update(khachHang);
			response.setData(khachHang);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
	}

}
