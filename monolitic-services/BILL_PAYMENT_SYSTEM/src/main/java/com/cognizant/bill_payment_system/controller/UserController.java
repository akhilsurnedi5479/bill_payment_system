package com.cognizant.bill_payment_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bill_payment_system.exception.UserIdAlreadyExistsException;
import com.cognizant.bill_payment_system.model.Issue;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.service.UserService;
import com.cognizant.bill_payment_system.service.VendorService;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private VendorService vendorService;

	@PostMapping
	public User signUp(@RequestBody @Valid User user) throws UserIdAlreadyExistsException {
		System.out.println("signUp USer :"+user);
		userService.signUp(user);
		return userService.getUser(user);
	}
	
	@GetMapping("/{username}")
	public boolean checkUserExists(@PathVariable String username) {
		return userService.checkUserExists(username);
	}
	
	@GetMapping("/{username}/vendor/{vendorType}")
	public List<Vendor> findVendorByVendorTypeAndUser(@PathVariable String username,@PathVariable String vendorType){
		return vendorService.findVendorByVendorTypeAndUser(username,vendorType);
	}
	
	
	
	
	
}
