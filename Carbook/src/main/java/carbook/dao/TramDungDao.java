package carbook.dao;

import java.util.List;

import carbook.entity.TramDung;

public interface TramDungDao {
	
	TramDung findOne(int id);
	
	List<TramDung> findAll();

	TramDung create(TramDung entity);
	
	void update(TramDung entity);
	
	List<TramDung> spGetTramDungForTuyenXe(int diemDiId,int diemToiId);
}
