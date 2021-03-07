package carbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="role_detal")
@Entity
public class RoleDetal {

	@Id
	private int id;

	@Column(name="id_user")
	private int idUser;
	
	@Column(name="id_role")
	private String RoleId;
	
	public RoleDetal() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	
	
}
