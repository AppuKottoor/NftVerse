package com.assignment.nftverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.nftverse.model.NftUser;

public interface NftUserRepository extends JpaRepository<NftUser,Integer> {
    
    public NftUser findByNftUserName(String nftUserName);
}
