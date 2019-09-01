package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	 @Autowired
	 private UserDetailServiceImpl userDetailService;
	 
	 
	 @Bean
	 public AuthenticationProvider authenticationProvider()
	 {
		 DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		 provider.setUserDetailsService(userDetailService);
		 provider.setPasswordEncoder(new BCryptPasswordEncoder());
		 provider.setAuthoritiesMapper(authoritiesMapper());
		 return provider;
	 }

	 @Bean 
	 public GrantedAuthoritiesMapper authoritiesMapper()
	 {
		 SimpleAuthorityMapper authorityMapper=new SimpleAuthorityMapper();
		 authorityMapper.setConvertToLowerCase(true);
		 authorityMapper.setDefaultAuthority("admin");
		 return authorityMapper;
	 }
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception
	 {
		 http
		     .csrf().disable()
		     .authorizeRequests().antMatchers("/login","/static/**","/css/*","/js/*").permitAll()
		     .anyRequest().authenticated()
		     .and()
		     .formLogin()
		     .loginPage("/login").permitAll()
		     .and()
		     .logout().invalidateHttpSession(true)
		     .clearAuthentication(true)
		     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		     .logoutSuccessUrl("/logout-sucess").permitAll();
		   
	 }
}
