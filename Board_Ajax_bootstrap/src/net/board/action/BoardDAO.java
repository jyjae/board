package net.board.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.board.db.BoardBean;

public class BoardDAO {
	public BoardDAO() {

	}

	public int insert(BoardBean bb) {

		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int num = 1;
		String sql = "";

		try {
			// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
			// 옵니다.
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

			String max_sql = "select max(board_num) from board";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("rs.getInt(1)=" + rs.getInt(1));
				num = rs.getInt(1) + 1;
			}

			sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,SYSDATE)";

			pstmt2 = conn.prepareStatement(sql);

			System.out.println(bb.getBOARD_NAME());

			pstmt2.setInt(1, num);
			pstmt2.setString(2, bb.getBOARD_NAME());
			pstmt2.setString(3, bb.getBOARD_PASS());
			pstmt2.setString(4, bb.getBOARD_SUBJECT());
			pstmt2.setString(5, bb.getBOARD_CONTENT());
			pstmt2.setString(6, bb.getBOARD_FILE());
			pstmt2.setInt(7, num);

			pstmt2.setInt(8, 0);
			pstmt2.setInt(9, 0);
			pstmt2.setInt(10, 0);

			int result = pstmt2.executeUpdate();
			if (result == 1) {

				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
			return result;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return 0;
	}
	
	public int getListCount(String search_field, String search_word) {
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int num = 1;
		String sql = "";

		int x = 0;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("select count(*) from member where id!='admin' and " + search_field + "= ?");
			pstmt.setString(1, search_word);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount(search_field, search_word)에러: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}

		return x;
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount() 에러:" + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}

