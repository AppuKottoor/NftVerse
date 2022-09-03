package com.assignment.nftverse.service.repositoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.nftverse.model.NftUser;
import com.assignment.nftverse.repository.NftUserRepository;

@Service
public class NftUserServiceImpl implements NftUserService{

    @Autowired
    private NftUserRepository nftUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NftUser nftUser=nftUserRepository.findByNftUserName(username);
        if(nftUser==null){
            throw new UsernameNotFoundException("User doesn't exist");
        }
        return nftUser; 
    }


    @Override
    public NftUser saveUser(NftUser nftUser) {
       
        return nftUserRepository.save(nftUser);
    }
    
}
