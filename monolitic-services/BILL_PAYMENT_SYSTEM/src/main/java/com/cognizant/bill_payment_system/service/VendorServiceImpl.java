package com.cognizant.bill_payment_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.exception.VendorAlreadyExistsException;
import com.cognizant.bill_payment_system.model.Payment_Gateway;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.model.VendorDetails;
import com.cognizant.bill_payment_system.model.VendorType;
import com.cognizant.bill_payment_system.repository.PaymentGatewayRepository;
import com.cognizant.bill_payment_system.repository.RoleRepository;
import com.cognizant.bill_payment_system.repository.UserRepository;
import com.cognizant.bill_payment_system.repository.VendorRepository;
import com.cognizant.bill_payment_system.repository.VendorTypeRepository;


@Service
public class VendorServiceImpl implements VendorService{
	
	@Autowired
	VendorRepository vendorRepo;

	
	@Autowired
	VendorTypeRepository vendorTypeRepository;
	
	@Autowired
	PaymentGatewayRepository paymentGatewayRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Override
	public void signUp(VendorDetails vendor) throws VendorAlreadyExistsException {
		
		System.out.println("vendorDetails   "+vendor.toString());
		
		Vendor newVendor=new Vendor();
		
		newVendor.setVendorName(vendor.getVendorName());
		newVendor.setVendorAddress(vendor.getVendorAddress());
		newVendor.setVendorCertificationIssueDate(vendor.getVendorCertificationIssueDate());
		newVendor.setVendorCertificationValidityDate(vendor.getVendorCertificationValidityDate());
		newVendor.setVendorCountry(vendor.getVendorCountry());
		newVendor.setVendorEmail(vendor.getVendorEmail());
		newVendor.setVendorRegNo(vendor.getVendorRegNo());
		newVendor.setVendorState(vendor.getVendorState());
		newVendor.setVendorWebSite(vendor.getVendorWebSite());
		newVendor.setVendorYearOfEstablishment(vendor.getVendorYearOfEstablishment());
		newVendor.setVendorContactNo(vendor.getVendorContactNo());
		
		
		VendorType vendorType= vendorTypeRepository.findByVendorTypeName(vendor.getVendorType());
		newVendor.setVendorType(vendorType);
		newVendor.setFlag(0);
		
		
		List<Payment_Gateway> paymentGateWay = new ArrayList<Payment_Gateway>();
		Payment_Gateway payGateway;
		for(String paymentGatewayName: vendor.getPaymentGateway()) {
			payGateway = paymentGatewayRepository.findByPaymentGatewayName(paymentGatewayName);
			paymentGateWay.add(payGateway);
		}
		
		newVendor.setPaymentGateway(paymentGateWay);
		vendorRepo.save(newVendor);
		
		User user= new User();
		user.setUserId(vendor.getVendorName());
		System.out.println(vendor.getPassword());
		user.setPassword(vendor.getPassword());
		appUserDetailsService.signUpVendor(user);
		
		
		
	}

	@Override
	public Vendor getVendor(String vendorName) {
		
		return vendorRepo.findByVendorName(vendorName);
	}
	
	@Override
	public VendorDetails getVendorByVendorName(String vendorName) {
		Vendor vendor=vendorRepo.findByVendorName(vendorName);
		VendorDetails vendorDetails=new VendorDetails();
		vendorDetails.setVendorName(vendorName);
		vendorDetails.setVendorRegNo(vendor.getVendorRegNo());
		vendorDetails.setVendorCountry(vendor.getVendorCountry());
		vendorDetails.setVendorState(vendor.getVendorState());
		vendorDetails.setVendorAddress(vendor.getVendorAddress());
		vendorDetails.setVendorEmail(vendor.getVendorEmail());
		vendorDetails.setVendorContactNo(vendor.getVendorContactNo());
		vendorDetails.setVendorWebSite(vendor.getVendorWebSite());
		vendorDetails.setVendorCertificationIssueDate(vendor.getVendorCertificationIssueDate());
		vendorDetails.setVendorCertificationValidityDate(vendor.getVendorCertificationValidityDate());
		vendorDetails.setVendorYearOfEstablishment(vendor.getVendorYearOfEstablishment());
		List<Payment_Gateway> paymentGatewayList=paymentGatewayRepository.findPaymentGatewayByVendorList(vendor);
		vendorDetails.setStatus(vendor.getFlag());
		
		vendorDetails.setVendorType(vendor.getVendorType().getVendorTypeName());
		List<String> pGatewayList=new ArrayList<>();
		for(Payment_Gateway paymentGateway : paymentGatewayList) {
			pGatewayList.add(paymentGateway.getPaymentGatewayName());
		}
		vendorDetails.setPaymentGateway(pGatewayList);
		return vendorDetails;
	}

	@Override
	public List<Vendor> findVendorByVendorTypeAndUser(String userId, String vendorTypeName) {
		VendorType vendorType = vendorTypeRepository.findByVendorTypeName(vendorTypeName);
		User user = userRepo.findByUserId(userId);
		
		return vendorRepo.findVendorByVendorTypeAndUserList( vendorType,user);
	}

	@Override
	public void updateVendor(VendorDetails vendor) {
		
		Vendor newVendor=vendorRepo.findByVendorName(vendor.getVendorName());
		
		//newVendor.setVendorName(vendor.getVendorName());
		newVendor.setVendorAddress(vendor.getVendorAddress());
		newVendor.setVendorCertificationIssueDate(vendor.getVendorCertificationIssueDate());
		newVendor.setVendorCertificationValidityDate(vendor.getVendorCertificationValidityDate());
		newVendor.setVendorCountry(vendor.getVendorCountry());
		newVendor.setVendorEmail(vendor.getVendorEmail());
		newVendor.setVendorRegNo(vendor.getVendorRegNo());
		newVendor.setVendorState(vendor.getVendorState());
		newVendor.setVendorWebSite(vendor.getVendorWebSite());
		newVendor.setVendorYearOfEstablishment(vendor.getVendorYearOfEstablishment());
		newVendor.setVendorContactNo(vendor.getVendorContactNo());
		
		VendorType vendorType= vendorTypeRepository.findByVendorTypeName(vendor.getVendorType());
		newVendor.setVendorType(vendorType);
		newVendor.setFlag(0);
		
		List<Payment_Gateway> paymentGateWay = new ArrayList<Payment_Gateway>();
		Payment_Gateway payGateway;
		for(String paymentGatewayName: vendor.getPaymentGateway()) {
			payGateway = paymentGatewayRepository.findByPaymentGatewayName(paymentGatewayName);
			paymentGateWay.add(payGateway);
		}
		
		newVendor.setPaymentGateway(paymentGateWay);
		vendorRepo.save(newVendor);
	}

	@Override
	public List<Vendor> getVendorByVendorType(String vendorTypeName) {
		VendorType vendorType = vendorTypeRepository.findByVendorTypeName(vendorTypeName);
		
		return vendorRepo.findVendorByVendorTypeAndFlag(vendorType,1);
	}

}
