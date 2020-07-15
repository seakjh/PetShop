package com.pet.model.shopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.exception.DMLException;
import com.pet.model.order.OrderSummary;
import com.pet.model.order.OrderSummaryDAO;
import com.pet.model.receiver.Receiver;
import com.pet.model.receiver.ReceiverDAO;

@Service
public class ShoppingService {
	@Autowired
	private ReceiverDAO receiverDAO;
	
	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	//배송자 정보 입력후, pk 를 가져와야한다!
	public void insert(OrderSummary orderSummary) throws DMLException {
		Receiver receiver = orderSummary.getReceiver();
		
		//받는사람 정보 입력
		System.out.println("입력 전의 receiver_id : "+receiver.getReceiver_id());
		System.out.println("입력 후의 receiver_id : "+receiver.getReceiver_id());
		
		if (orderSummary.getSame().equals("yes")) {
			//받을 사람이 회원인 경우엔 receiver_id는 member_id로 대체하면됨
			receiver.setReceiver_id(orderSummary.getMember().getMember_id());
		}
		else {
			//받을 사람이 회원이 아닌 경우엔 receiver_id는 receiverDAO.insert 
			//후에 반환되는 pk를 대체하면됨
			receiverDAO.insert(receiver);
		}
		
		//주문 정보 입력
		orderSummaryDAO.insert(orderSummary);
	}
}
















