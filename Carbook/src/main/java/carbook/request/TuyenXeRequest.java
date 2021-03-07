package carbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TuyenXeRequest {

	@JsonProperty("diem_di_id")
	private int diemDiId;
	
	@JsonProperty("diem_toi_id")
	private int diemToiId;
	
	@JsonProperty("khoang_cach")
	private int khoangCach;
	
	@JsonProperty("gia_ca")
	private int giaCa;
	
	@JsonProperty("thoi_gian_hanh_trinh")
	private int thoiGianChay;
	
	public TuyenXeRequest() {
		
	}


	public int getDiemDiId() {
		return diemDiId;
	}

	public void setDiemDiId(int diemDiId) {
		this.diemDiId = diemDiId;
	}

	public int getDiemToiId() {
		return diemToiId;
	}

	public void setDiemToiId(int diemToiId) {
		this.diemToiId = diemToiId;
	}


	public int getKhoangCach() {
		return khoangCach;
	}

	public void setKhoangCach(int khoangCach) {
		this.khoangCach = khoangCach;
	}

	public int getGiaCa() {
		return giaCa;
	}

	public void setGiaCa(int giaCa) {
		this.giaCa = giaCa;
	}

	public int getThoiGianChay() {
		return thoiGianChay;
	}

	public void setThoiGianChay(int thoiGianChay) {
		this.thoiGianChay = thoiGianChay;
	}
	
	
}
