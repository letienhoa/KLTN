package carbook.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ThongTinVeDataModel {

	@Id
	private int id;
	
	@Column(name="ten_kh")
	private String tenKh;
	
	@Column(name="sdt_khach_hang")
	private String sdt;
	
	private String code;
	
	@Column(name="diem_xuong")
	private String diemXuong;
	
	
	public ThongTinVeDataModel() {
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenKh() {
		return tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiemXuong() {
		return diemXuong;
	}

	public void setDiemXuong(String diemXuong) {
		this.diemXuong = diemXuong;
	}
 
	
}
