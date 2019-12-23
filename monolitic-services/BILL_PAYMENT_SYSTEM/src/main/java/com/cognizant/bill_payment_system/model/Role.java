package com.cognizant.bill_payment_system.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ro_id")
	private int roleId;
	
	@Column(name="ro_name")
	private String roleName;
	
	


	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
