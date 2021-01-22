package carbook.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.Ben;
import carbook.entity.TuyenXe;

public class TuyenXeCustomerResponse {

	private int id;
	
	@JsonProperty("diem_di_id")
	private int diemDi;
	
	@JsonProperty("diem_toi_id")
	private int diemToi;
	
	@JsonProperty("khoang_cach")
	private int khoangCach;
	
	@JsonProperty("gia_ca")
	private int giaCa;
	
	private String picture;
	
	public TuyenXeCustomerResponse() {
		
	}
	
	public TuyenXeCustomerResponse(Ben ben,TuyenXe tuyenXe) {
		this.id= tuyenXe.getId();
		this.diemDi= tuyenXe.getDiemDiId();
		this.diemToi=tuyenXe.getDiemToiId();
		this.giaCa=tuyenXe.getGiaCa();
		this.khoangCach=tuyenXe.getKhoangCach();
		this.picture= ben.getPicture();
	}



	public int getDiemDi() {
		return diemDi;
	}

	public void setDiemDi(int diemDi) {
		this.diemDi = diemDi;
	}

	public int getDiemToi() {
		return diemToi;
	}

	public void setDiemToi(int diemToi) {
		this.diemToi = diemToi;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
