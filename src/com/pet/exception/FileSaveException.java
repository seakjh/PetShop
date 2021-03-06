package com.pet.exception;

//예외의 원인을 보다 체계적이고 직관적으로 처리하기 위해
//최상위 예외를 바로 사용하지 말고, 개발자가 예외처리 전략을 세워
//체계적이 예외에 대한 로직을 작성하자
public class FileSaveException extends RuntimeException{
	public FileSaveException(String msg) {
		super(msg);
	}
	
	public FileSaveException(Throwable e) {
		super(e);		
	}
	
	
	public FileSaveException(String msg, Throwable e) {
		super(msg, e);
	}
}
