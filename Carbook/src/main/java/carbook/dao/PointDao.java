package carbook.dao;

import carbook.entity.UserPointDataModel;

public interface PointDao {
	
	Long spCreateHistoryPoint(String email, int point,int status);
	
	UserPointDataModel spGetDetailPointCustomer(int id);
}
