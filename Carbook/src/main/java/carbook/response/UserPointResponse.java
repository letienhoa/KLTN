package carbook.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.UserPointDataModel;

public class UserPointResponse {

	private int id;
	
	@JsonProperty("cap_do")
	
	private String capDo;
	
	@JsonProperty("tien_tich_luy")
	private int tongTienTichLuy;
	
	@JsonProperty("tong_diem")
	private int tongDiem;
	
	@JsonProperty("diem_tich_luy_kha_dung")
	private int diemTichLuyKhaDung;
	
	public UserPointResponse() {
		
	}
	
	public UserPointResponse(UserPointDataModel entity) {
		this.id=entity.getId();
		this.capDo=entity.getCapDo();
		this.diemTichLuyKhaDung=entity.getDiemTichLuyKhaDung();
		this.tongDiem=entity.getTongDiem();
		this.tongTienTichLuy=entity.getTongTienTichLuy(); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCapDo() {
		return capDo;
	}

	public void setCapDo(String capDo) {
		this.capDo = capDo;
	}

	public int getTongTienTichLuy() {
		return tongTienTichLuy;
	}

	public void setTongTienTichLuy(int tongTienTichLuy) {
		this.tongTienTichLuy = tongTienTichLuy;
	}

	public int getTongDiem() {
		return tongDiem;
	}

	public void setTongDiem(int tongDiem) {
		this.tongDiem = tongDiem;
	}

	public int getDiemTichLuyKhaDung() {
		return diemTichLuyKhaDung;
	}

	public void setDiemTichLuyKhaDung(int diemTichLuyKhaDung) {
		this.diemTichLuyKhaDung = diemTichLuyKhaDung;
	}
	
}
