package com.pet.exception;
/*
 * throw로 발생시킬 수 있는 예외의 종류
 * 1) Throwable 인터페이스
 * 2) Exception (예외처리를 강요한다 catched Exception)
 * 3) RuntimeException (예외 처리를 강요하지 않음) 
 */
public class DataNotFoundException extends RuntimeException {
	public DataNotFoundException(String msg) {
		super(msg);
	}
	public DataNotFoundException(Throwable e) {
		super(e);
	}
	
	public DataNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
}
