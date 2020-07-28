package com.pet.log;

import org.aspectj.lang.JoinPoint;

public class MyLogger {
	//aop에 의해 공콩코드가 관여한 지점에 대한 정보 객체
	public void log(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName(); // 공통
		String methodName = joinPoint.getSignature().getName();
		//System.out.println(className+" + "+methodName+" @메서드 호출");
	}
}
