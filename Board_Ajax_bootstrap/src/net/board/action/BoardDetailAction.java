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
			// 글 번호 파라미터 값을 num변수에 저장
			int num = Integer.parseInt(request.getParameter("num"));
			// 글의 조회수를 증가
			boardDao.setReadCountUpdate(num);
			// 글의 내용을 DAO에서 읽은 후 얻은 결과를 boardData객체에 저장
			boardData = boardDao.getDetail(num);
			
			// 게시물이 존재하지 않을 경우
			if(boardData == null){ 
				System.out.println("상세보기 실패!!");
				ActionForward forward = new ActionForward();
				forward.setRedirect(false);
				request.setAttribute("message", "데이터를 읽어오지 못했습니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			System.out.println("상세보기 성공!");
			
			net.comment.db.CommentDAO cdao=new net.comment.db.CommentDAO();
			int count=cdao.getListCount(num);
			System.out.println("count="+count);
			request.setAttribute("count", count);
			
			//boarddata객체를 request 객체에 저장합니다.
			request.setAttribute("boardData", boardData); 
			
			// 포워딩 방식과 경로를 지정
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
