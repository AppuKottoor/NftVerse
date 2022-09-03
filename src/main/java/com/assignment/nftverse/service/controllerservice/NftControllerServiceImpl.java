package com.assignment.nftverse.service.controllerservice;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assignment.nftverse.config.JWTTokenHelper;
import com.assignment.nftverse.model.NftUser;
import com.assignment.nftverse.request.AuthenticationRequest;
import com.assignment.nftverse.response.HelloResponse;
import com.assignment.nftverse.response.LoginResponse;

@Service
public class NftControllerServiceImpl implements NftControllerService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jWTTokenHelper;

    @Override
    public ResponseEntity<?> logAuth(AuthenticationRequest authenticationRequest, HttpServletRequest request)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUserName(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Please Enter Valid Credentials", HttpStatus.BAD_REQUEST);
        }

        if(authentication!=null){
            NftUser user = (NftUser) authentication.getPrincipal();
            LoginResponse loginResponse=new LoginResponse();
            String jwtToken = jWTTokenHelper.generateToken(user.getUsername());
            loginResponse.setToken(jwtToken);
            return ResponseEntity.ok(loginResponse);
        }else{
            return new ResponseEntity<String>("Failed to authenticate user",HttpStatus.INTERNAL_SERVER_ERROR);
        }

       
    }

    @Override
    public HelloResponse helloService() {
        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setStatus("Success");
        return helloResponse;
    }

}
