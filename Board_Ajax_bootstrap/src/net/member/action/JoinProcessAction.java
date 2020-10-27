package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		MemberDAO dao=new MemberDAO();
		Member member=new Member();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("pass"));
		member.setName(request.getParameter("name"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setGender(request.getParameter("gender"));
		member.setEmail(request.getParameter("email"));
		int result=dao.insert(member);
		
		if(result==1) {
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false); //주소 변경없이 jsp페이지의 내용을 보여줍니다.
		forward.setPath("member/loginForm.jsp");
		return forward;
		}else {

			System.out.println("실패");
			ActionForward forward=new ActionForward();
			forward.setRedirect(false); //주소 변경없이 jsp페이지의 내용을 보여줍니다.
			forward.setPath("member/joinForm.jsp");
			return forward;
			
		}
	}

}
