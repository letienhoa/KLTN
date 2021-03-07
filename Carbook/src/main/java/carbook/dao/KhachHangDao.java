package carbook.dao;

import java.util.List;

import carbook.entity.BaseEntity;
import carbook.entity.User;
import carbook.entity.UserRoleDataModel;
import carbook.entity.UserToken;

public interface KhachHangDao  {

	 BaseEntity create(BaseEntity entity);
	
	 void update(BaseEntity entity);
	
	 List<User> findAll();
	
	 User getById(int id);
	
	 UserToken getByTaiKhoanMatKhau(String taiKhoan,String matKhau);
	
	 UserToken loadUserByUsername(String username);
	
	 User findByUsername(String name);
	
	 User findByUsernameAndPassword(String name,String mk);
	
	 List<UserRoleDataModel> spGetAllUser();
}
