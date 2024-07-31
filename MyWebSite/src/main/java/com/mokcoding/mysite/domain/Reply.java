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
public class Reply {
   
   @Id @GeneratedValue
   private int replyId;
   private int boardId;
   private String memberId;
   private String replyContent;
   
   @Column(name = "regDate", nullable = false, updatable = true)
   @CreationTimestamp
   private LocalDateTime replyDateCreated;
   
} // end Reply
