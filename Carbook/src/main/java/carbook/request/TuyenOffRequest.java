package carbook.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TuyenOffRequest {

	
	@JsonProperty("id_tuyen_xe")
	private int idTuyenXe;
	
	private int gio;
	
	private String ngay;
	
	public TuyenOffRequest() {
		
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public int getGio() {
		return gio;
	}

	public void setGio(int gio) {
		this.gio = gio;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	
}
