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
			System.out.println("(����) �󼼺��� ����");
			forward =new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "ȸ�� �� ���� �����Դϴ�");
		}
		System.out.println("ȸ�� �� ���� ����");
		System.out.println(member);
		
		//���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ boarddata ��ü��
		//request ��ü�� �����մϴ�.
		forward.setRedirect(false);
		request.setAttribute("member", member);
	
		forward.setPath("member/member_view.jsp");
		return forward;
		
	
	}

}
