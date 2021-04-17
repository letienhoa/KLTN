package carbook.request;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeRequest {

	@JsonProperty("gio_chay")
	private int gioChay;
	
	@JsonProperty("gio_ket_thuc")
	private int gioKetThuc;
	
	@JsonProperty("id_tuyen_xe")
	private int idTuyenXe;
	

	private String sdt;
	

	private String email;
	

	private String date;
	
	@JsonProperty("gia_ve")
	private Double giaVe;
	
	@JsonProperty("paypal_id")
	private String paypalId;
	
	@JsonProperty("vnpay_id")
	private String vnpayId;
	
	@JsonProperty("diem_xuong")
	private String diemXuong;
	
	@JsonProperty("slot")
	private List<SlotRequest> Slot =new ArrayList<SlotRequest>();
	
	public VeRequest() {
		
	}

	public int getGioChay() {
		return gioChay;
	}

	public void setGioChay(int gioChay) {
		this.gioChay = gioChay;
	}

	public int getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(int gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(Double giaVe) {
		this.giaVe = giaVe;
	}

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	public String getDiemXuong() {
		return diemXuong;
	}

	public void setDiemXuong(String diemXuong) {
		this.diemXuong = diemXuong;
	}

	public void setSlot(List<SlotRequest> slot) {
		Slot = slot;
	}

	public List<SlotRequest> getSlot() {
		return Slot;
	}

	public String getVnpayId() {
		return vnpayId;
	}

	public void setVnpayId(String vnpayId) {
		this.vnpayId = vnpayId;
	}
	
	
}
