package com.oracle.oBootMybatis03.configuration;

import javax.persistence.EntityManager;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootMybatis03.service.EmpService;

@Configuration
public class SpringConfig {

	private final DataSource dataSource;
	private final EntityManager em;
	
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}

	
}
