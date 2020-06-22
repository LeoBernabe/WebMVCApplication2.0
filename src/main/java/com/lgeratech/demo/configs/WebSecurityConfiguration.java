package com.lgeratech.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
    	auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    } 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
			.antMatchers("/","/home","/profile").hasAnyAuthority("Admin")
			.antMatchers("/addUser" , "/addUser/**").permitAll()
			.anyRequest().permitAll()
	        .and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("username").passwordParameter("password")
			.and()
		.logout()
			.permitAll();
    	
    	http.csrf().disable();
    	http.headers().frameOptions().sameOrigin();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	  return new BCryptPasswordEncoder();
    }
}
