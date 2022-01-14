package com.oracle.oBootJpa01.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
//JPA에선 dto대신 entity라고 부름.
@Entity
public class Member { //Member => table명.. entity명과 table명은 다를수있다.
	//@Id 해줌으로서 Id를 Primary key로 설정함.
	@Id
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
