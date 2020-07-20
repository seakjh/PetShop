package com.pet.controller.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pet.domain.Member;
import com.pet.exception.DMLException;
import com.pet.exception.DataNotFoundException;
import com.pet.model.member.MemberService;
import com.pet.model.order.OrderService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/member/regist", method=RequestMethod.POST)
	public String regist(Model model, Member member) {
		
		memberService.insert(member);
		model.addAttribute("msg", "회원가입을 축하드려요");
		model.addAttribute("url", "/");
		
		return "view/message";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Member loginCheck(Member member, HttpSession session) {
		Member obj = memberService.loginCheck(member);
		
		//로그인 성공시 세션에 멤버정보를 담자
		session.setAttribute("member", obj);
		
		// VO를 자동으로 json형식으로 변환해주는 라이브러리가 있음..

		return obj;
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String logout(Model model,HttpSession session) {
		//세션을 소멸시킨다
		session.invalidate(); //현재 클라이언트의 관련된 세션을 무효화 시킴.
		//주의 : 객체를 소멸하는건 아니다.. 자바에서는 객체를 죽일수 없어서
		model.addAttribute("msg", "로그아웃 됨");
		model.addAttribute("url", "/");
		
		return "view/message";
	}
	
	@RequestMapping(value = "/member/mypage", method = RequestMethod.GET)
	public String getMyPage(Model model, HttpSession session) {		
		//회원정보 MemberService
		Member member = (Member)session.getAttribute("member");
		model.addAttribute("member", member);
		
		//결제내역 OrderService
		List orderList = orderService.selectAllByMember(member);
		model.addAttribute("orderList", orderList);
		
		//상담내역 BoardService
		
		return "member/mypage";
	}
	
	@ExceptionHandler({DataNotFoundException.class})
	@ResponseBody
	public String handle(DataNotFoundException e) {
		System.out.println("로그인 실패 메서드");
		StringBuilder msg = new StringBuilder();
		msg.append("{");
		msg.append("\"code\":0,");
		msg.append("\"msg\":\""+e.getMessage()+"\"");
		msg.append("}");
		return msg.toString();
	}
	
	@ExceptionHandler({DMLException.class})
	public ModelAndView handle(DMLException e) {
		ModelAndView mav = new ModelAndView();
		
		//파일 업로드 에러인 경우
		if(e !=null) {
			mav.addObject("e", e);//에러 객체 자체를 담는다!!
			mav.addObject("msg", e.getMessage());//에러 객체 자체를 담는다!!
		}
		//입력 에러엔 경우 
		mav.setViewName("view/error");
		return mav;
	}

}