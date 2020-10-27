package net.board.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.member.action.Action;
import net.member.action.ActionForward;


public class BoardAddAction implements Action {
	
	private String replaceParameter(String param){
	       String result=param;
	       if(param != null){
	       result=result.replaceAll("<","&lt;");
	       result=result.replaceAll(">","&gt;");
	         }
	         return result;
	       }

	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		BoardDAO dao=new BoardDAO();
		BoardBean bb=new BoardBean();
		ActionForward forward=new ActionForward();
		
		String realFolder="";
		//WebContent아래에 꼭 폴더를 생성하세여
		String saveFolder="boardupload";
		int fileSize=5*1024*1024; //업로드할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장 경로를 지정합니다.
		ServletContext sc=request.getServletContext();
		realFolder=sc.getRealPath(saveFolder);
		
		System.out.println("realFolder="+realFolder);
		
		
		try {
			MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		    bb.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
		    bb.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
		    bb.setBOARD_SUBJECT(replaceParameter(multi.getParameter("BOARD_SUBJECT")));
		    bb.setBOARD_CONTENT(replaceParameter(multi.getParameter("BOARD_CONTENT")));
		    bb.setBOARD_FILE(multi.getFilesystemName("BOARD_FILE"));
		
		    
		
		int result=dao.insert(bb);
		
		if(result==1) {
			System.out.println("게시판 등록 실패");
			 forward=new ActionForward();
			forward.setRedirect(false); //주소 변경없이 jsp페이지의 내용을 보여줍니다.
			forward.setPath("BoardList.bo");
			
			}else {

				System.out.println("게시판 등록실패");
				 forward=new ActionForward();
				 //주소 변경없이 jsp페이지의 내용을 보여줍니다.
				 forward.setRedirect(false);
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				
				
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return forward;
	}

}
