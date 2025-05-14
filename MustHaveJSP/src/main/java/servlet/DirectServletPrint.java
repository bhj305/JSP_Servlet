package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DirectServletPrint extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html; charset=UTF-8"); // 컨텐트 타입을 설정해줘야 함.
		PrintWriter writer = resp.getWriter(); // 직접 출력을 위해 PrintWriter 객체 생성
		
//		출력 내용 기술
		writer.println("<html>");
		
		writer.println("<head><title>DirectServletPrint</title></head>");
		
		writer.println("	<body>");
		writer.println("		<h2>서블릿에서 직접 출력합니다.</h2>");
		writer.println("		<p>JSP로 포워드 하지 않습니다.</p>");
		writer.println("	</body>");
		
		writer.println("</html>");
		
//		마무리로 객체 자원을 해제해줘야 함.
		writer.close();
	}
}
