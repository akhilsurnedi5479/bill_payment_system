package com.cognizant.bill_payment_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.bill_payment_system.model.Bill;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{

	List<Bill> findByVendorAndUserAndBillpaymentGateway(Vendor vendor,User user,String billPaymentGateway); 
	List<Bill> findByVendorAndUser(Vendor vendor,User user);
	List<Bill> findByVendorAndUserAndStatus(Vendor vendor, User user,int flag);
	List<Bill> findByUserAndStatus(User user,int flag);
}