package com.cognizant.bill_payment_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bill_payment_system.exception.UserIdAlreadyExistsException;
import com.cognizant.bill_payment_system.exception.VendorAlreadyExistsException;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.model.VendorDetails;
import com.cognizant.bill_payment_system.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	
	
	@Autowired
	VendorService vendorService;
	
	@PostMapping
	public Vendor signUp(@RequestBody @Valid VendorDetails vendor) throws VendorAlreadyExistsException {
		System.out.println("signUp USer :"+vendor);
		vendorService.signUp(vendor);
		return vendorService.getVendor(vendor.getVendorName());
	}
	
	@GetMapping("/{vendorName}")
	public VendorDetails getVendorByVendorName(@PathVariable String vendorName) {
		return vendorService.getVendorByVendorName(vendorName);
	}
	
	@PutMapping("/editvendor")
	public void updateVendor(@RequestBody VendorDetails vendorDetails) {
		vendorService.updateVendor(vendorDetails);
	}
	
	@GetMapping("vendorType/{vendorType}")
	public List<Vendor> getVendorsByVendorType(@PathVariable String vendorType){
		
		return vendorService.getVendorByVendorType(vendorType);
	}
}
