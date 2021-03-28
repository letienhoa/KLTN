package carbook.daoimpl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.TuyenXeDao;
import carbook.entity.TuyenOff;
import carbook.entity.TuyenXe;
import carbook.entity.TuyenXeModelData;
import carbook.entity.TuyenXePhoBienDataModel;

@SuppressWarnings("unchecked")
@Transactional
@Repository("tuyenXeDao")
public class TuyenXeDaoImpl extends AbstractDao<Integer,TuyenXe> implements TuyenXeDao {

	@Override
	public TuyenXe spGetByDiemDiDiemToi(int DiemDi, int DiemToi) {
		CriteriaQuery<TuyenXe> criteria = this.getBuilder().createQuery(TuyenXe.class);

		Root<TuyenXe> root = criteria.from(TuyenXe.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("diemDiId"), DiemDi),
									this.getBuilder().equal(root.get("diemToiId"),DiemToi));
		try {
			return this.getSession().createQuery(criteria).getSingleResult();
		}catch (Exception e) {
			
			TuyenXe tuyenXe=null;
			return tuyenXe;
		}
	}

	@Override
	public TuyenXe create(TuyenXe entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(TuyenXe entity) {
		this.getSession().update(entity);
		
	}

	@Override
	public TuyenXe findOne(int id) {
		CriteriaQuery<TuyenXe> criteria = this.getBuilder().createQuery(TuyenXe.class);
		Root<TuyenXe> root = criteria.from(TuyenXe.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("id"), id));
		return this.getSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public List<TuyenXeModelData> findAll() {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_list_tuyen_xe",TuyenXeModelData.class);
	    
		List<TuyenXeModelData> list =procedureCall.getResultList();
		return list;
	}

	@Override
	public List<TuyenXePhoBienDataModel> spGetListTuyenXePhoBien() {
		
ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_list_tuyen_xe_pho_bien",TuyenXePhoBienDataModel.class);
	    
		List<TuyenXePhoBienDataModel> list =procedureCall.getResultList();
		return list;
	}

	@Override
	public TuyenOff createTuyenOff(TuyenOff entity) {
		this.getSession().save(entity);
		return entity;
		
	}

}
