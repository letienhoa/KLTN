package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserRoleDataModel {

	@Id
	private int id;
	
	@JsonProperty("tai_khoan")
	@Column(name= "tai_khoan")
	private String taiKhoan;
	
	@JsonProperty("mat_khau")
	@Column(name= "mat_khau")
	private String Password;
	
	@JsonProperty("ten_kh")
	@Column(name= "ten_kh")
	private String tenKh;
	
	private String email;
	
	@JsonProperty("sdt")
	@Column(name= "sdt")
	private String sdt;
	
	private String cmnd;
	
	@JsonProperty("dia_chi")
	@Column(name= "dia_chi")
	private String diaChi;
	
	@JsonProperty("thanh_pho")
	@Column(name= "thanh_pho")
	private String thanhPho;
	
	@JsonProperty("quan_huyen")
	@Column(name= "quan_huyen")
	private String quanHuyen;
	
	private int confirm;
	
	private int status;
	
	private String role;
	
	public UserRoleDataModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTenKh() {
		return tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}

	public String getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
