package com.cognizant.bill_payment_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.model.VendorType;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer>{
	
	Vendor findByVendorName(String vendorName);
	Vendor findById(int vendorId);
	
	List<Vendor> findByFlag(int flag); 
	
	
	List<Vendor> findVendorByVendorTypeAndUserList(VendorType vendor, User user);
	
	List<Vendor> findVendorByVendorTypeAndFlag(VendorType vendorType,int flag);
}
