package com.assignment.nftverse.service.controllerservice;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.assignment.nftverse.request.AuthenticationRequest;
import com.assignment.nftverse.response.HelloResponse;

public interface NftControllerService {
    
    public ResponseEntity<?> logAuth(AuthenticationRequest authenticationRequest, HttpServletRequest request)
			throws InvalidKeySpecException, NoSuchAlgorithmException;

    public HelloResponse helloService();
}
