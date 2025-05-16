package model2.mvcboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do")  // 서블릿 매핑
public class ViewController extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		MVCBoardDAO dao = new MVCBoardDAO(); // dao 객체 생성 
		String idx = req.getParameter("idx");  // request 영역에서 idx 가져오기
		dao.updateVisitCount(idx); // MVCBoardDAO에 있는 updateVisitCount 메소드에 request 에서 가져온 idx 값 보내기
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close();
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>")); // 내용에 줄바꿈이 있으면 \r\n 를 <br/>로 바꿔주기
		
		String ext = null, fileName = dto.getSfile();
		if(fileName != null) {
			ext = fileName.substring(fileName.lastIndexOf(".") + 1); // 파일명을 가져와라
		}
		String[] mineStr = { "png", "jpg", "gif" , "PNG", "JPG", "GIF"}; // 그림파일 
		List<String> mineList = Arrays.asList(mineStr);
		boolean isImage = false;
		
		if(mineList.contains(ext)) {
			isImage = true;
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("isImage", isImage);
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp);
		
	}
}
