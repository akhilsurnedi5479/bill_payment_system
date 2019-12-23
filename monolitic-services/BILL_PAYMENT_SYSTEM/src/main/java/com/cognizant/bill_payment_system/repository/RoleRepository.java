package com.cognizant.bill_payment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.bill_payment_system.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByRoleId(int id);
	

}