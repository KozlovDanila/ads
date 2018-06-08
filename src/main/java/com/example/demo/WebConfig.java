package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("org.baeldung.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean
	public ViewResolver resourceBundleViewResolver() {
		ResourceBundleViewResolver bean = new ResourceBundleViewResolver();
		bean.setBasename("views");
		return bean;
	}

//	@Bean
//	public ViewResolver xmlViewResolver() {
//		XmlViewResolver bean = new XmlViewResolver();
//		bean.setLocation(new ClassPathResource("views.xml"));
//		return bean;
//	}
}