package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Customer 클래스는 JPA 엔티티로, DB의 테이블에 매핑

@Data
@Entity
// ㄴ @Table과 같음
public class Customer {

	@Id @GeneratedValue
	private Long id;
	
	private String firstname;
	private String lastname;
	
	
}
