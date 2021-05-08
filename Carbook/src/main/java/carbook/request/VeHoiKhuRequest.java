package carbook.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeHoiKhuRequest {

	//Phần 2
	
	
	@JsonProperty("gio_chay2")
	private Integer gioChay2;
	
	@JsonProperty("gio_ket_thuc2")
	private Integer gioKetThuc2;
	
	@JsonProperty("id_tuyen_xe2")
	private Integer idTuyenXe2;
	
	

	private String date2;
	
	@JsonProperty("gia_ve2")
	private Double giaVe2;
	
	@JsonProperty("diem_xuong2")
	private String diemXuong2;

	//Phần 1
	@JsonProperty("gio_chay")
	private Integer gioChay;
	
	@JsonProperty("gio_ket_thuc")
	private Integer gioKetThuc;
	
	@JsonProperty("id_tuyen_xe")
	private Integer idTuyenXe;
	
	@JsonProperty("diem_xuong")
	private String diemXuong;

	private String sdt;
	

	private String email;
	

	private String date;
	
	@JsonProperty("gia_ve")
	private Double giaVe;
	
	@JsonProperty("slot")
	private List<SlotRequest> slot;
	
	@JsonProperty("slot2")
	private List<SlotRequest> slot2;
	
	@JsonProperty("paypal_id")
	private String paypalId;
	
	@JsonProperty("vnpay_id")
	private String vnpayId;
	
	public VeHoiKhuRequest() {
		
	}

	public Integer getGioChay2() {
		return gioChay2;
	}

	public void setGioChay2(Integer gioChay2) {
		this.gioChay2 = gioChay2;
	}

	public Integer getGioKetThuc2() {
		return gioKetThuc2;
	}

	public void setGioKetThuc2(Integer gioKetThuc2) {
		this.gioKetThuc2 = gioKetThuc2;
	}

	public Integer getIdTuyenXe2() {
		return idTuyenXe2;
	}

	public void setIdTuyenXe2(Integer idTuyenXe2) {
		this.idTuyenXe2 = idTuyenXe2;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public Double getGiaVe2() {
		return giaVe2;
	}

	public void setGiaVe2(Double giaVe2) {
		this.giaVe2 = giaVe2;
	}

	public Integer getGioChay() {
		return gioChay;
	}

	public void setGioChay(Integer gioChay) {
		this.gioChay = gioChay;
	}

	public Integer getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(Integer gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public Integer getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(Integer idTuyenXe) {
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

	public String getDiemXuong2() {
		return diemXuong2;
	}

	public void setDiemXuong2(String diemXuong2) {
		this.diemXuong2 = diemXuong2;
	}

	public String getDiemXuong() {
		return diemXuong;
	}

	public void setDiemXuong(String diemXuong) {
		this.diemXuong = diemXuong;
	}

	public List<SlotRequest> getSlot() {
		return slot;
	}

	public void setSlot(List<SlotRequest> slot) {
		this.slot = slot;
	}

	public List<SlotRequest> getSlot2() {
		return slot2;
	}

	public void setSlot2(List<SlotRequest> slot2) {
		this.slot2 = slot2;
	}

	public String getVnpayId() {
		return vnpayId;
	}

	public void setVnpayId(String vnpayId) {
		this.vnpayId = vnpayId;
	}
	
	
	
}
