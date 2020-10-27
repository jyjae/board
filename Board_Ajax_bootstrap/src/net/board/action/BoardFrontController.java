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
		 * ��û�� ��ü URL�߿��� ��Ʈ ��ȣ ���� ���� ������ ���ڿ����� ��ȯ�˴ϴ�.
		 * ��) http://localhost:8088/JspProject/login.net�� ���
		 * "/JspProject/login.net" ��ȯ�˴ϴ�.
		 * */
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI ="+RequestURI);
		
		//getContextPath(): ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		//contextPath�� "/JspProject"�� ��ȯ�˴ϴ�.
		String contextPath=request.getContextPath();
		System.out.println("contextPath="+contextPath);
		
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
		//������ ��ġ ���ڱ��� �����մϴ�.
		//command�� "/BoardList.bo"��ȯ�˴ϴ�.
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//�ʱ�ȭ
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
			forward.setRedirect(false); //������ ������� �ּҰ� �ٲ��� �ʾƿ�
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
			if(forward.isRedirect()) { //�����̷�Ʈ �˴ϴ�.
				response.sendRedirect(forward.getPath());
			}else {//������ �˴ϴ�.
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
    //doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�.
    //POST������� ���۵Ǿ� ��� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
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
