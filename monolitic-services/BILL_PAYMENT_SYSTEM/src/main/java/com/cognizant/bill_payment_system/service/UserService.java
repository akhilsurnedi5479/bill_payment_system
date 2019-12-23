package com.cognizant.bill_payment_system.service;

import com.cognizant.bill_payment_system.exception.UserIdAlreadyExistsException;
import com.cognizant.bill_payment_system.model.Issue;
import com.cognizant.bill_payment_system.model.User;

public interface UserService {
	
	public void signUp(User user) throws UserIdAlreadyExistsException;// throws UserAlreadyExistsException;
	
	public User getUser(User user);

	boolean checkUserExists(String userId);

}
