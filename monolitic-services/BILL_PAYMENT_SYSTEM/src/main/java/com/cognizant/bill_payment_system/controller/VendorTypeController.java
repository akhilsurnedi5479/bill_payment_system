package com.cognizant.bill_payment_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bill_payment_system.model.VendorDetails;
import com.cognizant.bill_payment_system.service.VendorTypeService;

@RestController
@RequestMapping("/vendor-types")
public class VendorTypeController {
	
	@Autowired
	VendorTypeService vendorTypeService;
	
	@GetMapping
	public List<VendorDetails> getAllVendorTypes(){
		return vendorTypeService.getAllVendorTypes();
	}
}
	