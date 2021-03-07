package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ben")
public class Ben {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="ten_ben")
	private String tenBen;
	
	@Column(name="dia_chi")
	private String diaChi;
	
	@Column(name="thanh_pho")
	private String thanhPho;
	
	@Column(name="picture")
	private String picture;
	
	public Ben() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenBen() {
		return tenBen;
	}

	public void setTenBen(String tenBen) {
		this.tenBen = tenBen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}
	
	
}
