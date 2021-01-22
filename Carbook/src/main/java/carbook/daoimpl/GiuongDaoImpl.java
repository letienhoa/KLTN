package carbook.daoimpl;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.GiuongDao;
import carbook.entity.Giuong;
import carbook.entity.GiuongModelData;

@Transactional
@Repository("GiuongDao")
@SuppressWarnings("unchecked")
public class GiuongDaoImpl extends AbstractDao<Integer,GiuongModelData>  implements GiuongDao {

	@Override
	public List<GiuongModelData> spGetGiuongByTuyenXeId(int tuyenXeId, int gio,Date ngay) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_giuong_by_tuyen_xe_id",GiuongModelData.class);
		procedureCall.registerParameter("tuyenXeId", Integer.class, ParameterMode.IN).bindValue(tuyenXeId);
		procedureCall.registerParameter("ngay", Date.class, ParameterMode.IN).bindValue(ngay);
		procedureCall.registerParameter("gio", Integer.class, ParameterMode.IN).bindValue(gio);
	    
		List<GiuongModelData> list =procedureCall.getResultList();
		return list;
	}

	@Override
	public Giuong create(Giuong entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(Giuong entity) {
		this.getSession().update(entity);
		
	}
	
}
