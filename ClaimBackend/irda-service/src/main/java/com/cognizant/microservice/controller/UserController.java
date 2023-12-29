package com.cognizant.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.microservice.dto.AuthRequest;
import com.cognizant.microservice.entity.AuthenticationResponse;
import com.cognizant.microservice.entity.UserInfo;
import com.cognizant.microservice.services.AddUserService;
import com.cognizant.microservice.services.JwtService;




@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private AddUserService addUserService;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	  @PostMapping("/new")
	    public String addNewUser(@RequestBody UserInfo userInfo) {
	        return addUserService.addUser(userInfo);
	    }
	@PostMapping("/authenticate")
	    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	        	final String jwt=jwtService.generateToken(authRequest.getUsername());
	        	return ResponseEntity.ok(new AuthenticationResponse(jwt));

	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	}

}
