package com.mokcoding.mysite.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Attach {
	
	@Id @GeneratedValue
	private int attachId;
	private int boardId;
	private String attachPath;
	private String attachRealName;
	private String attachChgName;
	private String attachExtension;
	
	@Column(name = "attachDateCreated", nullable = false, updatable = true)
	@CreationTimestamp
	private LocalDateTime attachDateCreated;
}
