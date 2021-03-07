package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ThongKeDoanhThuModelData {

	@Column(name="total_amount")
	private double totalAmount;
	@Id
	@Column(name="time")
	private String time;
	
	public ThongKeDoanhThuModelData() {
		
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
