package com.cognizant.bill_payment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.exception.UserIdAlreadyExistsException;
import com.cognizant.bill_payment_system.model.Issue;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.repository.IssueRepository;
import com.cognizant.bill_payment_system.repository.RoleRepository;
import com.cognizant.bill_payment_system.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepo;
	
	
	
	@Override
	public void signUp(User user) throws UserIdAlreadyExistsException {
		//the front end should sent the role also;
		
		
		
		System.out.println("Inside SignUp UserServiceImpl");
			appUserDetailsService.signUp(user);
			

	}
	
	@Override
	public boolean checkUserExists(String username) {
		User user=userRepository.findByUserId(username);
		if(user!=null) {
			return true;
		}
		return false;
	}


	@Override
	public User getUser(User user) {
		
		return userRepository.findByUserId(user.getUserId());
	}

	

}
