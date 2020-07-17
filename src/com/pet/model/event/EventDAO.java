package com.pet.model.event;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.domain.Event;
import com.pet.exception.DMLException;

@Repository
public class EventDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	//이벤트 상품등록
	public void insert(Event event) throws DMLException {
		int result = sessionTemplate.insert("Event.insert", event);
		
		if (result == 0) {
			throw new DMLException("이벤트 등록 실패!");
		}
	}
	
	//이벤트 상품 목록 가져오기
	public List selectAll() {
		return sessionTemplate.selectList("Event.selectAll");
	}
	
	//이벤트 1건
	public Event select(int event_id) {
		return sessionTemplate.selectOne("Event.select", event_id);
	}
	
}
