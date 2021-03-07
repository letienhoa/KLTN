package carbook.dao;

import java.util.List;

import carbook.entity.TuyenOff;
import carbook.entity.TuyenXe;
import carbook.entity.TuyenXeModelData;
import carbook.entity.TuyenXePhoBienDataModel;

public interface TuyenXeDao {

	TuyenXe create(TuyenXe entity);
	
	void update(TuyenXe entity);
	
	TuyenXe findOne(int id);
	
	List<TuyenXeModelData> findAll();
	
	List<TuyenXePhoBienDataModel> spGetListTuyenXePhoBien();
	
	TuyenXe spGetByDiemDiDiemToi(int DiemDi,int DiemToi);
	
	TuyenOff createTuyenOff(TuyenOff entity);
	
}
