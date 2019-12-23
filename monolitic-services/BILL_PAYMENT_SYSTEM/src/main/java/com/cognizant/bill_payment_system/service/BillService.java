package com.cognizant.bill_payment_system.service;

import java.util.List;


import com.cognizant.bill_payment_system.model.Bill;
import com.cognizant.bill_payment_system.model.Payment;

public interface BillService {
	
	Bill getPaymentDetails(String vendorName,String username,String billPaymentGateway);
	List<Bill> getPaymentDetailsOfUser(String vendorName,String username);
	void doPayment(Payment payment);
	
	void addBill(Payment payment);
	
	List<Bill> getUnpaidBillsOfUserAndVendor(String vendorName, String username);
	
	List<Bill> getPaidBillsOfUserAndVendor(String vendorname, String username);
	
	List<Bill> getPaidBillsOfUser(String username);
}
