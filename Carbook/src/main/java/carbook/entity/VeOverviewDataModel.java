package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class VeOverviewDataModel {

	@Id
	private int id;

	@JsonProperty("id_tuyen_xe")
	@Column(name="id_tuyen_xe")
	private int idTuyenXe;
	
	@JsonProperty("so_luong_ve")
	@Column(name="so_luong_ve")
	private int soLuongVe;
	
	@JsonProperty("gio_chay")
	@Column(name="gio_chay")
	private int gioChay;

	public VeOverviewDataModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public int getSoLuongVe() {
		return soLuongVe;
	}

	public void setSoLuongVe(int soLuongVe) {
		this.soLuongVe = soLuongVe;
	}

	public int getGioChay() {
		return gioChay;
	}

	public void setGioChay(int gioChay) {
		this.gioChay = gioChay;
	}
	
	
}
