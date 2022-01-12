package com.oracle.oBootHello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootHello.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	//MemberRepository memberRepository = new MemoryMemberRepository();
	@Autowired //위에꺼처럼 쓰는 대신 @Autowired를 이용해서 DI방식으로 연결시켜줌.
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
}
