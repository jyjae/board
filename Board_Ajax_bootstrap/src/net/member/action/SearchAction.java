package net.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();

		int page = 1;
		int limit = 3;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ = " + page);

		List<Member> list = new ArrayList<Member>();
		int listcount = 0;
		int index = -1;// search_field�� �������� �ʴ� ������ �ʱ�ȭ

		String search_word = "";
		// �޴�-������-ȸ������ Ŭ���� ���(member_list.net)
		// �Ǵ� �޴�-������-ȸ������ Ŭ�� �� �������� Ŭ����
		// ���(member_list.net?page=2&search_field=&search_word=

		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {
			// �� ����Ʈ ���� �޾ƿɴϴ�.
			listcount = dao.getListCount();
			list = dao.getList(page, limit);
		} else {
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] { "id", "name", "age", "gender" };

			search_word = request.getParameter("search_word");
			listcount = dao.getListCount(search_field[index], search_word);
			
			list = dao.getList(search_field[index], search_word, page, limit);
		}

		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("���������� = " + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("���� �������� ������ ���� ������ �� = " + startpage);
		

		// endpage: ���� ������ �׷쿡�� ������ ������ ������ ��
		int endpage = startpage + 10 - 1;
		System.out.println("���� �������� ������ ������ ������ �� = " + endpage);

		if (endpage > maxpage)
			endpage = maxpage;


		request.setAttribute("page", page); // ���� ������ ��
		request.setAttribute("maxpage", maxpage); // �ִ� ������ ��

		// ���� �������� ǥ���� ù ������ ��
		request.setAttribute("startpage", startpage);

		// ���� �������� ǥ���� �� ������ ��
		request.setAttribute("endpage", endpage);

		request.setAttribute("listcount", listcount); // �� ���� ��

		// �ش� �������� �� ����� ���� �ִ� ����Ʈ
		request.setAttribute("memberlist", list);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);

		forward.setRedirect(false);

		// �� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("member/member_list.jsp");
		return forward;

	}

}
