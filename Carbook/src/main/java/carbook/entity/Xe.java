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
	
	@Column(name="floor")
	private int floor;
	
	@Column(name="roww")
	private int row;
	
	@Column(name="srow")
	private int srow;
	
	@Column(name="slot_count")
	private int slotCount;
	
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

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getSrow() {
		return srow;
	}

	public void setSrow(int srow) {
		this.srow = srow;
	}

	public int getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(int slotCount) {
		this.slotCount = slotCount;
	}
	
	
}
