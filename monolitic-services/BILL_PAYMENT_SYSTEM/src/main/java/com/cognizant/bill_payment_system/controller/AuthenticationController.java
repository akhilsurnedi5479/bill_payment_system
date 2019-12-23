package com.cognizant.bill_payment_system.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class AuthenticationController {

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		String user = getUser(authHeader);
		
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		Map<String, String> auth = new HashMap<String, String>();
		auth.put("token", generateJwt(user));
		auth.put("role", role);
		return auth;
	}

	private String getUser(String authHeader) {
		byte[] auth = Base64.getDecoder().decode(authHeader.split(" ")[1]);
		String authStr = new String(auth);
		return authStr.split(":")[0];
	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		// set the token issue time as current time
		builder.setIssuedAt(new Date());

		// Set the token expiry as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));

		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		return token;

	}
}
