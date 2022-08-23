package com.lms.api;

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

import com.lms.api.security.services.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(getAuthProvider());
		/*
		 * auth.inMemoryAuthentication() .withUser("harry")
		 * .password(getPassEncoder().encode("potter")) .authorities("ADMIN") .and()
		 * .withUser("ronald") .password(getPassEncoder().encode("weasley"))
		 * .authorities("USER");
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
				.antMatchers(HttpMethod.POST,"/user/register").permitAll()
				.antMatchers(HttpMethod.GET,"/hello/login").authenticated()
				.antMatchers(HttpMethod.PUT,"/user/update/{id}").authenticated()
				.antMatchers(HttpMethod.POST,"/learningtrack").authenticated()
				.antMatchers(HttpMethod.POST,"/course/{learningid}").authenticated()
				.antMatchers(HttpMethod.GET,"/learning-track").authenticated()
				.antMatchers(HttpMethod.GET,"/user/learning-track").authenticated()
				.antMatchers(HttpMethod.POST,"/enroll/user/{learningid}/{userid}").authenticated()
				.antMatchers(HttpMethod.POST,"/answer").authenticated()
				.antMatchers(HttpMethod.POST,"/question").authenticated()
				.antMatchers(HttpMethod.POST,"/question/answer/{qid}/{aid}").authenticated()
				.antMatchers(HttpMethod.POST,"/topic").authenticated()
				.antMatchers(HttpMethod.POST,"/topic/question/{tid}/{qid}").authenticated()
				.antMatchers(HttpMethod.POST,"/topic").authenticated()
				.antMatchers(HttpMethod.GET,"/answer/{qid}").authenticated()
				.antMatchers(HttpMethod.GET,"/topics").permitAll()
				.antMatchers(HttpMethod.GET,"/question/{tid}").authenticated()
				.antMatchers(HttpMethod.GET,"/stats/topic").permitAll()
				.antMatchers(HttpMethod.PUT,"/question/put/{qid}").authenticated()
				.antMatchers(HttpMethod.PUT,"/answer/{aid}").authenticated()
				.antMatchers(HttpMethod.PUT,"/answer/update_likes/{aid}").authenticated()
				.antMatchers(HttpMethod.PUT,"/question/update_likes/{qid}").authenticated()
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
