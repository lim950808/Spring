package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.SampleVO;
import com.oracle.oBootMybatis03.service.EmpService;
// @Controller + @ResponseBody => @RestController
@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;

	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController Start...");
		String hello = "안녕";
		return hello;
	}
	
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) {
		System.out.println("@RestController deptno->" + deptno);
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(deptno);
		return vo;
	}
	// http://jsonviewer.stack.hu/
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3() {
		System.out.println("@RestController sendVO3 START");
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}
	
	//empnoDelect
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp) { //empno와 ename이 포함된 emp
		System.out.println("@RestController empnoDelete START");
		System.out.println("@RestController empnoDelete emp.getEname()->" + emp.getEname());
		int delStatus = es.delete(emp.getEmpno()); //emp에서 empno를 get
		String delstatusStr = Integer.toBinaryString(delStatus);
		return delstatusStr; //String으로 변환된 값
	}
}
