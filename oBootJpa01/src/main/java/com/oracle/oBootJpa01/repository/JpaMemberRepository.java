package com.oracle.oBootJpa01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa01.domain.Member;

public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	//JDBC에선 DataSource, JPA에선 EntityManager
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		// JPA 저장
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("SELECT a FROM Member a", Member.class).getResultList();
		return memberList;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname = name + '%';
		System.out.println("JpaMemberRepository findByNames name->" + name);
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE name LIKE :name", Member.class).setParameter("name", pname).getResultList();
		System.out.println("JpaMemberRepository memberList.size()->" + memberList.size());
		return memberList;
	}

}
