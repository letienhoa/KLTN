package carbook.dao;

import java.util.Date;
import java.util.List;

import carbook.entity.Giuong;
import carbook.entity.GiuongModelData;

public interface GiuongDao {
	
	Giuong create(Giuong entity);
	
	void update(Giuong entity);

	List<GiuongModelData> spGetGiuongByTuyenXeId(int tuyenXeId,int gio,Date ngay);
}
