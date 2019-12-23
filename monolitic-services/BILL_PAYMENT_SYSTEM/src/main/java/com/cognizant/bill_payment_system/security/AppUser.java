package com.cognizant.bill_payment_system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.bill_payment_system.model.User;


public class AppUser implements UserDetails{
	
	private User user;
	private List<GrantedAuthority> authorities;
	
	
	public AppUser() {
		super();
	}
	public AppUser(User user) {
		super();
		this.user = user;
		this.authorities=new ArrayList<GrantedAuthority>();
		this.authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
