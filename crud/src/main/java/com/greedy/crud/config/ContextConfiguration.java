package com.greedy.crud.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan("com.greedy.crud")
public class ContextConfiguration {

	@Bean
	public MessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource messageSource
		 = new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("classpath:message/message");
		
		return messageSource;
	}
}
