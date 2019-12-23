package com.cognizant.bill_payment_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.bill_payment_system.model.VendorType;


@Repository
public interface VendorTypeRepository extends JpaRepository<VendorType, Integer>{
	VendorType findByVendorTypeName(String vendorTypeName);
	List<VendorType> findAll();
	
}
