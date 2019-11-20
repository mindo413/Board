package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;


@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션 객체 얻기
		HttpSession session = req.getSession();
		
		
		Member member = memberService.getLoginMember(req);
		System.out.println(member); //파라미터값 test
		System.out.println("-------------------");

		boolean login = memberService.login(member);
		
		if(login == true) {
			
			String id = req.getParameter("userid");
			
			String nick = memberService.getMemberByUserid(member).getUsernick();
			
			session.setAttribute("login", true);
			session.setAttribute("userid", id);
			session.setAttribute("usernick", nick);

			System.out.println("login : " + req.getSession().getAttribute("login"));
			System.out.println("id : " + req.getSession().getAttribute("userid"));
			System.out.println("nick : " + req.getSession().getAttribute("usernick"));
			
			resp.sendRedirect("/main");
		} else {
			resp.sendRedirect("/member/login");
		}
		
		
		
		
	}
}
