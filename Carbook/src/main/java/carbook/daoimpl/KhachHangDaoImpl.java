package carbook.daoimpl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.KhachHangDao;
import carbook.entity.BaseEntity;
import carbook.entity.User;
import carbook.entity.UserRoleDataModel;
import carbook.entity.UserToken;

@SuppressWarnings("unchecked")
@Transactional
@Repository("KhachHangDao")
public class KhachHangDaoImpl extends AbstractDao<Integer,User> implements KhachHangDao {

	@Override
	public BaseEntity create(BaseEntity entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(BaseEntity entity) {
		this.getSession().update(entity);
	}
	
	@Override
	public List<User> findAll() {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public User getById(int id) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("id"), id));
		User user =new User();
		try {
			user =this.getSession().createQuery(criteria).getSingleResult();
		}catch (Exception e) {
			user=null;
		}
		
		return user;
		//return this.getSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public UserToken getByTaiKhoanMatKhau(String taiKhoan, String matKhau) {
		
		//lấy user trước
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).where(this.getSession().getCriteriaBuilder().equal(root.get("taiKhoan"),taiKhoan),
									this.getSession().getCriteriaBuilder().equal(root.get("Password"), matKhau));
		User user= this.getSession().createQuery(criteria).getSingleResult();
		
		//lấy danh sách role
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("get_list_roles_for_user");
		procedureCall.registerParameter("idUser", Integer.class, ParameterMode.IN).bindValue(user.getId());
		UserToken userToken = new UserToken(user);
	
		List<String> list =procedureCall.getResultList();
		String [] mang=list.toArray(new String[0]);

		userToken.setRoles(mang);
		return userToken;
		
	}

	@Override
	public UserToken loadUserByUsername(String username) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("taiKhoan"), username));
		User user= this.getSession().createQuery(criteria).getSingleResult();
		
		
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("get_list_roles_for_user");
		procedureCall.registerParameter("idUser", Integer.class, ParameterMode.IN).bindValue(user.getId());
		UserToken userToken = new UserToken(user);
	
		List<String> list =procedureCall.getResultList();
		String [] mang=list.toArray(new String[0]);
	
		userToken.setRoles(mang);
		return userToken;
	}

	@Override
	public User findByUsername(String name) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("taiKhoan"), name));
		User user =new User();
		try {
			user =this.getSession().createQuery(criteria).getSingleResult();
		}catch (Exception e) {
			user=null;
		}
		
		return user;
		//return  this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public User findByUsernameAndPassword(String name, String mk) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).where(this.getSession().getCriteriaBuilder().equal(root.get("taiKhoan"),name),
									this.getSession().getCriteriaBuilder().equal(root.get("Password"), mk));
		User user =new User();
		try {
			user =this.getSession().createQuery(criteria).getSingleResult();
		}catch (Exception e) {
			user=null;
		}
		
		return user;
		//User user= this.getSession().createQuery(criteria).getSingleResult();
		//return user;
	}

	@Override
	public List<UserRoleDataModel> spGetAllUser() {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_all_user",UserRoleDataModel.class);
		List<UserRoleDataModel> list =procedureCall.getResultList();
		return list;
	}

	
}
