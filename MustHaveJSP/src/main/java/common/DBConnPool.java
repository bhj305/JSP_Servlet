package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB 커넥션 풀
public class DBConnPool
{
//	멤버변수 생성 
	public Connection con;
	public Statement stmt; // 인파라미터가 없는 정적 쿼리문을 실행할 때 사용.
	public PreparedStatement psmt; // 인파라미터가 있는 동적 쿼리문을 실행할 때 사용 
	public ResultSet rs; // 결과 멤버변수
	
//	기본생성자 생성 ** 커넥션 풀을 사용하기위해선 context.xml, server.xml 파일에 관련 엘리먼트를 추가해야함 
	public DBConnPool()
	{
		try
		{
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env"); // 톰캣의 루트 디렉토리명은 이미 java:comp/env 로 정해져 있다. 
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
			con = source.getConnection();
			System.out.println("DB 커넥션 풀 연결 성공");
		} catch (Exception e)
		{
			System.out.println("DB 커넥션 풀 연결 실패");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try
		{
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(con != null) {
				con.close();
			}
			
			System.out.println("DB 커넥션 풀 자원 해제");
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
