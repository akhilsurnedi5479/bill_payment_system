package com.cognizant.bill_payment_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/vendor/{flag}")
	List<Vendor> getAllVendorsBasedOnFlag(@PathVariable int flag){
		return adminService.getAllVendorsBasedOnFlag(flag);
	}
	
	@PutMapping("/vendor/accept/{vendorName}")
	Vendor acceptVendor(@PathVariable String vendorName) {
		return adminService.acceptVendor(vendorName);
	}
	
	@PutMapping("/vendor/sentBack/{vendorName}")
	Vendor sentBackForCorrection(@PathVariable String vendorName) {
		return adminService.sentBackForCorrection(vendorName);
	}
	
	@DeleteMapping("vendor/rejected/{vendorName}")
	void rejectVendor(@PathVariable String vendorName) {
		adminService.rejectVendor(vendorName);
	}
}
