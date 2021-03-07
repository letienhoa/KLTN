package carbook.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.VeCustomerDataModel;
import carbook.service.UtilsService;

public class VeCustomerDataModelResponse {

	private int id;
	
	@JsonProperty("gio_chay")
	private int gioChay;
	
	@JsonProperty("ngay_chay")
	private String ngayChay;
	
	@JsonProperty("count")
	private int count;
	
	@JsonProperty("gia_ve")
	private int giaVe;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("id_tuyen_xe")
	private int idTuyenXe;
	
	@JsonProperty("ben_di")
	private String benDi;
	
	@JsonProperty("ben_toi")
	private String benToi;
	
	@JsonProperty("ngay_dat")
	private String ngayDat;
	
	@JsonProperty("ngay_thanh_toan")
	private String ngayThanhToan;
	
	@JsonProperty("trang_thai")
	private String trangThai;
	
	public VeCustomerDataModelResponse() {
		
	}
	
	public VeCustomerDataModelResponse(VeCustomerDataModel e) {
		this.id=e.getId();
		this.gioChay=e.getGioChay();
		this.ngayChay= UtilsService.getDateFormatVN(e.getNgayChay());
		this.count=e.getCount();
		this.giaVe=e.getGiaVe();
		this.code=e.getCode();
		this.idTuyenXe=e.getIdTuyenXe();
		this.benDi=e.getBenDi();
		this.benToi=e.getBenToi();
		this.ngayDat=UtilsService.getDateHourFormatVN(e.getNgayDat());
		this.ngayThanhToan =UtilsService.getDateHourFormatVN(e.getNgayThanhToan());
		this.trangThai=e.getTrangThai();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGioChay() {
		return gioChay;
	}

	public void setGioChay(int gioChay) {
		this.gioChay = gioChay;
	}

	public String getNgayChay() {
		return ngayChay;
	}

	public void setNgayChay(String ngayChay) {
		this.ngayChay = ngayChay;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(int giaVe) {
		this.giaVe = giaVe;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public String getBenDi() {
		return benDi;
	}

	public void setBenDi(String benDi) {
		this.benDi = benDi;
	}

	public String getBenToi() {
		return benToi;
	}

	public void setBenToi(String benToi) {
		this.benToi = benToi;
	}

	public String getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(String ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	public List<VeCustomerDataModelResponse> mapToList(List<VeCustomerDataModel> baseEntity){
		List<VeCustomerDataModelResponse> list =baseEntity	.stream()	
															.map(x->new VeCustomerDataModelResponse(x))
															.collect(Collectors.toList());
	return list;
	}
}