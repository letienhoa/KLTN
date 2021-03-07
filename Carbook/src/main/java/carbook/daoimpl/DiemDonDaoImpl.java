package carbook.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.DiemDonDao;
import carbook.entity.DiemDon;


@Transactional
@Repository("diemDonDao")
public class DiemDonDaoImpl extends AbstractDao<Integer, DiemDon> implements DiemDonDao {

	@Override
	public DiemDon create(DiemDon entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(DiemDon entity) {
		this.getSession().update(entity);
		
	}

	@Override
	public List<DiemDon> getListForBen(int benId) {
		CriteriaQuery<DiemDon> criteria = this.getBuilder().createQuery(DiemDon.class);
		Root<DiemDon> root = criteria.from(DiemDon.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("idBenXe"), benId));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<DiemDon> findAll() {
		CriteriaQuery<DiemDon> criteria = this.getBuilder().createQuery(DiemDon.class);
		Root<DiemDon> root = criteria.from(DiemDon.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public DiemDon findOne(int id) {
		CriteriaQuery<DiemDon> criteria = this.getBuilder().createQuery(DiemDon.class);
		Root<DiemDon> root = criteria.from(DiemDon.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("id"), id));
		return this.getSession().createQuery(criteria).getSingleResult();
	}

}
