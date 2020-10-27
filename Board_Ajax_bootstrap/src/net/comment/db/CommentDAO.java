package net.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.member.action.Member;

public class CommentDAO {
	DataSource ds;
	
	public CommentDAO() {
		try {
			Context init=new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception ex) {
			System.out.println("DB 연결 실패 : "+ex);
		}
	}
	
	public int getListCount(int BOARD_RE_REF) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from comments where BOARD_RE_REF=?");
			pstmt.setInt(1,BOARD_RE_REF);
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
	
	public JsonArray member_info(int num) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Member member=null;
		ResultSet rs = null;
		
		try {
			// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
			// 옵니다.
			
			conn = ds.getConnection();

			String sql = "select * from member where BOARD_NUM=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
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

		return null;
	}
	
public int commentUpdate(Comment cmt) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "update comments "
							+ "set content = ? "
							+ "where num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmt.getContent());
			pstmt.setInt(2, cmt.getNum());
			
			result=pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 수정이 모두 완료되었습니다.");
			}
			
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
		return result;
	}


public JsonArray getCommentList(int BOARD_RE_REF) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	JsonArray ja = new JsonArray();
	String sql = "select num, id, content, reg_date "
			+ "		from comments"
			+ "		where BOARD_RE_REF=?"
			+ "		order by reg_date desc";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, BOARD_RE_REF);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			JsonObject obj = new JsonObject();
			obj.addProperty("num", rs.getInt(1));
			obj.addProperty("id", rs.getString(2));
			obj.addProperty("content", rs.getString(3));
			obj.addProperty("reg_date", rs.getString(4));
			ja.add(obj);
		}
	}catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        if (pstmt != null)
            try {
                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
	return ja;
}
	
	public int commentsInsert(Comment cmt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql="";
		try {
			conn = ds.getConnection();
			
			sql = "insert into comments "
					+ "(num, id, content, reg_date, BOARD_RE_REF) "
					+	"values(com_seq.nextval, ?, ?, sysdate, ?)";
			

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmt.getId());
			pstmt.setString(2, cmt.getContent());
			pstmt.setInt(3, cmt.getBoard_re_ref());
			
			result=pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
		return result;
	}
	
	public int commentDelete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "delete from comments "
					+ "where num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 삭제가 모두 완료되었습니다.");
			}
			
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
		return result;
	}
	
	public int commentaInsert(Comment comment) {


		Connection conn = null;
		PreparedStatement pstmt = null;


		try {
			// context.xml에 리소스를 생성해 놓은 (JNDI에 설정해 놓은) jdbc/OracleDB를 참조하여 Connection 객체를 얻어
			// 옵니다.
			
			conn = ds.getConnection();

			String sql = "insert into member(id,password,name,age,gender,email) values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comment.getNum());
			pstmt.setString(2, comment.getId());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, comment.getReg_date());
			pstmt.setInt(5, comment.getBoard_re_ref());
			


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

}
