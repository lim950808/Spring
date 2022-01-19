package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService es;
	
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController Start list...");
		int total = es.total(); //Emp Count -> 14
		System.out.println("EmpController total=>" + total);
		
		System.out.println("currentPage=>" + currentPage);
		//						14	  null(0,1,...)
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart()); //시작시 1
		emp.setEnd(pg.getEnd());	 //시작시 10
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()=>" + listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "list";
	}
	
	@GetMapping(value = "detail")
	public String detail(int empno, Model model) {
		//Emp emp = es.detail(emp)
		//Dao -> ed.detail(emp)
		//mapper --> tkEmpSelOne, empno
		System.out.println("EmpConotroller Start detail...");
		Emp emp =es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "detail";
	}
	
	@GetMapping(value = "updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController Start updateForm...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "updateForm";
	}
	
	@PostMapping(value = "update")
	public String update(Emp emp, Model model) {
		int uptCnt = es.update(emp);
		System.out.println("es.update(emp) Count-->" + uptCnt);
		model.addAttribute("uptCnt", uptCnt);				// Test Controller간 Date 전달
		model.addAttribute("kk3", "Message Test");	// Test Controller간 Date 전달
		
		//return "redirect:list";
		return "forward:list";
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		//Emp emp = null;
		//관리자 사번만 Get
		List<Emp> empList = es.listManager();
		System.out.println("EmpController writeForm list.size()->" + empList.size());
		model.addAttribute("empMngList", empList); //emp Manager List
		//부서(코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList); //dept
		
		return "writeForm";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("EmpController Start write...");
		//System.out.println("emp.getHiredate->"+emp.getHiredate());
		// Service, Dao , Mapper명[insertEmp] 까지 -> insert
		int result = es.insert(emp);
		if(result > 0) return "redirect:list";
		else {
			model.addAttribute("msg", "입력 실패 확인해보세요");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value = "confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if(emp != null) {
			model.addAttribute("msg", "중복된 사번입니다.");
			return "forward:writeForm";
		}else {
			model.addAttribute("msg", "사용 가능한 사번입니다.");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value = "delete")
	public String delete(int empno, Model model) {
		System.out.println("EmpController Start delete...");
		int result = es.delete(empno);
		return "redirect:list";
	}
}
