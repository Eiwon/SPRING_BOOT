package com.mokcoding.mysite.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.mokcoding.mysite.domain.Member;

public interface MemberRepository extends ListCrudRepository<Member, String>{
	
}
