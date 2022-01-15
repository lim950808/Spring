package com.oracle.oBootJpa02.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		//팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		//회원저장
		member.setTeam(team); //단방향 연관관계 설정, 참조 저장
		em.persist(member);
		return member;
	}
	
	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
		return memberList;
	}

	@Override
	public Member findByMember(Long id) {
		Member member = em.find(Member.class, id); //(Entity, PK) -> 쿼리문 상관없이 알아서 만들어줌.
		return member;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		System.out.println("JpaMemberRepository updateByMember member.getId()->" + member.getId());
		Member member3 = em.find(Member.class, member.getId());
		if(member3 != null) {
			//팀 저장
			System.out.println("JpaMemberRepository updateByMember member.getIeamid()->" + member.getTeamid());
			Team team = em.find(Team.class, member.getTeamid());
			if(team != null) {
				System.out.println("JpaMemberRepository updateByMember member.getTeamname()->" + member.getTeamname());
				team.setName(member.getTeamname());
				em.persist(team);
			}
			//회원 저장
			System.out.println("JpaMemberRepository updateByMember member.getName()->" + member.getName());
			member3.setTeam(team); //단방향 연관관계 설정, 참조 저장
			member3.setName(member.getName()); //단방향 연관관계 설정, 참조 저장
			em.persist(member3);
			System.out.println("JpaMemberRepository updateByMember member.getName()->" + member.getName());
			result = 1;
		}else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist...");
		}
		return result;
	}

}
