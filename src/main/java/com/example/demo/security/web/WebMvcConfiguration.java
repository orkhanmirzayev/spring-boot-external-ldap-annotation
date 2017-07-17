package com.example.demo.security.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("/home");
//		registry.addViewController("/home").setViewName("home");
//		registry.addViewController("/login").setViewName("login.jsp");
//		registry.addViewController("/admin").setViewName("admin.jsp");
//		registry.addViewController("/sadmin").setViewName("sadmin.jsp");
//	}
}
