package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		BoardDAO boarddao=new BoardDAO();
		BoardBean boarddata=new BoardBean();
		int result=0;
		
		//�Ķ���ͷ� �Ѿ�� ������ boarddata ��ü�� �����մϴ�.
		boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
		boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
		boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
		
		//�亯�� DB�� �����ϱ� ���� boarddata ��ü�� �Ķ���ͷ� �����ϰ�
		//DAO�� �޼��� boardReply�� ȣ���մϴ�.
		result=boarddao.boardReply(boarddata);
		
		//�亯 ���忡 ������ ���
		if(result==0) {
			System.out.println("�亯 ���� ����");
			forward=new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "���� ���� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//�亯 ������ ����� �� ���
		System.out.println("���� �Ϸ�");
		forward.setRedirect(true);
		//�亯 �� ������ Ȯ���ϱ� ���� �� ���� ���� �������� ��η� �����մϴ�.
		forward.setPath("BoardDetailAction.bo?num="+result);
		
		return forward;
	}

}
