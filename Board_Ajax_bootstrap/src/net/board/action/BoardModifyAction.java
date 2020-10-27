package net.board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =new ActionForward();
		BoardDAO boarddao=new BoardDAO();
		BoardBean boarddata=new BoardBean();
		int result=0;
		
		String realFolder="";
		//WebContent�Ʒ��� �� ������ �����ϼ���
		String saveFolder="boardupload";
		int fileSize=5*1024*1024; //���ε��� ������ �ִ� ������ �Դϴ�. 5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc=request.getServletContext();
		realFolder=sc.getRealPath(saveFolder);
		
		System.out.println("realFolder="+realFolder);
		
		
		try {
			
			MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			boarddata=boarddao.select(Integer.parseInt(multi.getParameter("BOARD_NUM")));
			String passcheck=boarddata.getBOARD_PASS();
			
		if(passcheck.equals(multi.getParameter("BOARD_PASS"))) {
			 boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
			 boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
			 boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
			 
			 
			 String check=multi.getParameter("check");
			 System.out.println("check"+check);
			 if(check!=null) {
				 //���� ���� �״�� ����ϴ� ����Դϴ�.
				 boarddata.setBOARD_FILE(check);
			 }else {
				 boarddata.setBOARD_FILE(multi.getFilesystemName("BOARD_FILE"));
				 
			 }
			 
			 System.out.println(boarddata.toString());
			result=boarddao.update(boarddata);
			System.out.println(result);
			if(result!=1) {
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ������ ���� �ʾҽ��ϴ�.");
				forward.setPath("error/error.jsp");
				
			}else {
				System.out.println("���� ����!");
				request.setAttribute("boarddata", boarddata); 
				forward.setRedirect(false);
				
				forward.setPath("BoardDetailAction.bo?num="+boarddata.getBOARD_NUM());			
				
			}
			
			
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
			out.println("history.back()");
			out.close();
			return null;
			
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return forward;
	}

}
