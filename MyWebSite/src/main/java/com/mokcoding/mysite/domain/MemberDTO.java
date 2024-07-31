package com.mokcoding.mysite.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter 
@Setter
@ToString 
public class MemberDTO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private LocalDateTime regDate;
	private int enabled;
}