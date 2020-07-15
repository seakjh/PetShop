package com.pet.controller.shopping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pet.model.product.Cart;

@Controller
public class ShoppingController {
	//장바구니 담기!!
	@RequestMapping(value="/shop/cart/regist",method=RequestMethod.POST)
	public String regist(Model model, Cart cart, HttpSession session) {
		//넘겨받은 상품에 대한 파라미터 정보를 VO에 담은 후 다시 
		//세션에 담자!! 세션에 담았으므로 세션종료까지는 데이터를 유지
		//할 수 있다..
		System.out.println("부여받은 세션 아이디는"+session.getId());
		
		List<Cart> cartList =(List)session.getAttribute("cartList");
		if(cartList==null) {
			//장바구니에 담을 리스트가 최초라면..
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		int count = 0; //존재여부를 체크하기 위한 카운터 변수 존재할 경우 0보다 큼
		Cart obj = null;
		
		//장바구니에 등록된 List내에 존재하는 제품인지 체크
		for (int i=0; i<cartList.size(); i++) {
			obj = cartList.get(i);
			//이미 존재하면..
			if (cart.getProduct_id() == obj.getProduct_id()) {
				count++;
				obj.setEa(obj.getEa()+1);
			}
		}
		if (count == 0){
			cart.setEa(1);//상세보기에서 장바구니에 담을때는 1개가 된다!!
			cartList.add(cart);//장바구니 리스트에 상품 추가!!							
		}
		model.addAttribute("msg", "장바구니에 "+cart.getProduct_name()+" 담겼습니다");
		model.addAttribute("url", "/shop/cart/list");
		return "view/message";
	}
	
	//장바구니 목록 가져오기!!
	@RequestMapping(value="/shop/cart/list",method=RequestMethod.GET)
	public String getList(Model model,HttpSession session) {
		//로그인 하지 않은 회원인 경우, 거부처리
		String view = null;
		if(session.getAttribute("member") == null) {
			model.addAttribute("msg", "로그인하세요");
			model.addAttribute("url", "/member/login.jsp");
			view = "view/message";
		}
		else {
			view = "redirect:/shop/cart.jsp";			
		}
		return view;
	}
	

	//장바구니에서 상품 1개 삭제
	@RequestMapping(value="/shop/cart/del",method=RequestMethod.GET)
	public String removeOne(@RequestParam int product_id, HttpSession session, Model model) {
		//cartList에서 product_id 를 조회하여 해당 객체를 List에서 제거한다.
		List<Cart> cartList = (List) session.getAttribute("cartList");
		
		for (int i=0; i<cartList.size(); i++) {
			Cart cart = cartList.get(i);
			if (product_id == cart.getProduct_id()) {
				cartList.remove(cart); //리스트에서 상품삭제
			}
		}
		model.addAttribute("msg", "삭제완료");
		model.addAttribute("url", "/shop/cart/list");
		
		return "view/message";
	}
	
	//장바구니 삭제
	@RequestMapping(value="/shop/cart/remove",method=RequestMethod.GET)
	public String removeAll(HttpSession session, Model model) {
		session.removeAttribute("cartList");
		
		model.addAttribute("msg", "장바구니 삭제완료");
		model.addAttribute("url", "/shop/cart/list");
		
		return "view/message";
	}

	@RequestMapping(value="/shop/cart/edit",method=RequestMethod.GET)
	public String edit(HttpSession session, Model model, Cart cart) {
		List<Cart> cartList = (List)session.getAttribute("cartList");
		
		//List에 들어있는 cart객체 끄집어 내기
		for (int i=0; i<cartList.size(); i++) {
			Cart obj = cartList.get(i);
			if (obj.getProduct_id() == cart.getProduct_id()) {
				obj.setEa(cart.getEa()); //갯수 대체
			}
		}
		
		model.addAttribute("msg", "장바구니 수정완료");
		model.addAttribute("url", "/shop/cart/list");
		
		return "view/message";
	}
	//구매1단계 화면 보기 (고객정보, 결제정보)
	@RequestMapping(value="/shop/step1",method=RequestMethod.GET)
	public String goStep1() {
		//만일 db관련 작업이 있다면 여기서 처리..
		return "shop/step1";
	}
}
