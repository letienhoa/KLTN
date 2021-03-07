package carbook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VeExcelDataModel {

	@Id
	private int id;
	
	@Column(name="tuyen_xe")
	private String tuyenXe;
	
	@Column(name="gio_chay")
	private int gioChay;
	
	@Column(name="gio_ket_thuc")
	private int gioKetThuc;
	
	@Column(name="sdt_khach_hang")
	private String sdt;
	

	private String email;
	

	private String code;
	
	@Column(name="gia_ve")
	private int giaVe;
	
	@Column(name="")
	private int point;
	
	@Column(name="")
	private Date date;
	
	@Column(name="diem_xuong")
	private String diemXuong;
	
	public VeExcelDataModel() {
		
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiemXuong() {
		return diemXuong;
	}

	public void setDiemXuong(String diemXuong) {
		this.diemXuong = diemXuong;
	}
	
	
}
