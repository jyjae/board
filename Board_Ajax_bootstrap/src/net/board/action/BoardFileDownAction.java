package net.board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardFileDownAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName=request.getParameter("filename");
		System.out.println("fileName="+fileName);
		
		String savePath="boardupload";
		
		//������ ���� ȯ�� ������ ��� �ִ� ��ü�� �����մϴ�.
		ServletContext context=request.getServletContext();
		String sDownloadPath=context.getRealPath(savePath);
		
		//String sFilePath=sDownloadPath+"\\"+fileName;
		// "\" �߰��ϱ� ���� "\\" ����մϴ�.
		String sFilePath=sDownloadPath+"/"+fileName;
		System.out.println(sFilePath);
		
		byte b[]= new byte[4096];
		
		//sFilePath�� �ִ� ������ MimeType�� ���ؿɴϴ�.
		String sMimeType=context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>"+sMimeType);
		
		if(sMimeType==null)
			sMimeType ="application/octet-stream";
		
		response.setContentType(sMimeType);
		
		//-�� �κ��� �ѱ� ���ϸ��� ������ ���� ������ �ݴϴ�.
		String sEncoding=new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		
		/*
		 * Context-Disposition : attachment: �������� �ش� Content�� 
		 * ó������ �ʰ�, �ٿ�ε��ϰ� �˴ϴ�.
		 * */
		
		response.setHeader("Content-Disposition", "attachment); filename="+sEncoding);
		
		try(
			//�� ���������� ��� ��Ʈ�� �����մϴ�.
				BufferedOutputStream out2=new BufferedOutputStream(response.getOutputStream());
				BufferedInputStream in= new BufferedInputStream(new FileInputStream(sFilePath)))
		{
			int numRead;
			//read(b,0,b.length): ����Ʈ �迭 b�� 0���� b.length
			//ũ�� ��ŭ �о�ɴϴ�.
			while((numRead=in.read(b,0,b.length))!=-1) {
				//���� �����Ͱ� �����ϴ� ���
				//����Ʈ �迭 b�� 0�� ���� numReadũ�� ��ŭ �������� ���
				out2.write(b,0,numRead);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
