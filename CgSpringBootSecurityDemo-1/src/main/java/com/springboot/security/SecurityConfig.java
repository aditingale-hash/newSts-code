package com.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		
		auth.inMemoryAuthentication()
		.withUser("Harry")
		.password(getPassEncoder().encode("potter"))
	    .authorities("MANAGER")
	    .and()
	    .withUser("ronald")
	    .password(getPassEncoder().encode("weasley"))
	    .authorities("ACCOUNTANT");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/user").permitAll()
		.antMatchers("/hello/public").permitAll()
		.antMatchers("/hello/private").authenticated()
		.antMatchers("/hello").hasAnyAuthority("MANAGER")
		.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		
		
	}
	
    @Bean
	public PasswordEncoder getPassEncoder() {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder;
	}
}
