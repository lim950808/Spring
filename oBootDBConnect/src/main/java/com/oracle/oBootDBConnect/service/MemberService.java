package com.oracle.oBootDBConnect.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootDBConnect.domain.Member1;
import com.oracle.oBootDBConnect.repository.MemberRepository;


@Service
public class MemberService {
	private final MemberRepository memberRepository;
	//MemberRepository memberRepository = new MemoryMemberRepository();
	@Autowired //위에꺼처럼 쓰는 대신 @Autowired를 이용해서 DI방식으로 연결시켜줌.
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	public Long join(Member1 member1) {
		System.out.println("MemberService join start...");
		memberRepository.save(member1);
		return member1.getId();
	}
	
	//전체회원 조회
	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = null;
		memList = memberRepository.findAll();
		System.out.println("memberList.size()->" + memList.size());
		return memList;
	}
}
