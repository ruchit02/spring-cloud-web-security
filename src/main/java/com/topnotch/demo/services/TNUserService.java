package com.topnotch.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.topnotch.demo.formDataAdapter.FormMappedUser;
import com.topnotch.demo.models.Authority;
import com.topnotch.demo.models.User;
import com.topnotch.demo.repositories.AuthorityRepository;
import com.topnotch.demo.repositories.UserRepository;

@Configuration
public class TNUserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private AuthorityRepository authRepository ;
	
	@Autowired
	private PasswordEncoder encoder ;
	
	public TNUserService() {
	}
	
	
	public void registerUser( FormMappedUser fmUser ) {
		
		System.out.println( "\n\nFETCHING DATA FROM THE SIGN UP FORM ......" );
		System.out.println( "FM-USER | " + fmUser.getFirst_name() );
		System.out.println( "FM-USER | " + fmUser.getLast_name() );
		System.out.println( "FM-USER | " + fmUser.getEmail() );
		System.out.println( "FM-USER | " + fmUser.getPass_word() );
		System.out.println( "\n\n" );
		
		
		User user = new User();
		user.setFirst_name( fmUser.getFirst_name() );
		user.setLast_name( fmUser.getLast_name() );
		user.setUsername( fmUser.getEmail() );
		user.setPass_word( encodePassword( fmUser.getPass_word() ) );
		
		
		System.out.println( "\n\nFILLING UP THE USER ENTITY  ......" );
		System.out.println( "USER | " + user.getFirst_name() );
		System.out.println( "USER | " + user.getLast_name() );
		System.out.println( "USER | " + user.getUsername() );
		System.out.println( "USER | " + user.getPass_word() );
		System.out.println( "\n\n" );
		
		String role = "USER" ;
		
		Authority authority = new Authority();
		authority.setUsername( user );
		authority.setAuthority( role );
		
		System.out.println( "\n\nASSIGNING ROLE TO THE USER ...." );
		System.out.println( "USER | " + authority.getAuthority() );
		
		List<Authority> authorities = new ArrayList<Authority>() ;
		authorities.add( authority );
		
		user.setAuthorities( authorities );
		
		userRepository.saveAndFlush( user );
		
		System.out.println( "\n\nUSER SAVED IN THE DATABASE" );
		
		authRepository.saveAndFlush( authority );
		
		System.out.println( "\n\nAUTHORITY SAVED IN THE DATABASE" );
	}
	
	private String encodePassword( String password ) {
		
		return encoder.encode( password );
	}
}
