package com.microservices.merchantOnboarding.merchantOnboarding.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Merchant;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MerchantDetails implements UserDetails {
	
	private static final long serialVersionUID = 5155720064139820502L;

//	private final Integer id;
	private final String username;
	private final String password;
	private final String roles;
 	private final Collection<? extends GrantedAuthority> authorities;

	public MerchantDetails(Merchant merchant) {
//		this.id = user.getId();
		this.username = merchant.getUsername();
		this.password = merchant.getPassword();
		this.roles=merchant.getRoles();
		
		this.authorities= Arrays.stream(merchant.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

//	@JsonIgnore
//	public Integer getId() {
//		return id;
//	}


	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}





}



