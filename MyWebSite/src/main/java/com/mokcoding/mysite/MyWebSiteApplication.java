package com.mokcoding.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class MyWebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWebSiteApplication.class, args);
	}

}
