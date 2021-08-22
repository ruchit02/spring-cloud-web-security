package com.topnotch.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.topnotch.demo.models.Authority;
import com.topnotch.demo.models.User;

public class PostRegitrationAutoLogin {

	
	
	public PostRegitrationAutoLogin() {
		
	}
	
	public static void login(User user) {
		
		List<Authority> authorities = user.getAuthorities() ;
		List<GrantedAuthority> gAuthorities = new ArrayList<>();
		
		for( Authority auth : authorities ) {
			gAuthorities.add( new SimpleGrantedAuthority( auth.getAuthority() ) ) ;
		}
		
		Authentication token = new UsernamePasswordAuthenticationToken( user.getUsername() , user.getPass_word()) ;
		SecurityContext context = SecurityContextHolder.createEmptyContext() ;
		context.setAuthentication(token);
		
		SecurityContextHolder.setContext( context );
		
		System.out.println( "\n\n" + "LOGIN TOKEN CREATED" );
	}
}
