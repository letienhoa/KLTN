package carbook.dao;

import java.util.List;

import carbook.entity.QuyDoiDiem;

public interface QuyDoiDiemDao {

	QuyDoiDiem create(QuyDoiDiem entity);
	
	void update(QuyDoiDiem entity);
	
	QuyDoiDiem findOne(int id);
	
	QuyDoiDiem findOneByPoint(int point);
	
	List<QuyDoiDiem> getAllList();
}
