package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VeThongKeModelDate {
	@Id
	@Column(name="id_tuyen_xe")
	private int idTuyenXe;
	
	@Column(name="doanh_thu")
	private double doanhThu;
	
	@Column(name="tong_ve")
	private int tongVe;
	
	@Column(name="time")
	private String time;
	
	public VeThongKeModelDate() {
		
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public double getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}

	public int getTongVe() {
		return tongVe;
	}

	public void setTongVe(int tongVe) {
		this.tongVe = tongVe;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
