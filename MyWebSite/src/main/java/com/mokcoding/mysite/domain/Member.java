package com.mokcoding.mysite.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Member implements Serializable{

   private static final long serialVersionUID = 1L;
   
   @Id
   private String memberId;
   private String memberPw;
   private String memberName;
   @Column(name = "regDate", nullable = false, updatable = false)
   @CreationTimestamp
   private Date regDate;
   private int enabled;
}

