package carbook.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import carbook.entity.QuyDoiDiem;

public class QuyDoiDiemCustomResponse {

	@JsonProperty("cap_do")
	private String capDo;
	@JsonProperty("tien_toi_thieu")
	private int tienToiThieu;
	@JsonProperty("phan_tram_giam_gia")
	private int phanTramGiamGia;
	
	public QuyDoiDiemCustomResponse() {
		
	}
	
	public QuyDoiDiemCustomResponse(QuyDoiDiem e) {
	this.capDo=e.getTitle();
	this.tienToiThieu = e.getPoint()*4000;
	this.phanTramGiamGia=e.getDiscount();
		
	}

	public String getCapDo() {
		return capDo;
	}

	public void setCapDo(String capDo) {
		this.capDo = capDo;
	}

	public int getTienToiThieu() {
		return tienToiThieu;
	}

	public void setTienToiThieu(int tienToiThieu) {
		this.tienToiThieu = tienToiThieu;
	}

	public int getPhanTramGiamGia() {
		return phanTramGiamGia;
	}

	public void setPhanTramGiamGia(int phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}
	public List<QuyDoiDiemCustomResponse> mapToList(List<QuyDoiDiem> l){
		List<QuyDoiDiemCustomResponse> list =l.stream()
												.map(x-> new QuyDoiDiemCustomResponse(x))
												.collect(Collectors.toList());
		return list;
	}
}
