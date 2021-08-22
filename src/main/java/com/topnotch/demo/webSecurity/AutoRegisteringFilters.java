package com.topnotch.demo.webSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class AutoRegisteringFilters extends AbstractSecurityWebApplicationInitializer {

	//this class will automatically register the springSecurityFilterChain Filter
	//for every URL in my application
		
	//Also adds a ContextLoaderListener that loads my SecurityConfig class
	
	public AutoRegisteringFilters() {
		
	}
}
