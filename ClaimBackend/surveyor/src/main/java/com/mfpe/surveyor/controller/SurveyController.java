package com.mfpe.surveyor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.surveyor.config.UserInfoUserDetails;
import com.mfpe.surveyor.dto.AuthRequest;
import com.mfpe.surveyor.dto.SurveyDto;
import com.mfpe.surveyor.exception.SurveyReportNotFoundException;
import com.mfpe.surveyor.model.AuthenticationResponse;
import com.mfpe.surveyor.model.UserInfo;
import com.mfpe.surveyor.service.JwtService;
import com.mfpe.surveyor.service.SurveyReportService;

@RestController
@CrossOrigin("http://localhost:4200")
public class SurveyController {

	@Autowired
	private SurveyReportService surveyReportService;
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	  @PostMapping("/new")
	    public String addNewUser(@RequestBody UserInfo userInfo) {
	        return surveyReportService.addUser(userInfo);
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

	@PostMapping("/api/surveyors/new")
	@PreAuthorize("hasAuthority('Role_surveyor')")
	public ResponseEntity<SurveyDto> addSurvey(@RequestBody SurveyDto surveyDto) throws SurveyReportNotFoundException {
		SurveyDto result = surveyReportService.addSurvey(surveyDto);
		return new ResponseEntity<SurveyDto>(result, HttpStatus.CREATED);
	}

	@GetMapping("/api/survey/{claimId}")
	@PreAuthorize("hasAuthority('Role_surveyor')")
	public SurveyDto getSurvey(@PathVariable String claimId) throws SurveyReportNotFoundException {
		return surveyReportService.getSurvey(claimId);
	}

	@PutMapping("/api/survey/{claimId}")
	@PreAuthorize("hasAuthority('Role_surveyor')")
	public ResponseEntity<SurveyDto> updateClaim(@PathVariable String claimId, @RequestBody SurveyDto surveyDto)
			throws SurveyReportNotFoundException {
		SurveyDto result = surveyReportService.updateSurvey(claimId, surveyDto);
		return new ResponseEntity<SurveyDto>(result, HttpStatus.OK);

	}

}
