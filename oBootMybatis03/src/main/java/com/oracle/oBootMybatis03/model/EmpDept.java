package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

//Join목적
@Getter
@Setter
public class EmpDept {
	
	//Emp용
	private int 	empno;			private String 	ename;
	private String 	job;			private int 	mgr;
	private String 	hiredate;		private int 	sal;
	private int 	comm;			private int 	deptno;
	
	//Dept용 - Dept가 많다는 가정하에 만듬.
	private String 	dname;
	private String 	loc;
	
}
