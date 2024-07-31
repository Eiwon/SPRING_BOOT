package com.mokcoding.mysite.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MemberRole {
   
   @Id @GeneratedValue
   private int roleId;
   private String memberId;
   private String roleName;
   
   @PrePersist
   private void setDefaultValues() {
      if(this.roleName == null) {
         this.roleName = "ROLE_MEMBER";
      }
   }
}
