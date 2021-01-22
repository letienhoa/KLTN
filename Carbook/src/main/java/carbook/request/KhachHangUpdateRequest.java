package carbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KhachHangUpdateRequest {
	
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
	
	public KhachHangUpdateRequest() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
}
