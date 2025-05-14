package servlet;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDTO;

public class MemberAuth extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	MemberDAO dao;
	
	@Override
	public void init() throws ServletException
	{
		ServletContext application = this.getServletContext(); // 내장객체 얻어오기
		
		String driver = application.getInitParameter("OracleDriver"); // 드라이버 로드
		String connectUrl = application.getInitParameter("OracleURL");
		String oid = application.getInitParameter("OracleId");
		String oPass = application.getInitParameter("OraclePwd");
		
		dao = new MemberDAO(driver, connectUrl, oid, oPass);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String admin_id = this.getInitParameter("admin_id"); //web.xml에 등록한 admin_id 값을 가져와서 넣겠음돠
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		MemberDTO memberDTO = dao.getMemberDTO(id, pass);
		
		String memberName = memberDTO.getName();
		if(memberName != null) {
			req.setAttribute("authMessage", memberName + "회원님 방가방가 햄토리");
		} 
		else {
			if(admin_id.equals(id)) {
				req.setAttribute("authMessage", admin_id + "님은 최고 관리자 입니다.");
			} else {
				req.setAttribute("authMessage", "귀하는 회원이 아닙니다.");
			}
		}
		req.getRequestDispatcher("/12Servlet/MemberAuth.jsp").forward(req, resp);
	}
	
//	마지막에 항상 실행되므로 dao 자원 해제 
	@Override
	public void destroy()
	{
		dao.close();
	}
	
}
