package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserPointDataModel {

	
	@Id
	private int id;
	
	@Column(name="cap_do")
	private String capDo;
	
	@Column(name="tong_diem")
	private int tongDiem;
	
	@Column(name="tich_luy_kha_dung")
	private int diemTichLuyKhaDung;

	
	@Column(name="tien_tich_luy")
	private int tongTienTichLuy;
	
	public UserPointDataModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCapDo() {
		return capDo;
	}

	public void setCapDo(String capDo) {
		this.capDo = capDo;
	}

	public int getTongTienTichLuy() {
		return tongTienTichLuy;
	}

	public void setTongTienTichLuy(int tongTienTichLuy) {
		this.tongTienTichLuy = tongTienTichLuy;
	}

	public int getTongDiem() {
		return tongDiem;
	}

	public void setTongDiem(int tongDiem) {
		this.tongDiem = tongDiem;
	}

	public int getDiemTichLuyKhaDung() {
		return diemTichLuyKhaDung;
	}

	public void setDiemTichLuyKhaDung(int diemTichLuyKhaDung) {
		this.diemTichLuyKhaDung = diemTichLuyKhaDung;
	}
	
	
}
