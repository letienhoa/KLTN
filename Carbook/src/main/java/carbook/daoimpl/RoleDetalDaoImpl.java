package carbook.daoimpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.RoleDetalDao;
import carbook.entity.RoleDetal;

@Transactional
@Repository("roleDetalDao")
public class RoleDetalDaoImpl extends AbstractDao<Integer, RoleDetal> implements RoleDetalDao {

	@Override
	public RoleDetal create(RoleDetal entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(RoleDetal entity) {
		// TODO Auto-generated method stub
		this.getSession().save(entity);
	}

}
