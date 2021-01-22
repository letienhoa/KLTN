package carbook.response;


import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.VeExcelDataModel;
import carbook.service.UtilsService;

public class VeExcelResponse {

	@Id
	private int id;
	
	@JsonProperty("tuyen_xe")
	private String tuyenXe;
	
	@JsonProperty("gio_chay")
	private int gioChay;
	
	@JsonProperty("gio_ket_thuc")
	private int gioKetThuc;
	
	@JsonProperty("sdt_khach_hang")
	private String sdt;
	

	private String email;
	

	private String code;
	
	@JsonProperty("gia_ve")
	private int giaVe;
	
	private int point;
	

	private String date;
	
	public VeExcelResponse() {
		
	}

	public VeExcelResponse(VeExcelDataModel e) {
		this.id= e.getId();
		this.tuyenXe=e.getTuyenXe();
		this.gioChay=e.getGioChay();
		this.gioKetThuc=e.getGioKetThuc();
		this.sdt=e.getSdt();
		this.email=e.getEmail();
		this.code=e.getCode();
		this.giaVe=e.getGiaVe();
		this.point=e.getPoint();
		this.date=UtilsService.getDateFormatVN(e.getDate());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTuyenXe() {
		return tuyenXe;
	}

	public void setTuyenXe(String tuyenXe) {
		this.tuyenXe = tuyenXe;
	}

	public int getGioChay() {
		return gioChay;
	}

	public void setGioChay(int gioChay) {
		this.gioChay = gioChay;
	}

	public int getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(int gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(int giaVe) {
		this.giaVe = giaVe;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<VeExcelResponse> mapToList(List<VeExcelDataModel> baseEntity){
		List<VeExcelResponse> list= baseEntity	.stream()
												.map(x->new VeExcelResponse(x))
												.collect(Collectors.toList());
		return list;
	}
	
}
