package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		
		BoardDAO boarddao=new BoardDAO();
		BoardBean boarddata=new BoardBean();
		
		//�Ķ���ͷ� ���޹��� ������ �� ��ȣ�� num������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		//�� ������ �ҷ��ͼ� boarddata��ü�� �����մϴ�.
		boarddata=boarddao.getDetail(num);
		
		//�� ���� �ҷ����� ������ ����Դϴ�.
		if(boarddata==null) {
			System.out.println("(����) �󼼺��� ����");
			forward =new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� ���� �� ���� �����Դϴ�");
		}
		System.out.println("(����)�󼼺��� ����");
		System.out.println(boarddata);
		
		//���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ boarddata ��ü��
		//request ��ü�� �����մϴ�.
		forward.setRedirect(false);
		request.setAttribute("boarddata", boarddata);
	
		forward.setPath("board/qna_board_modify.jsp");
		return forward;
	}

}
