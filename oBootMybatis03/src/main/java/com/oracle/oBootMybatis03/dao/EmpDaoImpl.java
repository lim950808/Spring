package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
//@Repository를 하면 bean을 안 만들어도 됨.
@Repository
public class EmpDaoImpl implements EmpDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int total() {
		int tot = 0;
		System.out.println("EmpDaoImpl Start total...");
		
		//Result => 14
		try { 
			tot = session.selectOne("tkEmpTotal"); //태광 emp테이블의 total
			System.out.println("EmpDaoImpl total tot->" + tot); //selectOne ->  한 건 조회
		}catch (Exception e) {
			System.out.println("EmpDaoImpl total Exception->" + e.getMessage());
		}
		return tot;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp Start...");
		try {
			//	Naming Rule					Map ID		 parameter
			empList = session.selectList("tkEmpListAll3", emp); //selectList -> 여러건 조회
		}catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpDaoImpl detail start...");
		Emp emp = new Emp();
		try {
			//						  mapper ID	, Parameter
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname->" + emp.getEname());
		}catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->" + e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl detail start...");
		int uptCnt = 0;
		try {
			uptCnt = session.update("TKempUpdate", emp);
		}catch (Exception e) {
			System.out.println("EmpDaoImpl update Exception->" + e.getMessage());
		}
		return uptCnt;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listManager Start...");
		try {
			//	emp 관리자만 Select				Naming Rule
			empList = session.selectList("tkSelectManager");
		}catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insert start...");
		try {
			result = session.insert("insertEmp", emp);
		}catch (Exception e) {
			System.out.println("EmpDaoImpl insert Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		System.out.println("EmpDaoImpl delete start...");
		try {
			result = session.delete("deleteEmp", empno);
			System.out.println("EmpDaoImpl delete result->" + result);
		}catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpDaoImpl listEmpDept Start...");
		List<EmpDept> empDept = null;
		try {
			empDept = session.selectList("TKlistEmpDept");
			System.out.println("EmpDaoImp listEmpDept empDept.size()->" + empDept.size());
		}catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpDept Exception->" + e.getMessage());
		}
		return empDept;
	}

}
