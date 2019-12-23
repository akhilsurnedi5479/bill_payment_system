package com.cognizant.bill_payment_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.bill_payment_system.exception.VendorAlreadyExistsException;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.model.VendorDetails;

@Service
public interface VendorService {
	public void signUp(VendorDetails vendor) throws VendorAlreadyExistsException;
	
	public Vendor getVendor(String vendorName);

	VendorDetails getVendorByVendorName(String vendorName);
	
	List<Vendor> findVendorByVendorTypeAndUser( String username, String vendorType);

	public void updateVendor(VendorDetails vendorDetails);
	
	List<Vendor> getVendorByVendorType(String vendorType);
}
