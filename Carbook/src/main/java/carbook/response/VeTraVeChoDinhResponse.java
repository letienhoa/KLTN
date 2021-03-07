package carbook.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeTraVeChoDinhResponse {

	@JsonProperty("tuyen_xe")
	private String tuyenXe;
	
	@JsonProperty("tong_ve")
	private int tongVe;
	
	@JsonProperty("gio_chay")
	private String gioChay;
	
	@JsonProperty("ngay_chay")
	private String date;
	
	@JsonProperty("danh_sach_ve")
	private List<VeExcelVer1Response> listVe;
	
	public VeTraVeChoDinhResponse() {
		this.listVe= new ArrayList<VeExcelVer1Response>();
	}

	public String getTuyenXe() {
		return tuyenXe;
	}

	public void setTuyenXe(String tuyenXe) {
		this.tuyenXe = tuyenXe;
	}

	public int getTongVe() {
		return tongVe;
	}

	public void setTongVe(int tongVe) {
		this.tongVe = tongVe;
	}

	public String getGioChay() {
		return gioChay;
	}

	public void setGioChay(String gioChay) {
		this.gioChay = gioChay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<VeExcelVer1Response> getListVe() {
		return listVe;
	}

	public void setListVe(List<VeExcelVer1Response> listVe) {
		this.listVe = listVe;
	}
	
	
	
	
}
