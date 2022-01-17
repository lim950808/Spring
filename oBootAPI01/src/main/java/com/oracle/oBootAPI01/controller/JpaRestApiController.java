package com.oracle.oBootAPI01.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootAPI01.domain.Member;
import com.oracle.oBootAPI01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
//Controller + ResponseBody
@RestController
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
//@RequiredArgsConstructor -> 지정된 속성들에 대해서만 생성자를 만들어줌.
//밑의 코드 대신 @RequiredArgsConstructor를 써서 한줄로 생성자를 만듬.	
//	public JpaRestApiController (MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	//Bad Api
	@GetMapping("/restApi/v1/members")
	public List<Member> membersV1() {
		System.out.println("JpaRestApiController /restApi/v1/members start...");
		return memberService.getListAllMember();
	}
	
	//Good Api
	@GetMapping("/restApi/v2/members")
	public Result membersV2() {
		List<Member> findMembers = memberService.getListAllMember();
		//자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
		List<MemberRtnDto> memberCollect = findMembers.stream().map(m -> new MemberRtnDto(m.getId(), m.getName())).collect(Collectors.toList());
		return new Result(memberCollect.size(), memberCollect);
		//return new Result(memberCollect);
	}
	// 1. Entity보안
	// 2. 유연성 --> Entity가 API에 의존적 X, 원하는 Data 생성 , 전달 
	// T는 인스턴스를 생성할 때 구체적인 타입으로 변경
	// http://jsonviewer.stack.hu/
	@Data
	@AllArgsConstructor
	class Result<T> {
		private int totCount;
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	class MemberRtnDto {
		private Long id;
		private String name;
	}
	
}