		return x;
	}

	public List<BoardBean> getBoardList(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// page : 페이지
		// limit : 페이지 당 목록의 수
		// BOARD_RE_REF desc, BOARD_RE_SEQ asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

		String board_list_sql = "select * from" + " (select rownum rnum, BOARD_NUM,BOARD_NAME, "
				+ "BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, " + "BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ,"
				+ " BOARD_READCOUNT, BOARD_DATE " + "from(select * from board"
				+ " order by BOARD_RE_REF desc, BOARD_RE_SEQ asc) " + ") " + "where rnum>=? and rnum<=?";

		List<BoardBean> list = new ArrayList<BoardBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_NUM"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getString("BOARD_DATE"));
				list.add(board); // 값을 담은 객체를 리스트에 저장합니다.
			}
		} catch (Exception ex) {
			System.out.println("getListCount() 에러:" + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}

		return list;
	}
	
	public BoardBean select(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean board=null;

		String board_list_sql = "select * from" + " board where BOARD_NUM=?";
		
		try {
			board=new BoardBean();
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_PASS(rs.getString("BOARD_PASS"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				

			}
			
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러:" + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}

		return board;
	}

	public BoardBean getDetail(int num) {
		BoardBean boardData = null;
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			String sql = " select * from board where BOARD_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardData = new BoardBean();
				// 조회된 게시물을 board 객체에 담아 리턴합니다.
				boardData.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				boardData.setBOARD_NAME(rs.getString("BOARD_NAME"));
				boardData.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				boardData.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				boardData.setBOARD_FILE(rs.getString("BOARD_FILE"));
				boardData.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				boardData.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				boardData.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				boardData.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				boardData.setBOARD_DATE(rs.getString("BOARD_DATE"));
			}
			return boardData;

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close(); // 4단계 : DB 연결을 끊는다
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	public void setReadCountUpdate(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;

		String sql = "";

		try {
			// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
			// 옵니다.
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

			String max_sql = "update board " + "set BOARD_READCOUNT = BOARD_READCOUNT+1 " + "where BOARD_NUM = ?";
			pstmt = conn.prepareStatement(max_sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}

	}

public int boardReply(BoardBean board) throws NamingException {
	Connection conn = null;
	PreparedStatement pstmt = null, pstmt2=null, pstmt3=null, pstmt4=null;
	ResultSet rs=null;
	
	int result=0;
	int re_seq=board.getBOARD_RE_SEQ();
	
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		
		
		
		String max_sql="select max(board_num) from board";
		String sql="";
		int num=0;
		/*
		 * 답변을 달 원문 글 그룹 번호입니다.
		 * 답변을 달게 되면 답벼느 글은 이 번호와 같은 관련글 번호를 갖게 처리되면서
		 * 같은 그룹에 속하게 됩니다.
		 * 글 목록에서 보여줄때
		 * */
		int re_ref=board.getBOARD_RE_REF();
		
		
		/*
		 * 답글의 깊이를 의미합니다.
		 * 원문에 대한 답글이 출력 될 때 한 번 들여쓰기 처리가 되고
		 * 답글에 대한 답글은 들여쓰기가 두 번 처리되게 합니다.
		 * 원문인 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가 됩니다.
		 * */
		int re_lev=board.getBOARD_RE_LEV();
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			
			//트랜잭션을 이용하기 위해서 setAutoCommit을 false로 설정합니다.
			conn.setAutoCommit(false);
			
			pstmt=conn.prepareStatement(max_sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1);
			pstmt.close();
			//BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문 글에 다른 답글이 있으면
			//다른 답글들의 BOARD_RE_SEQ값을 1씩 증가시킵니다.
			sql="update board "
					+ "set BOARD_RE_SEQ=BOARD_RE_SEQ+1 "
					+ "where BOARD_RE_REF=? "
					+ "and BOARD_RE_SEQ>?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			pstmt.executeUpdate();
			
			//등록할 답변 글의 BOARD_RE_LEV, BOARD_RE_SEQ 값을 원문 글보다 1씩
			//증가시킵니다.
			re_seq=re_seq+1;
			re_lev=re_lev+1;
			
			sql="insert into board "
					+ "(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,"
					+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"
					+ "BOARD_RE_LEV, BOARD_RE_SEQ,"
					+ "BOARD_READCOUNT, BOARD_DATE) "
					+ "values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, board.getBOARD_NAME());
			pstmt2.setString(3, board.getBOARD_PASS());
			pstmt2.setString(4, board.getBOARD_SUBJECT());
			pstmt2.setString(5, board.getBOARD_CONTENT());
			pstmt2.setString(6, "");
			pstmt2.setInt(7, re_ref);
			pstmt2.setInt(8, re_lev);
			pstmt2.setInt(9, re_seq);
			pstmt2.setInt(10, 0);
			
			if(pstmt2.executeUpdate()==1) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch (SQLException se) {
		System.out.println("boardReply() 에러 : "+se);
		if(conn!=null) {
			try {
				conn.rollback();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
	}finally {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	return num;
	}

public int update(BoardBean boarddata) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result=0;
	String sql = "";

	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();

		String max_sql = "update board " + "set BOARD_NAME = ?, BOARD_SUBJECT=?, BOARD_CONTENT=?, BOARD_FILE=?" + " where BOARD_NUM = ?";
		pstmt = conn.prepareStatement(max_sql);
		
		pstmt.setString(1, boarddata.getBOARD_NAME());
		pstmt.setString(2,boarddata.getBOARD_SUBJECT());
		pstmt.setString(3, boarddata.getBOARD_CONTENT());
		pstmt.setString(4, boarddata.getBOARD_FILE());
		pstmt.setInt(5, boarddata.getBOARD_NUM());
	
		result=pstmt.executeUpdate();
		
		



	} catch (SQLException se) {
		System.out.println(se.getMessage());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		try {
		
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	return result;
}



public int boardDelete(int num) {
	Connection conn = null;
	PreparedStatement pstmt = null, pstmt2=null;
	ResultSet rs = null;
	int result=0;
	String sql = "";

	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();
		String select_sql="select BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ from board where BOARD_NUM=?";

		String board_delete_sql = "delete from board "
				+ "where BOARD_RE_REF=? "
				+ "and BOARD_RE_LEV>=? "
				+ "and BOARD_RE_SEQ>=? "
				+ "and BOARD_RE_SEQ<=("
				+ "                    nvl((SELECT min(board_re_seq)-1 "
				+ "                         FROM BOARD "
				+ "                         WHERE BOARD_RE_REF=? "
				+ "                         AND BOARD_RE_LEV=? "
				+ "                         AND BOARD_RE_SEQ>?) , "
				+ "                         (SELECT max(board_re_seq) "
				+ "                         FROM BOARD "
				+ "                         WHERE BOARD_RE_REF=? ))"
				+ "                       )";
		
		//pstmt = conn.prepareStatement(board_delete_sql);
		boolean result_check=false;
		pstmt=conn.prepareStatement(select_sql);
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			pstmt2=conn.prepareStatement(board_delete_sql);
			pstmt2.setInt(1, rs.getInt("BOARD_RE_REF"));
			pstmt2.setInt(2, rs.getInt("BOARD_RE_LEV"));
			pstmt2.setInt(3, rs.getInt("BOARD_RE_SEQ"));
			pstmt2.setInt(4, rs.getInt("BOARD_RE_REF"));
			pstmt2.setInt(5, rs.getInt("BOARD_RE_LEV"));
			pstmt2.setInt(6, rs.getInt("BOARD_RE_SEQ"));
			pstmt2.setInt(7, rs.getInt("BOARD_RE_REF"));
			
			pstmt2.executeUpdate();
			
			//삭제가 안된 경우에는 false를 반환합니다.
			result_check=true;
			result=1;
		}
		
		
		



	} catch (SQLException se) {
		System.out.println(se.getMessage());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		try {
		
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	return result;
}
}

