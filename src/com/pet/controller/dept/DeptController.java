package com.pet.controller.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.model.dept.DeptDAO;

@Controller
public class DeptController {
	
	@Autowired
	private DeptDAO deptDAO;
	
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public String selectAll(Model model) {
		List deptList = deptDAO.selectAll();
		model.addAttribute("deptList", deptList);
		
		return "view/deptList";
	}
}
