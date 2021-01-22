package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xe")
public class Xe {

	@Id
	private int id;
	
	@Column(name="ten_xe")
	private String tenXe;
	
	@Column(name="hang_xe")
	private String hangXe;
	
	@Column(name="tuyen_san_sang_id")
	private int tuyenSanSangId;
	
	@Column(name="tuyen_off_id")
	private int tuyenOffId;
	
	@Column(name="gio_chay")
	private int gioChay;
	
	@Column(name="trang_thai")
	private int trangThai;
	
	public Xe() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenXe() {
		return tenXe;
	}

	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}

	public String getHangXe() {
		return hangXe;
	}

	public void setHangXe(String hangXe) {
		this.hangXe = hangXe;
	}

	public int getTuyenSanSangId() {
		return tuyenSanSangId;
	}

	public void setTuyenSanSangId(int tuyenSanSangId) {
		this.tuyenSanSangId = tuyenSanSangId;
	}

	public int getTuyenOffId() {
		return tuyenOffId;
	}

	public void setTuyenOffId(int tuyenOffId) {
		this.tuyenOffId = tuyenOffId;
	}

	public int getGioChay() {
		return gioChay;
	}

	public void setGioChay(int gioChay) {
		this.gioChay = gioChay;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
