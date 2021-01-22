package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class TuyenXeModelData {

	@Id
	private int id;
	
	@JsonProperty("ben_xe_di")
	@Column(name="ben_xe_di")
	private String benXeDi;
	
	@JsonProperty("ben_xe_di_id")
	@Column(name="ben_xe_di_id")
	private int benXeDiId;
	
	@JsonProperty("ben_xe_toi")
	@Column(name="ben_xe_toi")
	private String benXeToi;
	
	@JsonProperty("ben_xe_toi_id")
	@Column(name="ben_xe_toi_id")
	private int benXeToiId;
	
	@JsonProperty("gia_ca")
	@Column(name="gia_ca")
	private int giaCa;
	
	@JsonProperty("khoang_cach")
	@Column(name="khoang_cach")
	private int khoangCach;
	
	@JsonProperty("khoang_thoi_gian")
	@Column(name="khoang_thoi_gian")
	private int khoangThoiGian;
	
	@JsonProperty("trang_thai")
	@Column(name="status")
	private int trangThai;
	
	public TuyenXeModelData() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBenXeDi() {
		return benXeDi;
	}

	public void setBenXeDi(String benXeDi) {
		this.benXeDi = benXeDi;
	}

	public String getBenXeToi() {
		return benXeToi;
	}

	public void setBenXeToi(String benXeToi) {
		this.benXeToi = benXeToi;
	}

	public int getGiaCa() {
		return giaCa;
	}

	public void setGiaCa(int giaCa) {
		this.giaCa = giaCa;
	}

	public int getKhoangCach() {
		return khoangCach;
	}

	public void setKhoangCach(int khoangCach) {
		this.khoangCach = khoangCach;
	}

	public int getKhoangThoiGian() {
		return khoangThoiGian;
	}

	public void setKhoangThoiGian(int khoangThoiGian) {
		this.khoangThoiGian = khoangThoiGian;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
