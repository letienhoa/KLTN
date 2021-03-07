package carbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BenRequest {

	
	@JsonProperty("ten_ben")
	private String tenBen;

	@JsonProperty("dia_chi")
	private String diaChi;
	
	@JsonProperty("thanh_pho")
	private String thanhPho;
	
	private String picture;
	

	public String getTenBen() {
		return tenBen;
	}
	
	public void setTenBen(String tenBen) {
		this.tenBen = tenBen;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
