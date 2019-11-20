package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.MemberDao;
import web.dao.impl.MemberDaoImpl;
import web.dto.Member;
import web.service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Member getLoginMember(HttpServletRequest req) {

		Member member = new Member();

		String param = null;

		param = req.getParameter("userid");
		member.setUserid(param);

		param = req.getParameter("userpw");
		member.setUserpw(param);
		
		return member;
	}

	@Override
	public boolean login(Member member) {

		int cnt = 0;
		cnt = memberDao.selectCntMemberByUserid(member);
//		System.out.println("dao cnt : " + cnt); // 로그인 count값 확인
		if (cnt == 1) {
			return true;
		} else
			return false;

	}

	@Override
	public Member getjoinMember(HttpServletRequest req) {
		
		Member member = new Member();
		
		String param = null;
		
		// userid
		param = req.getParameter("userid");
		member.setUserid(param);
		
		// userpw
		param = req.getParameter("userpw");
		member.setUserpw(param);
		
		// usernick
		param = req.getParameter("usernick");
		member.setUsernick(param);
		
		return member;
	}
	
	@Override
	public void join(Member member) {
//		System.out.println(member); // 요청 member값 들어오는지 test
		
		memberDao.insert(member);
		
	}

	@Override
	public Member getMemberByUserid(Member member) {
		
		return memberDao.selectMemberByUserid(member);
	}


}
