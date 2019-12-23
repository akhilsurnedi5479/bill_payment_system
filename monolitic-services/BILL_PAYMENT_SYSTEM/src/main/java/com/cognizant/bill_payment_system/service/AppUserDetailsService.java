package com.cognizant.bill_payment_system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.exception.UserIdAlreadyExistsException;
import com.cognizant.bill_payment_system.exception.VendorAlreadyExistsException;
import com.cognizant.bill_payment_system.model.Role;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.model.Vendor;
import com.cognizant.bill_payment_system.repository.RoleRepository;
import com.cognizant.bill_payment_system.repository.UserRepository;
import com.cognizant.bill_payment_system.repository.VendorRepository;
import com.cognizant.bill_payment_system.security.AppUser;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	VendorRepository vendorRepository;
	
	User user;

	AppUser appUser;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		user = userRepository.findByUserId(userId);
		
		if (user == null) {
			throw new UsernameNotFoundException("Uername not found");
		} else {
			appUser = new AppUser(user);
		}
			
		return appUser;
	}

	
	public void signUp(User newUser) throws UserIdAlreadyExistsException {

		User user = new User();

		user = userRepository.findByUserId(newUser.getUserId());
		
		System.out.println("user: "+user);
		
		System.out.println("role "+newUser);
		
		if (user == null) {
			
			Role role = roleRepository.findByRoleId(2);

			if(newUser.isAdmin()) {
				 role = roleRepository.findByRoleId(1);
			}
			
			
			String password = newUser.getPassword();
			Set<Role> roleList = new HashSet<Role>();
			roleList.add(role);
			
			List<Vendor> vendorList = vendorRepository.findByFlag(1);
			
			newUser.setVendorList(vendorList);
			
			newUser.setRole(role);

			newUser.setPassword(passwordEncoder().encode(password));
			System.out.println(newUser.getPassword() + " Passowrd");
			userRepository.save(newUser);
		} else
			throw new UserIdAlreadyExistsException();
	}
	
	public void signUpVendor(User newUser) throws VendorAlreadyExistsException {

		User user = new User();

		user = userRepository.findByUserId(newUser.getUserId());
		
		System.out.println("user: "+user);
		
		System.out.println("role "+newUser);
		
		if (user == null) {
			
			Role role = roleRepository.findByRoleId(3);

			
			
			String password = newUser.getPassword();
			Set<Role> roleList = new HashSet<Role>();
			roleList.add(role);
			
			newUser.setRole(role);

			newUser.setPassword(passwordEncoder().encode(password));
			System.out.println(newUser.getPassword() + " Passowrd");
			userRepository.save(newUser);
		} else
			throw new VendorAlreadyExistsException();
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public AppUserDetailsService() {
		super();
	}

	public AppUserDetailsService(User user, AppUser appUser) {
		super();
		this.user = user;
		this.appUser = appUser;
	}
}
