package com.example.demo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/font/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .antMatchers("/login**").access("permitAll")
         .antMatchers("/logout**").access("permitAll")
         .antMatchers("/user/**").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
         .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
         .antMatchers("/sadmin/**").access("hasRole('ROLE_SUPER_ADMIN')")
         .anyRequest().authenticated()
         .and()
         .formLogin().loginPage("/login")
         .defaultSuccessUrl("/welcome")
         .failureUrl("/login?error")
         .usernameParameter("username").passwordParameter("password")
         .and()
         .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
         .clearAuthentication(true)
         .and()
         .exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	@Configuration
	public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter
	{
		@Override
		public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
	        authenticationManagerBuilder
            .ldapAuthentication()
            .userSearchFilter("(uid={0})")
            .userSearchBase("ou=people")
            .groupSearchFilter("uniqueMember={0}")
            .groupSearchBase("ou=users,ou=groups")
            .userDnPatterns("uid={0},ou=people")
            .rolePrefix("ROLE_")
            .contextSource()
            .url("ldap://localhost:10389/o=spark")
            .managerPassword("secret").managerDn("uid=admin,ou=system");
			
		}
	}
}
