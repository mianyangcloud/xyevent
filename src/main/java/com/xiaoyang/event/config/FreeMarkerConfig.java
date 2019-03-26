package com.xiaoyang.event.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreeMarkerConfig {
	
	@Value("${domain}")
	private String domain;
	
	@Autowired
	private freemarker.template.Configuration configuration;

	@PostConstruct
	public void setConfigure() throws Exception {
		 configuration.setSharedVariable("base", domain);
	}
}
