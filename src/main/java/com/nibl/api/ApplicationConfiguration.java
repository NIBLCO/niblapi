package com.nibl.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfiguration {
    
	private static Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                log.info("Enabling CORS");
                // TODO Restrict this to the specific host and port of the UI app on localhost
                registry.addMapping("/**").allowedMethods("PUT","POST","GET").allowedHeaders("Content-Type"); //.allowedOrigins("http://localhost:8080");
            }
        };
    }
}
