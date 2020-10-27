package net.member.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
	private DataSource ds;
public MemberDAO() {
	try {
		Context init=new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	}catch (Exception ex) {
		System.out.println("DB 연결 실패 : "+ex);
	}
}
public int isId(String id) {
	int result = -1;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	ResultSet rs = null;
	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		
		
		conn = ds.getConnection();

		String sql = "select * from member where id=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);

		rs = pstmt.executeQuery();

		if(rs.next()) {

			result = 0;
		}

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

	return result;
}

public int insert(Member member) {


	Connection conn = null;
	PreparedStatement pstmt = null;


	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		
		conn = ds.getConnection();

		String sql = "insert into member(id,password,name,age,gender,email) values(?,?,?,?,?,?)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setInt(4, member.getAge());
		pstmt.setString(5, member.getGender() + "");
		pstmt.setString(6, member.getEmail());


		int result = pstmt.executeUpdate();
		return result;

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
	return 0;
}

public int ismember(String id, String password) {
	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	ResultSet rs = null;
	
	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		
		conn = ds.getConnection();

		String sql = "select id, password from member where id=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		
		if (rs.next()) {
			
			System.out.println(password);
			System.out.println(rs.getString(2));
			if(rs.getString(2).equals(password)) {
				result=1; //아이디와 비밀번호 일치하는 경우
			}else {
				result=0; //비밀번호가 일치하지 않는 경우
			}
			
		}else {
			result=-1; //아이디가 존재하지 않습니다. 
		}
		

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

	return result;
}
public Member member_info(String id) {
	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	Member member=null;
	ResultSet rs = null;
	
	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		
		conn = ds.getConnection();

		String sql = "select * from member where id=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		
		if (rs.next()) {
			member=new Member();
			member.setId(rs.getString("ID"));
			member.setPassword(rs.getString("PASSWORD"));
			member.setName(rs.getString("NAME"));
			member.setAge(rs.getInt("AGE"));
			member.setGender(rs.getString("GENDER"));
			member.setEmail(rs.getString("EMAIL"));
			
		}
		

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

	return member;
}

public int getListCount() {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int x = 0;
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement("select count(*) from member where id!='admin'");
		rs = pstmt.executeQuery();

		if (rs.next()) {
			x = rs.getInt(1);
		}
	} catch (Exception e) {
		System.out.println("getListCount()에러: " + e);
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

public int getListCount(String search_field, String search_word) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	int x = 0;
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement("select count(*) from member where id!='admin' and " + search_field + " like ?");
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
public List<Member> getList(String search_field, String search_word, int page, int limit) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<Member> list = new ArrayList<Member>();

	String sql = "select * "
			+ "from (select rownum rnum, id, name, age, gender, email from member where id!='admin' and "+search_field+"=?) "
			+ "where rnum between ? and ?";
	int startrow = (page - 1) * limit + 1;
	int endrow = startrow + limit - 1;
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, search_word);
		pstmt.setInt(2, startrow);
		pstmt.setInt(3, endrow);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Member m = new Member();
			m.setId(rs.getString("id"));
			m.setName(rs.getString("name"));
			m.setAge(rs.getInt("age"));
			m.setGender(rs.getString("gender"));
			m.setEmail(rs.getString("email"));

			list.add(m);
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("getList()에러: " + e);
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
public List<Member> getList(int page, int limit) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<Member> list = new ArrayList<Member>();

	String sql = "select * "
			+ "from (select rownum rnum, id, name, age, gender, email from member where id!='admin') "
			+ "where rnum between ? and ?";
	int startrow = (page - 1) * limit + 1;
	int endrow = startrow + limit - 1;
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Member m = new Member();
			m.setId(rs.getString("id"));
			m.setName(rs.getString("name"));
			m.setAge(rs.getInt("age"));
			m.setGender(rs.getString("gender"));
			m.setEmail(rs.getString("email"));

			list.add(m);
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("getList()에러: " + e);
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
public int delete(String id) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	int result=0;

	try {
		// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
		// 옵니다.
		
		conn = ds.getConnection();

		String sql = "delete from member where id=?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);
		

		result = pstmt.executeUpdate();
		return result;

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
	return 0;
}

}
