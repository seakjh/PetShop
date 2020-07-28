package com.pet.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

//클라이언트의 모든 요청을 대상으로, 세션이 있는지 여부를 체크할 횡단적 관심사항으로 사용할 예정
public class LoginCheckAspect {
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("7 7 ㅓ ㅡ 억");
		
		Class target = joinPoint.getClass();
		System.out.println("Target : " + target.getName());
				
		//세션 존재 여부를 확인해보자!!
		Object[] args = joinPoint.getArgs(); //매개변수들..
		
		HttpSession session = null;
		for (Object arg : args) {
			if(arg instanceof HttpServletRequest) {
				HttpServletRequest request =(HttpServletRequest)arg;
				session = request.getSession();
			}
		}
		
		if (session.getAttribute("member") == null) {
			System.out.println("로그인하지 않았음.");
			//throw new LoginFailException;
		} else {
			System.out.println("로그인 상태임");
		}
//		if (session != null) {
//		}
		
		joinPoint.proceed(); //원래의 메서드 호출
		
		
		
		return null;
	}
}
