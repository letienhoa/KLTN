package carbook.dao;

import java.util.List;

import carbook.entity.DiemDon;

public interface DiemDonDao {

	DiemDon create(DiemDon entity);
	
	void update(DiemDon entity);
	
	List<DiemDon> getListForBen(int benId);
	
	List<DiemDon> findAll();
	
	DiemDon findOne(int id);
}
