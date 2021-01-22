package carbook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "giuong")
public class Giuong {

	@Id
	private int id;
	
	@Column(name="stt")
	private int stt;
	
	@Column(name="id_tuyen_xe")
	private int idTuyenXe;
	
	@Column(name="id_ve")
	private int idVe;
	
	@Column(name="ngay")
	private Date ngay;
	
	@Column(name="gio")
	private int gio;
	
	@Column(name="trang_thai")
	private int trangThai;
	
	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public Giuong() {
		
	}
	
	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public int getGio() {
		return gio;
	}

	public void setGio(int gio) {
		this.gio = gio;
	}

	public int getIdVe() {
		return idVe;
	}

	public void setIdVe(int idVe) {
		this.idVe = idVe;
	}
	
	
}
