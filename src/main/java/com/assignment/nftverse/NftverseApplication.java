package com.assignment.nftverse;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.assignment.nftverse.model.NftUser;
import com.assignment.nftverse.service.repositoryservice.NftUserService;

@SpringBootApplication
public class NftverseApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private NftUserService nftUserService;

	public static void main(String[] args) {
		SpringApplication.run(NftverseApplication.class, args);
	}


	// @PostConstruct
	// protected void init(){
	// 	NftUser nftUser=new NftUser();
	// 	nftUser.setNftPassword(passwordEncoder.encode("1234"));
	// 	nftUser.setNftUserName("appu");
	// 	nftUserService.saveUser(nftUser);



	// }

}
