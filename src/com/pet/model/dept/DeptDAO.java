package com.pet.model.dept;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List selectAll() {
		return sessionTemplate.selectList("Dept.selectAll");
	}
}
