package carbook.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListGiuongModelData {

	private Integer floor;
	
	private Integer row;
	
	private Integer srow;
	
	@JsonProperty("slot_count")
	private Integer slotCount;
	
	@JsonProperty("list_giuong")
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
	
	public Integer getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(Integer slotCount) {
		this.slotCount = slotCount;
	}

	public List<GiuongModelData> getListGiuong() {
		return listGiuong;
	}

	public void setListGiuong(List<GiuongModelData> listGiuong) {
		this.listGiuong = listGiuong;
	}
	
	
	
}
