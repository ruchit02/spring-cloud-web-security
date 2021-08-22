package com.topnotch.demo.webSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.topnotch.demo.repositories.UserRepository;
import com.topnotch.demo.services.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepository userRepository ;
	
	public SecurityConfig() {
		System.out.println( "\n\n\n AUTHENTICATION BUILDER STARTED \n\n\n" );
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	
	@Bean
	public UserDetailsService getMyDetailsService() {
		return new CustomUserDetailsService(userRepository) ;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService( getMyDetailsService() ).passwordEncoder(getPasswordEncoder()) ;
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
	    return super.userDetailsServiceBean();
	}
	
	@Bean(name="myAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
				.antMatchers("/mySignup").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/myLogin").defaultSuccessUrl("/myHome",true).permitAll()
				.and()
				.logout().logoutUrl( "/myLogout" ).logoutSuccessUrl("/myLogin").permitAll()
				.and()
				.httpBasic();
	}
}
