package carbook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import carbook.request.TuyenOffRequest;
import carbook.service.UtilsService;

@Entity
@Table(name="tuyen_off")
public class TuyenOff {

	@Id
	private int id;
	
	@Column(name="id_tuyen_xe")
	private int idTuyenXe;
	
	private int gio;
	
	private Date ngay;
	
	public TuyenOff() {
		
	}
	
	public TuyenOff(TuyenOffRequest e) {
		this.idTuyenXe =e.getIdTuyenXe();
		this.gio = e.getGio();
		this.ngay =UtilsService.changeStringToDate(e.getNgay());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public int getGio() {
		return gio;
	}

	public void setGio(int gio) {
		this.gio = gio;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
}
