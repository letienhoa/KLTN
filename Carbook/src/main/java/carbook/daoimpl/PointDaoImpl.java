package carbook.daoimpl;

import javax.persistence.ParameterMode;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.PointDao;
import carbook.entity.HistoryPoint;
import carbook.entity.UserPointDataModel;

//@SuppressWarnings("unchecked")
@Transactional
@Repository("pointDao")
public class PointDaoImpl extends AbstractDao<Integer,HistoryPoint> implements PointDao{

	@Override
	public Long spCreateHistoryPoint(String email, int point, int status) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_create_history_point");
		procedureCall.registerParameter("email", String.class, ParameterMode.IN).bindValue(email);
		procedureCall.registerParameter("point", Integer.class, ParameterMode.IN).bindValue(point);
		procedureCall.registerParameter("status", Integer.class, ParameterMode.IN).bindValue(status);
		procedureCall.registerParameter("message", Long.class, ParameterMode.OUT);
		procedureCall.execute();
		return (Long) procedureCall.getOutputParameterValue("message");

	}

	@Override
	public UserPointDataModel spGetDetailPointCustomer(int id) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_detail_point_customer",UserPointDataModel.class);
		procedureCall.registerParameter("idUser", Integer.class, ParameterMode.IN).bindValue(id);
		UserPointDataModel user = new UserPointDataModel();
		try {
			user= (UserPointDataModel) procedureCall.getSingleResult();
		}catch (Exception e) {
			user=null;
		}
		return user;
	}

}
