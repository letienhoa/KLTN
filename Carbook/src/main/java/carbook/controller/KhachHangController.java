package carbook.controller;

import javax.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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

import carbook.dao.KhachHangDao;
import carbook.dao.PointDao;
import carbook.dao.RoleDetalDao;
import carbook.dao.VeDao;

import carbook.entity.ResponseStatusEnum;
import carbook.entity.RoleDetal;
import carbook.entity.TokenResponse;
import carbook.entity.User;
import carbook.entity.UserPointDataModel;
import carbook.entity.UserRoleDataModel;
import carbook.entity.UserToken;
import carbook.request.KhachHangRequest;
import carbook.request.KhachHangUpdateRequest;
import carbook.response.BaseResponse;
import carbook.response.UserPointResponse;
import carbook.security.PasswordEncryption;
import carbook.service.EmailService;
import carbook.service.GenerateCode;
import carbook.service.JwtService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/khach-hang")
public class KhachHangController {

	@Autowired
	private KhachHangDao khachHangdao;
	
	@Autowired
	private RoleDetalDao roleDetalDaol;
	
	@Autowired
	private VeDao veDao;
	
	@Autowired
	private PointDao pointDao;
	
	@Autowired
	  private JwtService jwtService;
	
	@Autowired
	private EmailService emailService; 
	
	public static List<String> listCode = new ArrayList<String>();
	
