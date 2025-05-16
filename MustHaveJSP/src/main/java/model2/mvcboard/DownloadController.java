package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
//		request 영역의 매개변수를 파라미터로 받아옴
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		String idx = req.getParameter("idx");
		
//		FileUtil에 작성한 download 메서드 호출
		FileUtil.download(req, resp, "/Uploads", sfile, ofile);
		
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.downCountPlus(idx);
		dao.close(); // 사용 후 잘 닫아줘야 메모리 낭비가 없음.
		
		
	}
}
