package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Member;

public interface MemberService {

	Member getLoginMember(HttpServletRequest req);

	public boolean login(Member member);

	Member getjoinMember(HttpServletRequest req);
	
	void join(Member member);
	
	Member getMemberByUserid(Member member);


}
