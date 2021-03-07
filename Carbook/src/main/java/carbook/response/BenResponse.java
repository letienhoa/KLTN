package carbook.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.Ben;

public class BenResponse {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("ben_toi")
	private String tenBen;
	
	@JsonProperty("dia_chi")
	private String diaChi;
	
	@JsonProperty("thanh_pho")
	private String thanhPho;
	
	@JsonProperty("picture")
	private String picture;
	
	public BenResponse() {
		
	}
	
	public BenResponse(Ben e) {
		this.id=e.getId();
		this.tenBen=e.getTenBen();
		this.diaChi=e.getDiaChi();
		this.thanhPho=e.getThanhPho();
		this.picture=e.getPicture();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenBen() {
		return tenBen;
	}

	public void setTenBen(String tenBen) {
		this.tenBen = tenBen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public List<BenResponse> mapToList(List<Ben> ben){
		List<BenResponse> list =ben.stream()
									.map(x->new BenResponse(x))
									.collect(Collectors.toList());
		return list;
	}
}
