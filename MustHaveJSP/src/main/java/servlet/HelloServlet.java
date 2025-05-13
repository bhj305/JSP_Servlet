package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setAttribute("message", "Hello Servlet..!!"); // request 영역에 저장
		req.getRequestDispatcher("/12Servlet/HelloServlet.jsp").forward(req, resp);// view에 해당하는 JSP 페이지로 포워드함
//		리퀘스트 영역은 포워드된 페이지까지 공유되므로 서블릿에서 저장한 속성값을 JSP에서도 사용할 수 있다.
	}
	
}
