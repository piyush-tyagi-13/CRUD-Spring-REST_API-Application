package com.azzbeeter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter 
{
	/*
	 * @Autowired private DataSource securityDataSource;
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		/*
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * auth.inMemoryAuthentication()
		 * .withUser(users.username("azzbeeter").password("test123").roles("MANAGER",
		 * "EMPLOYEE"))
		 * .withUser(users.username("timmy").password("test123").roles("EMPLOYEE",
		 * "ADMIN"))
		 * .withUser(users.username("larry").password("test123").roles("EMPLOYEE"))
		 * .withUser(users.username("burk").password("test123").roles("EMPLOYEE","ADMIN"
		 * ,"MANAGER"));
		 */
		//super.configure(auth);
		
		// add our users for in memory authentication
		
				UserBuilder users = User.withDefaultPasswordEncoder();
				
				auth.inMemoryAuthentication()
					.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
					.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
					.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		//code without role restrictions
		/*
		 * http.authorizeRequests(). anyRequest(). authenticated() .and() .formLogin()
		 * .loginPage("/showLoginPage")
		 * .loginProcessingUrl("/authenticateUser").permitAll()
		 * .and().logout().permitAll();
		 */
		
		//code with role restrictions
		/*
		 * http.authorizeRequests() .antMatchers("/").hasAnyRole("EMPLOYEE")
		 * .antMatchers("/leaders/**").hasRole("MANAGER")
		 * .antMatchers("/admin-portal/**").hasRole("ADMIN") .and() .formLogin()
		 * .loginPage("/showLoginPage")
		 * .loginProcessingUrl("/authenticateUser").permitAll()
		 * .and().logout().permitAll()
		 * .and().exceptionHandling().accessDeniedPage("/access-denied");
		 */
		
		// secures all REST endpoints under "/api/customers"
		http.authorizeRequests()
		.antMatchers("/api/customers/**").authenticated()
		.and()
		.httpBasic()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
}
