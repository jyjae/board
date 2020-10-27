package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Member_infoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		
		Member member=new Member();
		MemberDAO memberdao=new MemberDAO();
		String id=request.getParameter("id");
		
		member=memberdao.member_info(id);
		
		if(member==null) {
			System.out.println("(수정) 상세보기 실패");
			forward =new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원 상세 보기 실패입니다");
		}
		System.out.println("회원 상세 보기 성공");
		System.out.println(member);
		
		//수정 폼 페이지로 이동할 때 원문 글 내용을 보여주기 때문에 boarddata 객체를
		//request 객체에 저장합니다.
		forward.setRedirect(false);
		request.setAttribute("member", member);
	
		forward.setPath("member/member_view.jsp");
		return forward;
		
	
	}

}
