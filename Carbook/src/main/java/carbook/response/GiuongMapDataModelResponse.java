package carbook.response;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiuongMapDataModelResponse {

	
	private int id;

	@JsonProperty("id_ve")
	private int idVe;
	
	private String stt;
	
	private String ten;
	
	@JsonProperty("dia_chi")
	private String diaChi;
	
	@JsonProperty("noi_xuong")
	private String noiXuong;
	
	public GiuongMapDataModelResponse() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdVe() {
		return idVe;
	}

	public void setIdVe(int idVe) {
		this.idVe = idVe;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getNoiXuong() {
		return noiXuong;
	}

	public void setNoiXuong(String noiXuong) {
		this.noiXuong = noiXuong;
	}
	
	
}
