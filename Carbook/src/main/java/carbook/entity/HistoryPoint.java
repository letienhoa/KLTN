package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="history_point")
public class HistoryPoint {
	@Id
	private int id;
	
	@Column(name="id_khach_hang")
	private int idKhachHang;
	
	@Column(name="diem_cong")
	private int diemCong;
	
	@Column(name="diem_tru")
	private int diemTru;
	
	@Column(name="previous_point")
	private int previousPoint;
	
	private String note;
	
	public HistoryPoint() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdKhachHang() {
		return idKhachHang;
	}

	public void setIdKhachHang(int idKhachHang) {
		this.idKhachHang = idKhachHang;
	}

	public int getDiemCong() {
		return diemCong;
	}

	public void setDiemCong(int diemCong) {
		this.diemCong = diemCong;
	}

	public int getDiemTru() {
		return diemTru;
	}

	public void setDiemTru(int diemTru) {
		this.diemTru = diemTru;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPreviousPoint() {
		return previousPoint;
	}

	public void setPreviousPoint(int previousPoint) {
		this.previousPoint = previousPoint;
	}
	
}
