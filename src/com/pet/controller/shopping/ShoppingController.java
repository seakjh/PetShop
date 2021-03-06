package com.pet.controller.shopping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pet.controller.common.Pager;
import com.pet.domain.Cart;
import com.pet.domain.Member;
import com.pet.domain.OrderSummary;
import com.pet.domain.Receiver;
import com.pet.model.product.ProductService;
import com.pet.model.shopping.ShoppingService;

@Controller
public class ShoppingController {
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Pager pager;
	
	//장바구니 담기!!
	@RequestMapping(value="/shop/cart/regist",method=RequestMethod.POST)
	public String regist(Model model, Cart cart, HttpSession session) {
		//넘겨받은 상품에 대한 파라미터 정보를 VO에 담은 후 다시 
		//세션에 담자!! 세션에 담았으므로 세션종료까지는 데이터를 유지
		//할 수 있다..
		String view = null;
		System.out.println("부여받은 세션 아이디는"+session.getId());
		if (session.getAttribute("member")==null) {
			model.addAttribute("msg", "로그인하세요");
			view = "view/error";
		}
		else {
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
			view = "view/message";
		}
		return view;
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
	public String goStep1(HttpSession session, HttpServletRequest request) {
		//만일 db관련 작업이 있다면 여기서 처리..
		
		//만일 cartOne이라는 List가 세션에 존재한다면, 없애버리자
		//왜? cartList를 대체하지 않도록
		String referer = request.getHeader("referer");
		try {
			URI uri=new URI(referer);
			System.out.println(uri.getPath());
			if(!uri.getPath().equals("/shop/detail")) {//바로구매가 아니라면
				session.removeAttribute("cartOne");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return "shop/step1";
	}

	//구매2단계 화면 보기 (고객정보, 결제정보)
	@RequestMapping(value="/shop/step2",method=RequestMethod.POST)
	public String goStep2(Model model, HttpSession session,OrderSummary orderSummary) {
		//주문상품정보 (장바구니) 세션이 있으므로 가져잘필요 x
		
		//고객정보 (멤버에 들어있음)세션이 있으므로 가져잘필요 x
		
		//받는자 정보 (파라미터에 있음) 저장 후 페이지에서 출력.
		Receiver receiver = orderSummary.getReceiver();
		System.out.println(receiver.getRname());
		System.out.println(receiver.getRphone());
		System.out.println(receiver.getRaddr());
		
		//jsp에서 보여질 정보 저장
		model.addAttribute("orderSummary", orderSummary);
		
		return "shop/step2";
	}
	
	//구매 3단계 요청 처리 (결제정보 입력 )
	@RequestMapping(value="/shop/step3",method=RequestMethod.POST)
	public String goStep3(Model model, HttpSession session, OrderSummary orderSummary) {
		
		//주문요약 정보 중 누가 샀는지를 결정!!
		Member member = (Member)session.getAttribute("member");
		orderSummary.setMember(member);
		
		//서비스에게 일시키기 
		List<Cart> cartList = (List)session.getAttribute("cartList");
		shoppingService.insert(cartList ,orderSummary);
		
		//장바구니 모두 비우기!!
		session.removeAttribute("cartList"); // 장바구니 내역
		session.removeAttribute("cartOne"); // 바로구매 내역
		
		//내일은 주문 상세도 service에서 처리할 것임!!
		
		model.addAttribute("msg", "받을사람 정보는 "+orderSummary.getReceiver().getReceiver_id());
		model.addAttribute("url", "/");
		
		return "view/message";
	}
	
	//바로구매 요청처리 (장바구니에 1건의 상품을 담는 처리)
	@RequestMapping(value="/shop/buy",method=RequestMethod.POST)
	public String buy(Model model, Cart cart, HttpSession session) {	
		//넘겨받은 상품에 대한 파라미터 정보를 VO에 담은 후 다시 
		//세션에 담자!! 세션에 담았으므로 세션종료까지는 데이터를 유지
		//할 수 있다..
		String view = null;
		System.out.println("부여받은 세션 아이디는"+session.getId());
		if (session.getAttribute("member")==null) {
			model.addAttribute("msg", "로그인하세요");
			view = "view/error";
		}
		else {
			List<Cart> cartOne =(List)session.getAttribute("cartOne");
			if(cartOne==null) {
				//장바구니에 담을 리스트가 최초라면..
				cartOne = new ArrayList<Cart>();
				cartOne.add(cart);
				session.setAttribute("cartOne", cartOne);
			}
			view = "redirect:/shop/step1";
		}
		return view;
	}
	
	//쇼핑 상품 목록
	@RequestMapping(value="/product/list",method=RequestMethod.GET)
	public String getProductList(HttpServletRequest request,Model model) {
		List productList = productService.selectAll();

		model.addAttribute("productList", productList);
		model.addAttribute("pager", pager);//이거 넣기!!!

		pager.init(productList, request);

		return "shop/list";
	}

}














