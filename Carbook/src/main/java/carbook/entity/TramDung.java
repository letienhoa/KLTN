package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tram_dung")
public class TramDung {

	@Id
	private int id;
	
	@Column(name="ten_tram")
	private String tenTram;
	
	@Column(name="dia_chi")
	private String diaChi;
	
	public TramDung() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenTram() {
		return tenTram;
	}

	public void setTenTram(String tenTram) {
		this.tenTram = tenTram;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
}
