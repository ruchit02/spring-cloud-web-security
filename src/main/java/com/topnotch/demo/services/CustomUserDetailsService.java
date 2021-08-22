package com.topnotch.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.topnotch.demo.models.Authority;
import com.topnotch.demo.models.User;
import com.topnotch.demo.repositories.UserRepository;



public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository usersRepository;
	
	public CustomUserDetailsService(UserRepository usersRepository) {
		
		System.out.println( "\n\n\n TopnotchUserDetails STARTED CONSTRUCTOR \n\n\n" );
		this.usersRepository = usersRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println( "\n\nSEARCHING FOR THE FOLLOWING USER IN THE DATABASE .....");
		System.out.println( "USERNAME | " + username );
		
	    Optional<User> user = usersRepository.findById(username) ;
	    
		if( !user.isPresent() ) { throw new UsernameNotFoundException("No such user found"); }
		
		System.out.println( "\n\nUSER FOUND");
		
		return buildUser( user.get() );
	}
	
	private UserDetails buildUser( User user ) {
		
		List<Authority> authorities = user.getAuthorities() ;
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		for( Authority uAuth :  authorities ) {
			
			grantedAuthorities.add( new SimpleGrantedAuthority( uAuth.getAuthority() ) );
		}
		
		String username = user.getUsername();
		String password = user.getPass_word();
		System.out.println( "\n\nFETCHED FOLLOWING DETAILS ......");
		System.out.println( "EMAIL : " + username );
		System.out.println( "PASSWORD : " + password );
		
		SecurityUser userDetails = new SecurityUser();
		userDetails.setUserName(username);
		userDetails.setPassword(password);
		userDetails.setAuthorities(grantedAuthorities);
		
		System.out.println( "\n\nDELEGATING THE AUTHENTICATION TASK TO THE INTERNAL ___AUTHENTICATION PROVIDER___ ......");
		
		return userDetails;
	}
	
	
}
