package com.cognizant.bill_payment_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.repository.UserRepository;
import com.cognizant.bill_payment_system.repository.VendorRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Vendor> getAllVendorsBasedOnFlag(int flag) {
		return vendorRepository.findByFlag(flag);
	}

	@Override
	public Vendor acceptVendor(String vendorId) {
		Vendor vendor = vendorRepository.findByVendorName(vendorId);

		System.out.println("Vendor Id" + vendor.getId());

		vendor.setFlag(1);
		vendorRepository.save(vendor);
		
		List<User> userList= userRepository.findAll();
		for(User user: userList) {
			user.getVendorList().add(vendor);
			userRepository.save(user);
		}
		
		
		return vendor;
	}

	@Override
	public Vendor sentBackForCorrection(String vendorId) {
		Vendor vendor = vendorRepository.findByVendorName(vendorId);
		List<User> userList= userRepository.findAll();
		for(User user: userList) {
			user.getVendorList().remove(vendor);
			userRepository.save(user);
		}
		
		vendor.setFlag(2);
		vendorRepository.save(vendor);
		return vendor;
	}

	@Override
	public Vendor rejectVendor(String vendorId) {
		System.out.println("vendor decline");
		
		Vendor vendor = vendorRepository.findByVendorName(vendorId);
		
		List<User> userList= userRepository.findAll();
		for(User user: userList) {
			user.getVendorList().remove(vendor);
			userRepository.save(user);
		}
		
		User user = userRepository.findByUserId(vendor.getVendorName());
		
		
		
		userRepository.delete(user);
		vendorRepository.delete(vendor);
		
		return null;
		
	}

}
