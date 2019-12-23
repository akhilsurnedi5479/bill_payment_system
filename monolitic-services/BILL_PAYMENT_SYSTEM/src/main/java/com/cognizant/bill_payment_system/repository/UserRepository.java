package com.cognizant.bill_payment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserId(String userId);
	User findByVendorList(Vendor vendor); 
}
