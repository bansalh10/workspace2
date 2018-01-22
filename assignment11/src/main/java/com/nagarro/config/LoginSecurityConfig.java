//package com.nagarro.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//		authenticationMgr.inMemoryAuthentication()
//			.withUser("a")
//			.password("a")
//			.authorities("ROLE_USER");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/login.html","/assets/**").permitAll()
//			.antMatchers("/flightsearch.html").access("hasRole('ROLE_USER')")
//			.antMatchers("/displayflights.html").access("hasRole('ROLE_USER')")
//			.antMatchers("/**").access("hasRole('ROLE_USER')")
//			.and()
//				.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
//				.defaultSuccessUrl("/flightsearch.html")
//				.failureUrl("/login.html?error")
//				.usernameParameter("username").passwordParameter("password")				
//			.and()
//				.logout().logoutSuccessUrl("/login.html?logout"); 
//		
//	}
//}
