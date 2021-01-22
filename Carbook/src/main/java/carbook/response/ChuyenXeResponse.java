package carbook.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.ChuyenXe;



public class ChuyenXeResponse {

	private int id;
	
	@JsonProperty("loai_xe")
	private String loaiXe;
	
	public ChuyenXeResponse() {
		
	}
	
	public ChuyenXeResponse(ChuyenXe entity) {
		this.id=entity.getId();
		this.loaiXe=entity.getLoaiXe();		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoaiXe() {
		return loaiXe;
	}

	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}
	
	public List<ChuyenXeResponse> maptolist(List<ChuyenXe> entity){
		
		List<ChuyenXeResponse> list = entity
				.stream()
				.map(x-> new ChuyenXeResponse(x)).collect(Collectors.toList());
		return list;
	} 
	
}
