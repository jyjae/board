package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num =Integer.parseInt(request.getParameter("num"));
		int result=0;
		
		BoardDAO boarddao=new BoardDAO();
		BoardBean boarddata=new BoardBean();
		//글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		//입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
		boarddata=boarddao.select(Integer.parseInt(request.getParameter("num")));
		String passcheck=boarddata.getBOARD_PASS();
		System.out.println(passcheck);
	if(!passcheck.equals(request.getParameter("BOARD_PASS"))) {
		response.setContentType("text/html; charset=urf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('비밀번호가 다릅니다');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
		return null;
		
	}

	
	 result=boarddao.boardDelete(num);
	 if(result==0) {
		 System.out.println("게시판 삭제 실패");
		 ActionForward forward=new ActionForward();
		 forward.setRedirect(false);
		 request.setAttribute("message", "데이터를 삭제하지 못했습니다");
		 forward.setPath("error/error.jsp");
		 return forward;
	 }
	 
	 //삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
	  System.out.println("게시판 삭제 성공");
	  response.setContentType("text/html;charset=utf-8");
	  PrintWriter out=response.getWriter();
	  out.println("<script>");
		out.println("alert('삭제되셨습니다.');");
		out.println("location.href='BoardList.bo'");
		out.println("</script>");
		out.close();
		return null;
	  
	}

}
