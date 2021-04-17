package carbook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ve")
public class Ve extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "gio_chay")
	private String gioChay;

	@Column(name = "gio_ket_thuc")
	private String gioKetThuc;

	@Column(name = "id_tuyen_xe")
	private int idTuyenXe;

	@Column(name = "sdt_khach_hang")
	private String sdtKhachHang;

	@Column(name = "gia_ve")
	private int giaVe;

	private int point;

	@Column(name = "date")
	private Date date;

	@Column(name = "trang_thai")
	private int trangThai;

	private String email;

	private String code;

	@Column(name = "diem_xuong")
	private String diemXuong;

	@Column(name = "paypal_id")
	private String paypalId;
	
	@Column(name= "vnpay_id")
	private String vnPayId;

	public Ve() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGioChay() {
		return gioChay;
	}

	public void setGioChay(String gioChay) {
		this.gioChay = gioChay;
	}

	public String getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(String gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public String getSdtKhachHang() {
		return sdtKhachHang;
	}

	public void setSdtKhachHang(String sdtKhachHang) {
		this.sdtKhachHang = sdtKhachHang;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
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

	public String getVnPayId() {
		return vnPayId;
	}

	public void setVnPayId(String vnPayId) {
		this.vnPayId = vnPayId;
	}

	
}
