package com.example.demo.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/", "/index").permitAll()
			.antMatchers("/user/index").hasRole("user")
			.antMatchers("/admin").hasRole("admin")
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?loginError=true")
			.and()
			.logout()
			.logoutUrl("/")
			;
	
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("password")
				.roles("user")
				.and()
				.withUser("admin")
				.password("password")
				.roles("admin");
		
	}
	
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */

}
