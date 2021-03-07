package carbook.daoimpl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.TramDungDao;
import carbook.entity.TramDung;

@Transactional
@Repository("tramDungDao")
@SuppressWarnings("unchecked")
public class TramDungDaoImpl extends AbstractDao<Integer, TramDung>  implements TramDungDao{

	@Override
	public TramDung create(TramDung entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(TramDung entity) {
		// TODO Auto-generated method stub
		this.getSession().update(entity);
	}

	@Override
	public List<TramDung> spGetTramDungForTuyenXe(int diemDiId, int diemToiId) {
		// TODO Auto-generated method stub
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_tram_dung_for_tuyen_xe",TramDung.class);
		procedureCall.registerParameter("diemDiId", Integer.class, ParameterMode.IN).bindValue(diemDiId);
		procedureCall.registerParameter("diemToiId", Integer.class, ParameterMode.IN).bindValue(diemToiId);
	    
		List<TramDung> list =procedureCall.getResultList();
		return list;
	}

	@Override
	public TramDung findOne(int id) {
		CriteriaQuery<TramDung> criteria = this.getBuilder().createQuery(TramDung.class);
		Root<TramDung> root = criteria.from(TramDung.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("id"), id));
		return this.getSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public List<TramDung> findAll() {
		CriteriaQuery<TramDung> criteria = this.getBuilder().createQuery(TramDung.class);
		Root<TramDung> root = criteria.from(TramDung.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

}
