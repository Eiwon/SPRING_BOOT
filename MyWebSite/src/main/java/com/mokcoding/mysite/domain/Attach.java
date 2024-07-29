package com.mokcoding.mysite.domain;

import java.util.Date;

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
   @Column(name = "attachDateCreated", nullable = false, updatable = false)
   @CreationTimestamp
   private Date attachDateCreated;
}

