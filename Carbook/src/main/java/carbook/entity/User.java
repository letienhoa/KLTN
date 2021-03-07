package carbook.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "roles", "authorities" })
@Entity
@Table(name="user")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name= "tai_khoan")
	private String taiKhoan;
	
	@Column(name= "mat_khau")
	private String Password;
	
	@Column(name= "ten_kh")
	private String tenKh;
	
	private String email;
	
	@Column(name= "sdt")
	private String sdt;
	
	private String cmnd;
	
	@Column(name= "dia_chi")
	private String diaChi;
	
	@Column(name= "thanh_pho")
	private String thanhPho;
	
	@Column(name= "quan_huyen")
	private String quanHuyen;
	
	private int confirm;
	
	private int status;
	
	@Column(name= "diem_tich_luy")
	private int diemTichLuy;
	
	private int discount;
	
	@Column(name= "tong_diem")
	private int tongDiem;
	
	public User() {
		
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

	public void setPassword(String matKhau) {
		this.Password = matKhau;
	}

	public String getTenKh() {
		return tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getTongDiem() {
		return tongDiem;
	}

	public void setTongDiem(int tongDiem) {
		this.tongDiem = tongDiem;
	}

	
}
