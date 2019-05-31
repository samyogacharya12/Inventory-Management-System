package com.example.demo.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private Projectuser projectUser;
	@Autowired
	private List<String> roleuser;
	public CustomUserDetails(Projectuser projectUser,List<String> roleuser)
	{
		super();
		this.projectUser=projectUser;
		this.roleuser=roleuser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
      String roles=StringUtils.collectionToCommaDelimitedString(roleuser);
      System.out.println(roleuser);
      System.out.println(roles);
      return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return projectUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return projectUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
