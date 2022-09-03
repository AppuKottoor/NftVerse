package com.assignment.nftverse.service.repositoryservice;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.assignment.nftverse.model.NftUser;

public interface NftUserService extends UserDetailsService{
    
    public NftUser saveUser(NftUser nftUser); 
   
}
