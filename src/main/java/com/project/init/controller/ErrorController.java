package com.project.init.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/error")

public class ErrorController {

	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	
	@RequestMapping("404page")
	public String page404() {
		logger.info("error() in >>>>");
		return "error/404page";
	}
		
	
	
}