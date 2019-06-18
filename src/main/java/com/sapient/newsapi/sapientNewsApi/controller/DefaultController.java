package com.sapient.newsapi.sapientNewsApi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
	
	@RequestMapping("/")
	public String firstPage() {
		return "Hello Sapient application successfully deployed and started...";
	}
	

}
