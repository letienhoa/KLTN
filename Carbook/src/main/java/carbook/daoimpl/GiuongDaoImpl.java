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
import carbook.entity.ListGiuongModelData;

@Transactional
@Repository("GiuongDao")
@SuppressWarnings("unchecked")
public class GiuongDaoImpl extends AbstractDao<Integer,GiuongModelData>  implements GiuongDao {

	@Override
	public ListGiuongModelData spGetGiuongByTuyenXeId(int tuyenXeId, int gio,Date ngay) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_giuong_by_tuyen_xe_id",GiuongModelData.class);
		procedureCall.registerParameter("tuyenXeId", Integer.class, ParameterMode.IN).bindValue(tuyenXeId);
		procedureCall.registerParameter("ngay", Date.class, ParameterMode.IN).bindValue(ngay);
		procedureCall.registerParameter("gio", Integer.class, ParameterMode.IN).bindValue(gio);
		procedureCall.registerParameter("floor", Long.class, ParameterMode.OUT);
		procedureCall.registerParameter("row", Long.class, ParameterMode.OUT);
		procedureCall.registerParameter("srow", Long.class, ParameterMode.OUT);
		
		List<GiuongModelData> list =procedureCall.getResultList();
	    
		ListGiuongModelData listGiuongModelData= new ListGiuongModelData();
		
	    listGiuongModelData.setRow((Integer) procedureCall.getOutputParameterValue("row"));
	    listGiuongModelData.setFloor((Integer) procedureCall.getOutputParameterValue("floor"));
	    listGiuongModelData.setSrow((Integer) procedureCall.getOutputParameterValue("srow"));
	    listGiuongModelData.setListGiuong(list);
		return listGiuongModelData;
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
