package com.topnotch.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.topnotch.demo.formDataAdapter.FormMappedUser;
import com.topnotch.demo.services.TNUserService;

@Controller
public class RegistrationController {

	@Autowired
	private TNUserService userService ;
	
	public RegistrationController() {
		super();
	}
	
	@GetMapping("/mySignup")
	public String displaySignUpForm(Model model) {
		
		System.out.println( "SIGNUP PAGE GET REQUEST REGISTERED" );
		model.addAttribute( "fmUser", new FormMappedUser() );
		return "signup";
	}
	
	@PostMapping("/mySignup")
	public String registerUser( @ModelAttribute @Valid FormMappedUser fmUser, BindingResult errors) {
		
		System.out.println( "SIGNUP PAGE POST REQUEST REGISTERED" );
		userService.registerUser(fmUser) ;
		return "redirect:home" ;
	}
	
	@GetMapping("/myLogin")
	public String loginRequest() {
		
		System.out.println( "LOGIN GET REQUEST REGISTERED" );
		return "login" ;
	}
	
	@PostMapping("/myLogin")
	public String loginUser() {
		
		System.out.println( "LOGIN POST REQUEST REGISTERED" );
		return "home";
	}
	
	@GetMapping("/myHome")
	public String displayHome() {
		
		System.out.println( "DISPLAY HOME REQUEST REGISTERED" );
		return "home" ;
	}
	
	@PostMapping("/myLogout")
	public String logoutRequest() {
		
		System.out.println( "LOGOUT REQUEST REGISTERED" );
		return "login";
	}
}