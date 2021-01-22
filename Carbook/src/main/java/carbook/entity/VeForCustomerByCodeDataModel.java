package carbook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class VeForCustomerByCodeDataModel {
	
	@Id
	private int id;
	
	@Column(name="id_tuyen_xe")
	private int idTuyenXe;
	
	@Column(name="tuyen_xe")
	private String tuyenXe;
	
	@Column(name="ngay_dat")
	private Date ngayDat;
	
	@Column(name="gio_chay")
	private int gioChay;
	

	private String sdt;
	

	private String email;
	

	private String code;
	
	@Column(name="diem_xuong")
	private String diemXuong;
	
	@Column(name="gia_ve")
	private int giaVe;
	
	@Column(name="ngay_chay")
	private Date ngayChay;

	public VeForCustomerByCodeDataModel() {
		
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

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public int getGioChay() {
		return gioChay;
	}

	public void setGioChay(int gioChay) {
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

	public Date getNgayChay() {
		return ngayChay;
	}

	public void setNgayChay(Date ngayChay) {
		this.ngayChay = ngayChay;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public String getDiemXuong() {
		return diemXuong;
	}

	public void setDiemXuong(String diemXuong) {
		this.diemXuong = diemXuong;
	}
	
	
}
