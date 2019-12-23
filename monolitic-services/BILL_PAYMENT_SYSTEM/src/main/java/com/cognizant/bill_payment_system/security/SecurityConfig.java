package com.cognizant.bill_payment_system.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognizant.bill_payment_system.service.AppUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	AppUserDetailsService appUserDetailsService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(inMemoryUserDetailsManager());
		
		LOGGER.info("Configure Auth Start");
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
		LOGGER.info("Configure Auth End");
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		System.out.println("Configure in SecuirtyConfig");
		httpSecurity.csrf().disable().httpBasic().and()
			.authorizeRequests()
			.antMatchers("/authenticate").permitAll()
			
			.antMatchers("/users/**").anonymous()
			
			.antMatchers("/admin/**").permitAll()
			//.antMatchers("/**").permitAll()
			.antMatchers("/vendor/**").permitAll()
			.antMatchers("/vendor-types/**").permitAll()
			.antMatchers("/payment/**").permitAll()
			.antMatchers("/issue/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new JwtAuthorizationFilter(authenticationManager()));

	}

}
