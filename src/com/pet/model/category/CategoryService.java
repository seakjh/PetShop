package com.pet.model.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.exception.CategoryDMLException;

@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	
	public void regist(Category category) throws CategoryDMLException {
		categoryDAO.insert(category);
	}
}
