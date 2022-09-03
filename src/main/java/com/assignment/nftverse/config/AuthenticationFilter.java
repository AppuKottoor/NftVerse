package com.assignment.nftverse.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.nftverse.model.NftUser;
import com.assignment.nftverse.service.repositoryservice.NftUserService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {
    
    private NftUserService nftUserService;
	private JWTTokenHelper jwtTokenHelper;
	
	public AuthenticationFilter(NftUserService nftUserService,JWTTokenHelper jwtTokenHelper) {
		this.nftUserService=nftUserService;
		this.jwtTokenHelper=jwtTokenHelper;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authToken=jwtTokenHelper.getToken(request);
		
		if(null!=authToken) {
			
			String userName=jwtTokenHelper.getUsernameFromToken(authToken);
			
			if(null!=userName) {
				
				NftUser nftUser=(NftUser) nftUserService.loadUserByUsername(userName);
				
				if(jwtTokenHelper.validateToken(authToken, nftUser)) {
					UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(nftUser, null,nftUser.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				
			}
			
		}
		filterChain.doFilter(request, response);
	}
}
