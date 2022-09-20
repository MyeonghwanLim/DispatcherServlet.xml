package com.lim.biz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.lim.biz.member.MemberVO;
import com.lim.biz.member.impl.MemberDAO;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		MemberVO mVO=new MemberVO();
		mVO.setMid(mid);
		mVO.setMpw(mpw);
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOne(mVO);
		
		ModelAndView mav = new ModelAndView();
		if(mVO==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<script>alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.'); history.go(-1);</script>");
			out.flush();
			mav.setViewName("redirect:login.jsp");
			System.out.println("로그인 실패");
			 
		}
		else {
			session.setAttribute("member", mVO);
			mav.setViewName("redirect:main.do"); 
		}
		return mav;
	}

}
