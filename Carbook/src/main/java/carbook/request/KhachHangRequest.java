package carbook.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class KhachHangRequest {

	@JsonProperty("tai_khoan")
	private String taiKhoan;
	
	@JsonProperty("mat_khau")
	private String matKhau;
	
	private String email;
	
	private String ten;
	
	private String sdt;
	
	private String cmnd;
	
	@JsonProperty( "dia_chi")
	private String diaChi;
	
	@JsonProperty("thanh_pho")
	private String thanhPho;
	
	@JsonProperty("quan_huyen")
	private String quanHuyen;
	
	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
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

	
}
