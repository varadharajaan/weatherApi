package com.sapient.newsapi.sapientNewsApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan(basePackages= {"com.sapient.newsapi"})
public class SapientNewsApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SapientNewsApiApplication.class, args);
	}

	@Override
	public SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
		return builder.sources(SapientNewsApiApplication.class);
	}
	
	/*
	 * @GetMapping("/") public String firstPage() { return
	 * "Hello Sapient application successfully deployed and started...";
	 * 
	 * }
	 */
}
