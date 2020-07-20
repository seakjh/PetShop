package com.pet.controller.cs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.controller.common.Pager;
import com.pet.domain.Board;
import com.pet.model.cs.CSService;

@Controller
public class CSController {
	
	@Autowired
	private CSService csService;
	
	@Autowired
	private Pager pager;
	
	@RequestMapping(value = "/cs/list", method = RequestMethod.GET)
	public String selectAll(Model model, HttpServletRequest request) {
		
		List<Board> csList = csService.selectAll();
		
		pager.init(csList, request);
		model.addAttribute("csList", csList);
		model.addAttribute("pager", pager);
		return "cs/list";
	}
}
