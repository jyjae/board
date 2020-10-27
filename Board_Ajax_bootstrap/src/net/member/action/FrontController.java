package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.net")
public class FrontController extends HttpServlet {
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
		//command는 "/login.net"반환됩니다.
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//초기화
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/login.net")) {
			action=new LoginAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/join.net")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("member/joinForm.jsp");
			
		}else if(command.contentEquals("/idcheck.net")) {
			action=new IdCheckAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/joinProcess.net")) {
			action=new JoinProcessAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/loginProcess.net")) {
			action=new LoginProcessAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/logout.net")) {
			action =new LogoutAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_update.net")) {
			action =new Member_updateAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/updateProcess.net")) {
			action =new UpdateProcessAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_list.net")) {
			action =new SearchAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_info.net")) {
			action =new Member_infoAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_delete.net")) {
			action =new Member_deleteAction();
			try {
				forward=action.execute(request, response);
			}catch (Exception e) {
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
    public FrontController() {
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
