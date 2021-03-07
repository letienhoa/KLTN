package carbook.entity;

import java.util.List;

public class ListGiuongModelData {

	private Integer floor;
	
	private Integer row;
	
	private Integer srow;
	
	List<GiuongModelData> listGiuong;
	
	public ListGiuongModelData() {
		
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getSrow() {
		return srow;
	}

	public void setSrow(Integer srow) {
		this.srow = srow;
	}

	public List<GiuongModelData> getListGiuong() {
		return listGiuong;
	}

	public void setListGiuong(List<GiuongModelData> listGiuong) {
		this.listGiuong = listGiuong;
	}
	
	
	
}