	@RequestMapping(value ="/test", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> tesst(HttpServletRequest req) {
		BaseResponse response= new BaseResponse();
		
			response.setData(null);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> create(@RequestBody KhachHangRequest wrapper) {
		BaseResponse response= new BaseResponse();
		String matKhauMK = null;
		User khachHang =new User();
		khachHang = khachHangdao.findByUsername(wrapper.getTaiKhoan());
		
		if(khachHang!=null) {
			response.setMessageError("Tên tài khoản đã tồn tại, vui lòng nhập lại tên khác <3.");
			response.setStatus(ResponseStatusEnum.DATA_INVALID);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.BAD_REQUEST);
		} else {
			khachHang= new User();
			PasswordEncryption pe = new PasswordEncryption();
			try {
				matKhauMK= pe.convertHashToString(wrapper.getMatKhau());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			khachHang.setTaiKhoan(wrapper.getTaiKhoan());
			khachHang.setSdt(wrapper.getSdt());
			khachHang.setPassword(matKhauMK);
			khachHang.setTenKh(wrapper.getTen());
			khachHang.setDiaChi(wrapper.getDiaChi());
			khachHang.setCmnd(wrapper.getCmnd());
			khachHang.setEmail(wrapper.getEmail());
			khachHang.setQuanHuyen(wrapper.getQuanHuyen());
			khachHang.setThanhPho(wrapper.getThanhPho());
			khachHang.setDiemTichLuy(0);
			khachHang.setDiscount(0);
			khachHang.setTongDiem(0);
			String code=GenerateCode.generateString();
			this.listCode.add(code);
			khachHangdao.create(khachHang);
			
			khachHang= khachHangdao.findByUsername(khachHang.getTaiKhoan());
			
			RoleDetal role =new RoleDetal(); 
			role.setIdUser(khachHang.getId());
			role.setRoleId("ROLE_USER");
			roleDetalDaol.create(role);
			
			try {
				emailService.sendEmail(wrapper.getEmail(),"XÁC THỰC EMAIL","Bấm vào links này để xác thực tài khoản: "
						+"http://localhost:8082/api/khach-hang/confirm?user_name="+wrapper.getTaiKhoan()+"&code="+code);
						response.setData(khachHang);
			} catch (Exception e) {
				response.setMessageError("email bạn có vấn đề, nhập lại");
			}
			
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}

	}
	
	@RequestMapping(value ="/confirm", method = RequestMethod.GET)
	public String spGetBenToi(
			@RequestParam(name = "user_name", required = false, defaultValue = "0") String userName,
			@RequestParam(name = "code", required = false, defaultValue = "0") String code){
		
		int h=0;
		String m= new String();
		for(String x: listCode) {
			if(x.equals(code)) {
				h=1;
				m=x;
			} 
		}
		listCode.remove(m);
		User user= khachHangdao.findByUsername(userName);
		String responses= new String("");
		if(user!=null && h==1)
		{
			user.setConfirm(1);
			user.setStatus(1);
			khachHangdao.update(user);
			String button="<a href=\"http://localhost:4200/login\">"+
							"<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n" + 
								"  border-radius: 4px;\r\n" + 
								"  background-color: #f4511e;\r\n" + 
								"  border: none;\r\n" + 
								"  color: #FFFFFF;\r\n" + 
								"  text-align: center;\r\n" + 
								"  font-size: 22px;\r\n" + 
								"  padding: 20px;\r\n" + 
								"  width: 700px;\r\n" + 
								"  transition: all 0.5s;\r\n" + 
								"  cursor: pointer;\r\n" + 
								"  margin-left: 350px;\">"+"Nhấn vào đây để đăng nhập"
							+ "</button>"+
							"</a>";
			 
			 
			responses ="<div><h1 style=\"margin-left:500px;	\">Bạn đã xác thực thành công</h1></div>"+button;
	        h=0;
		} else {
			responses="<div><h1 style=\" margin-left: 350px;\">Đừng cố xâm nhập vào trang web của mình nhé, ahihi</h1></div>";
		}
        return responses;
	}
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public ResponseEntity<TokenResponse> login(HttpServletRequest request ,@RequestBody KhachHangRequest wrapper) {
		TokenResponse respnse;
		String result = "hihihihi";
		@SuppressWarnings("unused")
		HttpStatus httpStatus=null;
		String matKhauMK = null;
		UserToken khachHang =new UserToken();
		User khachHang1 =new User();
		PasswordEncryption pe = new PasswordEncryption();
		try {
			matKhauMK= pe.convertHashToString(wrapper.getMatKhau());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			khachHang1=khachHangdao.findByUsernameAndPassword(wrapper.getTaiKhoan(), matKhauMK);
			khachHang=khachHangdao.getByTaiKhoanMatKhau(wrapper.getTaiKhoan(),matKhauMK);
			if(khachHang==null)
			{
				result = "Wrong userId and password";
				httpStatus = HttpStatus.BAD_REQUEST;
			} else if(khachHang1.getStatus()==0) {
				
				result = "Tài khoản này đã vô hiệu hóa,liên lạc với Admin để giải quyết.";
			
			} else {
				
				result = jwtService.generateTokenLogin(khachHang.getTaiKhoan());
				JwtService.listToken.add(result);
		      	 httpStatus = HttpStatus.OK;
			}
		} catch (Exception ex) {
		      result = "Server Error";
		      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		respnse= new TokenResponse(result);
		respnse.setId(khachHang.getId());
		respnse.setName(khachHang.getName());
		respnse.setEmail(khachHang.getEmail());
		respnse.setDiscount(khachHang.getDiscount());
		respnse.setRoles(khachHang.getRoles());
		return new ResponseEntity<TokenResponse>(respnse,HttpStatus.OK);
	}
		
	@RequestMapping(value ="/logout", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> logout(HttpServletRequest req) {
		BaseResponse response= new BaseResponse();
		String authorization = req.getHeader("Authorization"); 
		for(int i=0; i<JwtService.listToken.size();i++) {
			if(JwtService.listToken.get(i).equals(authorization))
			{
				JwtService.listToken.remove(i);
			}
		}
		response.setMessageError("Đăng xuất thành công");
			response.setData(null);
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}

	@RequestMapping(value ="/change-password", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> changepassword(
			HttpServletRequest req,
			@RequestParam(name = "user_name", required = false, defaultValue = "") String userName,
			@RequestParam(name = "password_old", required = false, defaultValue = "") String pass,
			@RequestParam(name = "password", required = false, defaultValue = "") String passWord) {
		BaseResponse response= new BaseResponse();
		String authorization = req.getHeader("Authorization");
		String matKhauMK = null;
		PasswordEncryption pe = new PasswordEncryption();
		try {
			matKhauMK= pe.convertHashToString(pass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User khachHang =khachHangdao.findByUsernameAndPassword(userName, matKhauMK);
		if(khachHang!=null)
		{
			try {
				matKhauMK= pe.convertHashToString(passWord);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0; i<JwtService.listToken.size();i++) {
				if(JwtService.listToken.get(i).equals(authorization))
				{
					JwtService.listToken.remove(i);
				}
			}
			
			khachHang.setPassword(matKhauMK);
			khachHangdao.update(khachHang);
			response.setMessageError("Đổi mật khẩu thành công");
		} else {
			response.setMessageError("Tài khoản không tồn tại");
		}	
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> index(
			@PathVariable(name="id")int id) {
		BaseResponse response= new BaseResponse();
		User user = khachHangdao.getById(id);
		if(user!=null) {
		response.setData(user);
		} else {
			response.setMessageError("Không tồn tại tài khoản này!");
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
			Long mesageSQL=	veDao.spUpdateVe(id, wrapper.getSdt(), wrapper.getEmail());
			if(mesageSQL==1){
				response.setMessageError("Lỗi chỉnh sửa dữ liệu");
			} else {
				khachHangdao.update(khachHang);
				response.setData(khachHang);
			}
			return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
		}
	}

	
	//Lấy tất cả người dùng
	@RequestMapping(value ="/get-all-user", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> spGetAllUser(){
		BaseResponse response= new BaseResponse();
		List<UserRoleDataModel> list =khachHangdao.spGetAllUser();
		response.setData(list);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/get-detail-point/{id}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> spGetAllUser(@PathVariable(name="id")int id){
		BaseResponse response= new BaseResponse();
		UserPointDataModel user =pointDao.spGetDetailPointCustomer(id);
		UserPointResponse data =new UserPointResponse(user);
		response.setData(data);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	}
}
