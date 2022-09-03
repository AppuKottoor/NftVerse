package com.assignment.nftverse.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.assignment.nftverse.service.repositoryservice.NftUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private NftUserService nftUserService;
	
	@Autowired
	private PasswordConfig passwordConfig;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
	
		return super.authenticationManager();
	}


	private final String[] PUBLIC_URLS= {"/authenticate"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint)
		.and()
		.authorizeRequests((request) -> request.antMatchers(PUBLIC_URLS)
				.permitAll().antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest()
				.authenticated())
		.addFilterBefore(new AuthenticationFilter(nftUserService, jwtTokenHelper),
				UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable().headers().frameOptions().disable();
		
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.userDetailsService(nftUserService).passwordEncoder(passwordConfig.passwordEncoder());
		
	}
	
	

}
