package com.mokcoding.mysite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class MainController {

	@GetMapping("/")
	public String main() {
		log.info("main");
		return "HELLO";
	}
	
	
}
