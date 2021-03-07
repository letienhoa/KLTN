package carbook.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.QuyDoiDiemDao;
import carbook.entity.QuyDoiDiem;

@Transactional
@Repository("quyDoiDiemDao")
public class QuyDoiDiemDaoImpl extends AbstractDao<Integer, QuyDoiDiem> implements QuyDoiDiemDao {

	@Override
	public QuyDoiDiem create(QuyDoiDiem entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(QuyDoiDiem entity) {
	this.getSession().update(entity);
		
	}

	@Override
	public QuyDoiDiem findOne(int id) {
		CriteriaQuery<QuyDoiDiem> criteria = this.getBuilder().createQuery(QuyDoiDiem.class);
		Root<QuyDoiDiem> root = criteria.from(QuyDoiDiem.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("id"), id));
		return this.getSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public QuyDoiDiem findOneByPoint(int point) {
		CriteriaQuery<QuyDoiDiem> criteria = this.getBuilder().createQuery(QuyDoiDiem.class);
		Root<QuyDoiDiem> root = criteria.from(QuyDoiDiem.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("point"), point));
		return this.getSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public List<QuyDoiDiem> getAllList() {
		CriteriaQuery<QuyDoiDiem> criteria = this.getBuilder().createQuery(QuyDoiDiem.class);
		Root<QuyDoiDiem> root = criteria.from(QuyDoiDiem.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

}
