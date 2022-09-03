package com.assignment.nftverse.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.nftverse.request.AuthenticationRequest;
import com.assignment.nftverse.response.HelloResponse;
import com.assignment.nftverse.service.controllerservice.NftControllerService;

@RestController
public class NftController {

    @Autowired
    private NftControllerService nftControllerService;
    
    @GetMapping("/hello")
    public HelloResponse hello(){
        return nftControllerService.helloService();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> logIn(@RequestBody AuthenticationRequest authenticationRequest,HttpServletRequest request) throws InvalidKeySpecException,NoSuchAlgorithmException{
         return nftControllerService.logAuth(authenticationRequest, request);
    }
}
