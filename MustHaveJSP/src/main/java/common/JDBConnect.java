package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

// DB연결을 위한 클래스 생성
public class JDBConnect
{
//	멤버변수 생성 
	public Connection con;
	public Statement stmt; // 인파라미터가 없는 정적 쿼리문을 실행할 때 사용.
	public PreparedStatement psmt; // 인파라미터가 있는 동적 쿼리문을 실행할 때 사용 
	public ResultSet rs; // 결과 멤버변수
	
	// 기본 생성자
	public JDBConnect()
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver"); // 드라이버 로딩
//			DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "musthave";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url, id, pwd);
//			연결되면 성공 메세지 출력
			System.out.println("DB연결성공(기본생성자)");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//	인수 생성자1: Db연결에 필요한 모든 정보를 매개변수로 받음, 오버로딩 
	public JDBConnect(String driver, String url, String id, String pwd)
	{
		try
		{
//			JDBC 드라이버 로드 
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd); //  DB에 연결
			
			System.out.println("DB연결성공(인수 생성자 1)");
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}	
	
//	효율적인 방법 !!
	public JDBConnect(ServletContext application)
	{
		try
		{
			String driver = application.getInitParameter("OracleDriver"); // 드라이버 로드
			Class.forName(driver);
			
//			DB 연결 
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			
			
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB연결성공(인수 생성자 2)");
			
		} catch (Exception e)
		{
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
			
			System.out.println("JDBC 자원 해제");
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
