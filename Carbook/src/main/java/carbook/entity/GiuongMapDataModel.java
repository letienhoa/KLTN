package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GiuongMapDataModel {

	@Id
	private int id;

	@Column(name="id_ve")
	private int idVe;
	
	private int stt;
	
	private String ten;
	
	@Column(name="dia_chi")
	private String diaChi;
	
	@Column(name="noi_xuong")
	private String noiXuong;
	
	public GiuongMapDataModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdVe() {
		return idVe;
	}

	public void setIdVe(int idVe) {
		this.idVe = idVe;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getNoiXuong() {
		return noiXuong;
	}

	public void setNoiXuong(String noiXuong) {
		this.noiXuong = noiXuong;
	}
	
	
}
