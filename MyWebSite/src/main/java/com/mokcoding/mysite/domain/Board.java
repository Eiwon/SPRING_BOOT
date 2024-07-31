package com.mokcoding.mysite.domain;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Board {
	
	@Id @GeneratedValue
	private int boardId; // BOARD_ID
	
	private String boardTitle; // BOARD_TITLE
	private String boardContent; // BOARD_CONTENT
	private String memberId; // MEMBER_ID
	
	@Column(name = "boardDateCreated", nullable = false, updatable = true)
	@CreationTimestamp
	private LocalDateTime boardDateCreated; // BOARD_DATE_CREATED1
	@ColumnDefault("0")
	private int replyCount; // REPLY_COUNT;
	
} // end Board
