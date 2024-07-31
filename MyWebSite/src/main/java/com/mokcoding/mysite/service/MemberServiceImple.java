package com.mokcoding.mysite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokcoding.mysite.domain.Member;
import com.mokcoding.mysite.domain.MemberDTO;
import com.mokcoding.mysite.domain.MemberRole;
import com.mokcoding.mysite.repository.MemberRepository;
import com.mokcoding.mysite.repository.MemberRoleRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberServiceImple implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberRoleRepository memberRoleRepository;

	@Transactional(value = "transactionManager")
	@Override
	public int createMember(MemberDTO memberDTO) {
		log.info("createMember()");
		Member member = memberRepository.save(toEntity(memberDTO));
		log.info(member);
	
		MemberRole memberRole = new MemberRole();
		memberRole.setMemberId(memberDTO.getMemberId());
		MemberRole savedRole = memberRoleRepository.save(memberRole);
		log.info(savedRole);
		return 1;
	}

	@Override
	public boolean checkMemberId(String memberId) {
		log.info("checkMemberId()");
		
		return memberRepository.existsById(memberId);
	}
	
	@Override
	public MemberDTO getMemberById(String memberId) {
		log.info("getMemberById()");
		return toDTO(memberRepository.findById(memberId).get());
	}


	@Override
	public int updateMember(MemberDTO memberDTO) {
		log.info("updateMember()");
		Optional<Member> memberOptional = memberRepository.findById(memberDTO.getMemberId());
		Member member = memberOptional.get();
		member.setMemberPw(memberDTO.getMemberPw());
		member.setMemberName(memberDTO.getMemberName());
		memberRepository.save(toEntity(memberDTO));
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public int deleteMember(String memberId) {
		log.info("deleteMember()");
		
		memberRepository.deleteById(memberId);

		memberRoleRepository.deleteByMemberId(memberId);
		return 1;
	}
	
	
    public MemberDTO toDTO(Member member) {
    	MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setMemberPw(member.getMemberPw());
        memberDTO.setMemberName(member.getMemberName());
        memberDTO.setRegDate(member.getRegDate());
        memberDTO.setEnabled(member.getEnabled());
        return memberDTO;
    }

    public Member toEntity(MemberDTO memberDTO) {
        Member entity = new Member();
        entity.setMemberId(memberDTO.getMemberId());
        entity.setMemberPw(memberDTO.getMemberPw());
        entity.setMemberName(memberDTO.getMemberName());
        entity.setRegDate(memberDTO.getRegDate());
        entity.setEnabled(memberDTO.getEnabled());
        return entity;
    }



}
