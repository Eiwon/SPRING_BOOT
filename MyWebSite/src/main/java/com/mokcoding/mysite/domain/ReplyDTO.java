package com.mokcoding.mysite.domain;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter 
@Setter
@ToString 
public class ReplyDTO {
	private int replyId;
	private int boardId;
	private String memberId;
	private String replyContent;
	private LocalDateTime replyDateCreated;
	
} // end ReplyDTO