package com.cognizant.bill_payment_system.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.bill_payment_system.model.Vendor;

public interface AdminService {
	List<Vendor> getAllVendorsBasedOnFlag( int flag);
	
	Vendor acceptVendor( String vendorId);
	
	Vendor sentBackForCorrection(String vendorId);
	
	Vendor rejectVendor(String vendorId);
}
