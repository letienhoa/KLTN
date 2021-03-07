package carbook.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserToken {

	private String taiKhoan;
	
	private String Password;
	
	private String[] roles;
	
	private int id;
	
	private String name;
	
	private String email;
	
	private int discount;
	
	public UserToken() {
		
	}
	
	public UserToken(User entity) {
		this.taiKhoan = entity.getTaiKhoan();
		this.Password =entity.getPassword();
		this.id = entity.getId();
		this.name = entity.getTenKh();
		this.email = entity.getEmail();
		this.discount=entity.getDiscount();
	}
	
	
	public String getTaiKhoan() {
		return taiKhoan;
	}



	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}



	public String[] getRoles() {
		return roles;
	}



	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (String role : roles) {
	      authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;
	  }
	
}
