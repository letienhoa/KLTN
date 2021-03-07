package carbook.daoimpl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.ChuyenXeDao;
import carbook.entity.ChuyenXe;

@Transactional
@Repository("chuyenXeDao")
public class ChuyenXeDaoImpl extends AbstractDao<Integer, ChuyenXe> implements ChuyenXeDao {

	@Override
	public List<ChuyenXe> findAll() {
		CriteriaQuery<ChuyenXe> criteria = this.getBuilder().createQuery(ChuyenXe.class);
		Root<ChuyenXe> root = criteria.from(ChuyenXe.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public ChuyenXe spGetCarBook(int id) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_carbook",ChuyenXe.class);
		procedureCall.registerParameter("id", Integer.class, ParameterMode.IN).bindValue(id);
		ChuyenXe xe = (ChuyenXe) procedureCall.getSingleResult();
		return xe;
	}

}
