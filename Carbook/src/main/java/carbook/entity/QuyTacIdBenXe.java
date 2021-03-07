package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="quy_tac_id_ben_xe")
@Entity
public class QuyTacIdBenXe {

	@Id
	private int id;
	
	@Column(name="thanh_pho")
	private String thanhPho;
	
	public QuyTacIdBenXe() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}
	
	
}
