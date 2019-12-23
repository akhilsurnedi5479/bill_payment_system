package com.cognizant.bill_payment_system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.model.Bill;
import com.cognizant.bill_payment_system.model.Payment;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.repository.BillRepository;
import com.cognizant.bill_payment_system.repository.UserRepository;
import com.cognizant.bill_payment_system.repository.VendorRepository;

@Service
public class BillServiceImpl implements BillService{

	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/* (non-Javadoc)
	 * @see com.cognizant.billpaymentsystem.service.PaymentService#getPaymentDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public Bill getPaymentDetails(String vendorName, String username,String billPaymentGateway) {
		User user=userRepository.findByUserId(username);
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		List<Bill> billList=billRepository.findByVendorAndUserAndBillpaymentGateway(vendor, user, billPaymentGateway);
		System.out.println(billList.size());
		for(Bill bl:billList) {
			System.out.println(bl.getNameOnCard());
		}
		Bill bill=new Bill();
		bill=billList.get(billList.size()-1);
		return bill;
	}

	@Override
	public void doPayment(Payment payment) {
		System.out.println(payment.getBillAmount() +" "+payment.getBillId());
		Vendor vendor=vendorRepository.findByVendorName(payment.getVendorName());
		User user=userRepository.findByUserId(payment.getUsername());
		
		Bill bill=billRepository.findById(payment.getBillId()).get();
		bill.setUser(user);
		bill.setVendor(vendor);
		bill.setBillAmount(payment.getBillAmount());
		bill.setBillpaymentGateway(payment.getBillpaymentGateway());
		bill.setMonthsPaid(payment.getMonthsPaid());
		bill.setNameOnCard(payment.getNameOnCard());
		bill.setCardNumber(payment.getCardNumber());
		bill.setExpirationMonth(payment.getExpirationMonth());
		bill.setExpirationYear(payment.getExpirationYear());
		bill.setPostalCode(payment.getPostalCode());
		bill.setEmail(payment.getEmail());
		bill.setMobileNumber(payment.getMobileNumber());
		bill.setDateOfPay(new Date());
		
		bill.setStatus(1); // bill paid
		
		billRepository.save(bill);
	}

	@Override
	public List<Bill> getPaymentDetailsOfUser(String vendorName, String username) {
		User user=userRepository.findByUserId(username);
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		List<Bill> billList=billRepository.findByVendorAndUser(vendor, user);
		return billList;
	}

	@Override
	public void addBill(Payment payment) {
		Vendor vendor=vendorRepository.findByVendorName(payment.getVendorName());
		User user=userRepository.findByUserId(payment.getUsername());
		Bill bill=new Bill();
		bill.setUser(user);
		bill.setVendor(vendor);
		bill.setBillAmount(payment.getBillAmount());
		//bill.setBillpaymentGateway(payment.getBillpaymentGateway());
		//bill.setMonthsPaid(payment.getMonthsPaid());
		//bill.setNameOnCard(payment.getNameOnCard());
		//bill.setCardNumber(payment.getCardNumber());
		bill.setExpirationMonth(payment.getExpirationMonth());
		bill.setExpirationYear(payment.getExpirationYear());
		//bill.setPostalCode(payment.getPostalCode());
		//bill.setEmail(payment.getEmail());
		//bill.setMobileNumber(payment.getMobileNumber());
		//bill.setDateOfPay(new Date());
		bill.setStatus(0);
		billRepository.save(bill);
		
	}
	
	@Override
	public List<Bill> getUnpaidBillsOfUserAndVendor(String vendorName, String username) {
		User user=userRepository.findByUserId(username);
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		List<Bill> billList=billRepository.findByVendorAndUserAndStatus(vendor, user,0);
		return billList;
		
	}
	
	@Override
	public List<Bill> getPaidBillsOfUserAndVendor(String vendorName, String username) {
		System.out.println("paid");
		User user=userRepository.findByUserId(username);
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		List<Bill> billList=billRepository.findByVendorAndUserAndStatus(vendor, user,1);
		return billList;
		
	}

	@Override
	public List<Bill> getPaidBillsOfUser(String username) {
		User user=userRepository.findByUserId(username);
		List<Bill> billList=billRepository.findByUserAndStatus(user, 1);
		return billList;
	}
	

}
