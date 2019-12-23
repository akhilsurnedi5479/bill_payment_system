package com.cognizant.bill_payment_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.bill_payment_system.model.Payment_Gateway;
import com.cognizant.bill_payment_system.model.Vendor;

@Repository
public interface PaymentGatewayRepository extends JpaRepository<Payment_Gateway, Integer>{

	Payment_Gateway findByPaymentGatewayName(String name);
	
	List<Payment_Gateway> findPaymentGatewayByVendorList(Vendor vendor);
	
}
