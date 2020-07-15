package com.pet.model.order;

import com.pet.model.member.Member;
import com.pet.model.receiver.Receiver;

public class OrderSummary {
	private int order_summary_id;
	private Member member;
	private int total_pay;
	private String order_date;
	private String paymethod;
	private String same;
	private Receiver receiver;
	
	public int getOrder_summary_id() {
		return order_summary_id;
	}
	public void setOrder_summary_id(int order_summary_id) {
		this.order_summary_id = order_summary_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getSame() {
		return same;
	}
	public void setSame(String same) {
		this.same = same;
	}
	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
}
