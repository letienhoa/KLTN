package carbook.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.Ben;

public class BenToiResponse {
 
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("ben_toi")
	private String thanhPho;
	
	public BenToiResponse() {
		
	}
	public BenToiResponse(Ben entity) {
		this.id = entity.getId();
		this.thanhPho = entity.getThanhPho();
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

	public void setThanhPho(String tenBen) {
		this.thanhPho = tenBen;
	}
	
	public List<BenToiResponse> mapTolist(List<Ben> baseEntity){
		List<BenToiResponse> list = baseEntity.stream().map(x-> new BenToiResponse(x)).collect(Collectors.toList());
	return list;
	}
	
}
