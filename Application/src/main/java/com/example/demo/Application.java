package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		
		CustomerRepository repository = 
				SpringApplication.run(Application.class, args)
				.getBean(CustomerRepository.class);
		
		Customer customer = new Customer();
		customer.setFirstname("qwer");
		customer.setLastname("asdf");
		
		repository.save(customer);
		
		
	}

}
