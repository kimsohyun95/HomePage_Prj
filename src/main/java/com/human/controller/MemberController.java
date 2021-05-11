package com.human.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.service.MemberService;
import com.human.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	//회원가입 GET
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void getRegister() throws Exception{
		System.out.println("getRegister()");
		
		logger.info("get register");
	}
	
	//회원가입 POST
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception{
		System.out.println("postRegister()");
		
		logger.info("post register");
		
		service.register(vo);
		
		return null;
		
	}

	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		System.out.println("login()");
		
		logger.info("post login");
		
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {
			session.setAttribute("member", login);
		}
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		System.out.println("logout()");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원정보 수정 view
	@RequestMapping(value="/memberUpdateView", method=RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		System.out.println("registerUPdateView()");
		
		return "member/memberUpdateView";
	}
	
	//회원정보 수정
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		
		service.memberUpdate(vo);
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원탈퇴 view
	@RequestMapping(value="/memberDeleteView", method=RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		System.out.println("memberDeleteView()");
		
		return "member/memberDeleteView";
	}
	
	//회원탈퇴
	@RequestMapping(value="/memberDelete", method=RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		System.out.println("memberDelete()");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		//세션에 있는 비밀번호
		String sessionPw = member.getM_pw();
		//vo로 들어오는 비밀번호
		String voPw = vo.getM_pw();
		
		if(!(sessionPw.equals(voPw))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/memberDeleteView";
		}
		service.memberDelete(vo);
		session.invalidate();
		return "redirect:/";
	}
}
