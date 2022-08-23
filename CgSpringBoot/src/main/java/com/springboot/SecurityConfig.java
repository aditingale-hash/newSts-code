package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
   private MyUserDetailsService myUserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(getAuthProvider());
		/*
		 * auth.inMemoryAuthentication() .withUser("aditi")
		 * .password(getPassEncoder().encode("1234")) .authorities("MANAGER") .and()
		 * .withUser("RAM") .password(getPassEncoder().encode("Ram"))
		 * .authorities("ACCOUNTANT");
		 */
		
	}
	
	private AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserDetailsService);
		auth.setPasswordEncoder(getPassEncoder());
		return auth;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/employee").hasAnyAuthority("MANAGER")		
				.antMatchers(HttpMethod.POST,"/employee/{did}").authenticated()
				.antMatchers(HttpMethod.GET,"/employee/{id}").authenticated()
				.antMatchers(HttpMethod.PUT,"/employee/{id}").authenticated()
				.antMatchers(HttpMethod.DELETE,"/employee/{id}").authenticated()
				.antMatchers(HttpMethod.GET,"/employee/salary").hasAnyAuthority("ACCOUNT")
				.antMatchers(HttpMethod.GET,"/employee/city").authenticated()
				.antMatchers(HttpMethod.GET,"/employee/age").authenticated()
				.antMatchers(HttpMethod.GET,"/employee/department/{did}").hasAnyAuthority("ADMIN")
			   .anyRequest().permitAll()
				.and()
				.httpBasic()
				.and()
				.csrf().disable();
				
	}
	@Bean
  public PasswordEncoder getPassEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	
	

}
