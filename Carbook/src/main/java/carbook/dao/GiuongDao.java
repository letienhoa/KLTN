package carbook.dao;

import java.util.Date;

import carbook.entity.Giuong;
import carbook.entity.ListGiuongModelData;

public interface GiuongDao {
	
	Giuong create(Giuong entity);
	
	void update(Giuong entity);

	ListGiuongModelData spGetGiuongByTuyenXeId(int tuyenXeId,int gio,Date ngay);
}
