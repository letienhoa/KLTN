package carbook.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.service.UtilsService;


public class VeForCustomerByCodeDataModelFinal {


	private int id;
	
	@JsonProperty("tuyen_xe")
	private String tuyenXe;
	
	@JsonProperty("ngay_dat")
	private String ngayDat;
	
	@JsonProperty("gio_dat")
	private String gioDat;
	
	@JsonProperty("gio_chay")
	private String gioChay;
	
	@JsonProperty("so_dien_thoai")
	private String sdt;
	

	private String email;
	
	@JsonProperty("ma_code")
	private String code;
	
	@JsonProperty("gia_ve")
	private int giaVe;
	
	@JsonProperty("ngay_chay")
	private String ngayChay;
	
	@JsonProperty("diem_xuong")
	private String diemXuong;
	
	private List<String> slots;
	
	public VeForCustomerByCodeDataModelFinal() {
		this.slots =new ArrayList<String>();
	}
	
	public VeForCustomerByCodeDataModelFinal(VeForCustomerByCodeDataModel e) {
		this.slots =new ArrayList<String>();
		this.id=e.getId();
		this.code=e.getCode();
		this.email=e.getEmail();
		this.giaVe=e.getGiaVe();
		this.gioChay=String.valueOf(e.getGioChay())+"h";
		this.ngayChay=UtilsService.getDateFormatVN(e.getNgayChay());
		this.sdt=e.getSdt();
		this.tuyenXe=e.getTuyenXe();
		this.diemXuong= e.getDiemXuong();
		this.ngayDat=UtilsService.getDateFormatVN(e.getNgayDat());
		this.gioDat= UtilsService.getHour(e.getNgayDat())+"h";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTuyenXe() {
		return tuyenXe;
	}

	public void setTuyenXe(String tuyenXe) {
		this.tuyenXe = tuyenXe;
	}

	public String getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getGioChay() {
		return gioChay;
	}

	public void setGioChay(String gioChay) {
		this.gioChay = gioChay;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(int giaVe) {
		this.giaVe = giaVe;
	}

	public String getNgayChay() {
		return ngayChay;
	}

	public void setNgayChay(String ngayChay) {
		this.ngayChay = ngayChay;
	}

	public List<String> getSlots() {
		return slots;
	}

	public void setSlots(List<String> slots) {
		this.slots = slots;
	}

	public String getGioDat() {
		return gioDat;
	}

	public void setGioDat(String gioDat) {
		this.gioDat = gioDat;
	}

	public String getDiemXuong() {
		return diemXuong;
	}

	public void setDiemXuong(String diemXuong) {
		this.diemXuong = diemXuong;
	}
	
	
}
