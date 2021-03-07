package carbook.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.ThongTinVeDataModel;


public class VeExcelVer1Response {

	
	@JsonProperty("ten_khach_hang")
	private String tenKh;
	
	@JsonProperty("so_dien_thoai")
	private String sdt;
	
	@JsonProperty("ma_ve")
	private String code;
	
	@JsonProperty("diem_xuong")
	private String diemXuong;
	
	@JsonProperty("vi_tri_giuong")
	private List<GiuongMapDataModelResponse> slotss;
	
	public VeExcelVer1Response() {
		this.slotss = new ArrayList<GiuongMapDataModelResponse>();
	}
	 public VeExcelVer1Response(ThongTinVeDataModel e) {
		 
		 this.tenKh=e.getTenKh();
		 this.sdt=e.getSdt();
		 this.code=e.getCode();
		 this.slotss =new ArrayList<GiuongMapDataModelResponse>(); 
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

	
	public List<GiuongMapDataModelResponse> getSlotss() {
		return slotss;
	}
	public void setSlotss(List<GiuongMapDataModelResponse> slotss) {
		this.slotss = slotss;
	}
	public List<VeExcelVer1Response> mapToList(List<ThongTinVeDataModel> b){
		
		List<VeExcelVer1Response> list= b.stream()
											.map(x->new VeExcelVer1Response(x))
											.collect(Collectors.toList());
		return list;
		
	}
}
