package com.mokcoding.mysite.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.mokcoding.mysite.domain.MemberRole;

public interface MemberRoleRepository extends ListCrudRepository<MemberRole, Integer>{
	Optional<MemberRole> findByMemberId(String memberId);
	void deleteByMemberId(String memberId);
}
