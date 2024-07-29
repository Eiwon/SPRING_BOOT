package com.mokcoding.mysite.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {
	
	@Id @GeneratedValue
	private int boardId; // BOARD_ID
	
	private String boardTitle; // BOARD_TITLE
	private String boardContent; // BOARD_CONTENT
	private String memberId; // MEMBER_ID

	@Column(name = "boardDateCreated", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime boardDateCreated; // BOARD_DATE_CREATED
	@ColumnDefault("0")
	private int replyCount; // REPLY_COUNT;

	
}
