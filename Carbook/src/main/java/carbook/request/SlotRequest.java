package carbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlotRequest {
	
	private String ten;
	
	@JsonProperty("so_ghe")
	private Integer soGhe;

	@JsonProperty("noi_xuong")
	private String noiXuong;
	
	@JsonProperty("dia_chi")
	private String diaChi;
	
	public SlotRequest() {
		
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Integer getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(Integer soGhe) {
		this.soGhe = soGhe;
	}

	public String getNoiXuong() {
		return noiXuong;
	}

	public void setNoiXuong(String noiXuong) {
		this.noiXuong = noiXuong;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
}

