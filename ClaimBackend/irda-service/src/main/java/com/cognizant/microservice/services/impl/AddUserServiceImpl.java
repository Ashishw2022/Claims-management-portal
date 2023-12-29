package com.cognizant.microservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.entity.UserInfo;
import com.cognizant.microservice.repository.UserInfoRepository;
import com.cognizant.microservice.services.AddUserService;

@Service
public class AddUserServiceImpl implements AddUserService {
	
	@Autowired
	private UserInfoRepository repo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repo.save(userInfo);
        return "user added to system ";
    }
}
