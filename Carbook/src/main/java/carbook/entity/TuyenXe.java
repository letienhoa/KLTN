package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="tuyen_xe")
public class TuyenXe {

	@Id
	@Column(name="id")
	private int id;
	
	@JsonProperty("diem_di_id")
	@Column(name="diem_di_id")
	private int diemDiId;
	
	@JsonProperty("diem_toi_id")
	@Column(name="diem_toi_id")
	private int diemToiId;
	
	@JsonProperty("khoang_cach")
	@Column(name="khoang_cach")
	private int khoangCach;
	
	@JsonProperty("gia_ca")
	@Column(name="gia_ca")
	private int giaCa;
	
	@JsonProperty("khoang_thoi_gian")
	@Column(name="khoang_thoi_gian")
	private int khoangThoiGian;
	
	private Integer status;
	
	public TuyenXe() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiemDiId() {
		return diemDiId;
	}

	public void setDiemDiId(int diemDi) {
		this.diemDiId = diemDi;
	}

	public int getDiemToiId() {
		return diemToiId;
	}

	public void setDiemToiId(int diemToi) {
		this.diemToiId = diemToi;
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
	
	
}
