package carbook.response;

import java.util.List;
import java.util.stream.Collectors;

import carbook.entity.Xe;

public class GioChayResponse {

	private int giochay;
	
	public GioChayResponse() {
		
	}
	public GioChayResponse(Xe entity) {
		this.giochay= entity.getGioChay();
	}
	public int getGiochay() {
		return giochay;
	}
	public void setGiochay(int giochay) {
		this.giochay = giochay;
	}
	
	public List<GioChayResponse> mapToList(List<Xe> baseEntity){
		List<GioChayResponse> list = baseEntity.stream().map(x->new GioChayResponse(x) ).collect(Collectors.toList());
	return list;
	}
}
