package carbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TramDungRequest {

	private int id;
	
	@JsonProperty("ten_tram")
	private String tenTram;
	
	@JsonProperty("dia_chi")
	private String diaChi;
	
	public TramDungRequest() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenTram() {
		return tenTram;
	}

	public void setTenTram(String tenTram) {
		this.tenTram = tenTram;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
}
