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
		System.out.println("넘어온 페이지 = " + page);

		List<Member> list = new ArrayList<Member>();
		int listcount = 0;
		int index = -1;// search_field에 존재하지 않는 값으로 초기화

		String search_word = "";
		// 메뉴-관리자-회원정보 클릭한 경우(member_list.net)
		// 또는 메뉴-관리자-회원정보 클릭 후 페이지를 클릭한
		// 경우(member_list.net?page=2&search_field=&search_word=

		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {
			// 총 리스트 수를 받아옵니다.
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
		System.out.println("총페이지수 = " + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		

		// endpage: 현재 페이지 그룹에서 보여줄 마지막 페이지 수
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);

		if (endpage > maxpage)
			endpage = maxpage;


		request.setAttribute("page", page); // 현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수

		// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);

		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);

		request.setAttribute("listcount", listcount); // 총 글의 수

		// 해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("memberlist", list);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);

		forward.setRedirect(false);

		// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("member/member_list.jsp");
		return forward;

	}

}
