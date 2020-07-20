package com.pet.model.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSService {

	@Autowired
	private BoardDAO boardDAO;
	
	public List selectAll() {
		return boardDAO.selectAll();
	}
}
