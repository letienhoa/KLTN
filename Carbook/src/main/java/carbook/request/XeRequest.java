package carbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XeRequest {

	@JsonProperty("ten_xe")
	private String tenXe;
	@JsonProperty("hang_xe")
	private String hangXe;
	@JsonProperty("tuyen_san_sang_id")
	private int tuyenSanSangId;
	@JsonProperty("tuyen_off_id")
	private int tuyenOffId;
	@JsonProperty("gio_chay")
	private int gioChay;
	@JsonProperty("floor")
	private int floor;
	@JsonProperty("row")
	private int row;
	@JsonProperty("srow")
	private int srow;
	@JsonProperty("slot_count")
	private int slotCount;
	
	public XeRequest() {

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
