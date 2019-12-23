package com.cognizant.bill_payment_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bill_payment_system.model.Bill;
import com.cognizant.bill_payment_system.model.Payment;
import com.cognizant.bill_payment_system.service.BillService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	BillService billService;
	
	@GetMapping("/{vendorname}/{username}/{billPaymentGateway}")
	public Bill getPaymentDetails(@PathVariable String vendorname,@PathVariable String username,@PathVariable String billPaymentGateway) {
		return billService.getPaymentDetails(vendorname, username, billPaymentGateway);
	}
	
	@GetMapping("/{vendorname}/{username}")
	public List<Bill> getPaymentDetailsOfUser(@PathVariable String vendorname,@PathVariable String username) {
		return billService.getPaymentDetailsOfUser(vendorname, username);
	}
	
	@PostMapping
	public void doPayment(@RequestBody Payment payment) {
		System.out.println(payment);
		billService.doPayment(payment);
	}
	
	
	@PostMapping("/bill")
	public void addBill(@RequestBody Payment payment) {
		billService.addBill(payment);
	}
	
	@GetMapping("/bill/{vendorname}/{username}")
	List<Bill> getUnpaidBillsOfUserAndVendor(@PathVariable String vendorname,@PathVariable String username){
		return billService.getUnpaidBillsOfUserAndVendor(vendorname, username);
	}
	
	@GetMapping("/bill/paid/{vendorname}/{username}")
	List<Bill> getPaidBillsOfUserAndVendorPaid(@PathVariable String vendorname,@PathVariable String username){
		return billService.getPaidBillsOfUserAndVendor(vendorname, username);
	}
	
	@GetMapping("/bill/paid/{username}")
	List<Bill> getPaidBillsOfUserPaid(@PathVariable String username){
		System.out.println("this is called");
		List<Bill> billList =billService.getPaidBillsOfUser(username);
		System.out.println("returned here");
		
		for(Bill bill:billList) {
			System.out.println(bill.getVendor().getVendorName()+ "   ");
		}
		
		return billList;
	}
	
}
