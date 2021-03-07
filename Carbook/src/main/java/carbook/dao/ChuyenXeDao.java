package carbook.dao;

import java.util.List;

import carbook.entity.ChuyenXe;


public interface ChuyenXeDao {

	List<ChuyenXe> findAll();
	
	ChuyenXe spGetCarBook(int id);
}
