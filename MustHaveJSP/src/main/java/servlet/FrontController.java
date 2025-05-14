package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.one") // 모든 .one 파일을 매핑처리함
public class FrontController extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// request 내장 객체를 통해 얻어옴
		String uri = req.getRequestURI(); // uri 를 받을 객체 생성
		int lastSlash = uri.lastIndexOf("/"); 
		String commandStr = uri.substring(lastSlash); // 마지막 '/'를 포함한 이후의 값을 넣어주기
		
		if(commandStr.equals("/regist.one")) {
			registFunc(req);
		} 
		else if(commandStr.equals("/login.one")) {
			loginFunc(req);
		} 
		else if(commandStr.equals("/freeboard.one")) {
			freeboardFunc(req);
		}
		
		req.setAttribute("uri", uri);
		req.setAttribute("commandStr", commandStr);
		req.getRequestDispatcher("/12Servlet/FrontController.jsp").forward(req, resp);
		
	}
	
//	private 으로 하면 불러올 수 없음 !
	void registFunc(HttpServletRequest req)
	{
		req.setAttribute("resultValue", "<h4>회원가입</h4>");
	}
	
	void loginFunc(HttpServletRequest req)
	{
		req.setAttribute("resultValue", "<h4>로그인</h4>");
	}

	void freeboardFunc(HttpServletRequest req)
	{
		req.setAttribute("resultValue", "<h4>자유게시판</h4>");
	}
	
}
