package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
		 * 예) http://localhost:8088/JspProject/login.net인 경우
		 * "/JspProject/login.net" 반환됩니다.
		 * */
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI ="+RequestURI);
		
		//getContextPath(): 컨텍스트 경로가 반환됩니다.
		//contextPath는 "/JspProject"가 반환됩니다.
		String contextPath=request.getContextPath();
		System.out.println("contextPath="+contextPath);
		
		//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
		//마지막 위치 문자까지 추출합니다.
		//command는 "/BoardList.bo"반환됩니다.
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//초기화
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/BoardList.bo")) {
			action=new BoardListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.contentEquals("/BoardWrite.bo")) {
			forward=new ActionForward();
			forward.setRedirect(false); //포워딩 방식으로 주소가 바뀌지 않아요
			forward.setPath("board/qna_board_write.jsp");
		}else if(command.equals("/BoardAddAction.bo")) {
			action=new BoardAddAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReplyView.bo")) {
			action =new BoardReplyView();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReplyAction.bo")) {
			action=new BoardReplyAction();
			try {
				forward=action.execute(request, response);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardModifyView.bo")) {
			action=new BoardModifyView();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}else if(command.equals("/BoardModifyAction.bo")) {
			action=new BoardModifyAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDeleteAction.bo")) {
			action=new BoardDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardFileDown.bo")) {
			action=new BoardFileDownAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommentAdd.bo")) {
			action = new net.comment.action.CommentAdd();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommentList.bo")) {
			action = new net.comment.action.CommentList();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommentDelete.bo")) {
			action = new net.comment.action.CommentDelete();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommentUpdate.bo")) {
			action = new net.comment.action.CommentUpdate();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(forward!=null) {
			if(forward.isRedirect()) { //리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			}else {//포워딩 됩니다.
				RequestDispatcher dispatcher= request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //doProcess(request,response)메서드를 구현하여 요청이 GET방식이든.
    //POST방식으로 전송되어 모든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doProcess(request,response);
	}

}
