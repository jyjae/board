package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		BoardDAO boarddao=new BoardDAO();
		BoardBean boarddata=new BoardBean();
		
		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num ������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		boarddata=boarddao.getDetail(num);
		
		//�� ������ ���� ���
		if(boarddata==null) {
			System.out.println("���� �������� �ʽ��ϴ�.");
			forward.setRedirect(false);
			request.setAttribute("message", "���� �������� �ʽ��ϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("�亯 ������ �̵� �Ϸ�");
		//�亯 �� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("boarddata", boarddata);
		
		forward.setRedirect(false);
		//�� �亯 ������ ��� �����մϴ�.
		forward.setPath("board/qna_board_reply.jsp");
		return forward;
	}

}
