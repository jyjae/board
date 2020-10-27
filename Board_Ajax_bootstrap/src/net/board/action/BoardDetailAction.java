package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.board.db.BoardBean;
import net.comment.db.CommentDAO;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardDetailAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardData = new BoardBean();
		
		try {
			// �� ��ȣ �Ķ���� ���� num������ ����
			int num = Integer.parseInt(request.getParameter("num"));
			// ���� ��ȸ���� ����
			boardDao.setReadCountUpdate(num);
			// ���� ������ DAO���� ���� �� ���� ����� boardData��ü�� ����
			boardData = boardDao.getDetail(num);
			
			// �Խù��� �������� ���� ���
			if(boardData == null){ 
				System.out.println("�󼼺��� ����!!");
				ActionForward forward = new ActionForward();
				forward.setRedirect(false);
				request.setAttribute("message", "�����͸� �о���� ���߽��ϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			System.out.println("�󼼺��� ����!");
			
			net.comment.db.CommentDAO cdao=new net.comment.db.CommentDAO();
			int count=cdao.getListCount(num);
			System.out.println("count="+count);
			request.setAttribute("count", count);
			
			//boarddata��ü�� request ��ü�� �����մϴ�.
			request.setAttribute("boardData", boardData); 
			
			// ������ ��İ� ��θ� ����
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			forward.setPath("board/qna_board_view.jsp");			
			return forward;
			
		}catch (Exception se) {
			se.printStackTrace();
		}
		return null;
	}

}
