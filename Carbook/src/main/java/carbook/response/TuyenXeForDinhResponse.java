package carbook.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TuyenXeForDinhResponse {

	private int id;
	
	@JsonProperty("diem_di")
	private String diemDi;
	
	@JsonProperty("diem_toi")
	private String diemToi;
	
	@JsonProperty("diem_di_id")
	private Integer diemDiId;
	
	@JsonProperty("diem_toi_id")
	private Integer diemToiId;
	
	@JsonProperty("khoang_cach")
	private int khoangCach;
	
	@JsonProperty("gia_ca")
	private int giaCa;
	
	@JsonProperty("khoang_thoi_gian")
	private int khoangThoiGian;
	
	private Integer status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiemDi() {
		return diemDi;
	}

	public void setDiemDi(String diemDi) {
		this.diemDi = diemDi;
	}

	public String getDiemToi() {
		return diemToi;
	}

	public void setDiemToi(String diemToi) {
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

	public int getKhoangThoiGian() {
		return khoangThoiGian;
	}

	public void setKhoangThoiGian(int khoangThoiGian) {
		this.khoangThoiGian = khoangThoiGian;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDiemDiId() {
		return diemDiId;
	}

	public void setDiemDiId(Integer diemDiId) {
		this.diemDiId = diemDiId;
	}

	public Integer getDiemToiId() {
		return diemToiId;
	}

	public void setDiemToiId(Integer diemToiId) {
		this.diemToiId = diemToiId;
	}
	
	
}
