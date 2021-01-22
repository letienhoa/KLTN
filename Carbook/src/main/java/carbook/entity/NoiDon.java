package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="noi_don")
public class NoiDon {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="id_ben_xe")
	private int idBenXe;
	
	@Column(name="dia_chi")
	private String diaChi;
	
	
	public NoiDon() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdBenXe() {
		return idBenXe;
	}


	public void setIdBenXe(int idBenXe) {
		this.idBenXe = idBenXe;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
}
