package web.dao.face;

import web.dto.Member;

public interface MemberDao {
	
	int selectCntMemberByUserid(Member member);

	void insert(Member member);

	Member selectMemberByUserid(Member member);
}
