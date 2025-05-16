package model2.mvcboard;

import java.io.File;
import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
//	pass 검증 페이지로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(req, resp);
	}
	
//	비밀번호 검증 페이지에서 전송한 경우 수정, 삭제 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
//		입력한 비밀번호와 hidden 박스에서 전송된 파라미터 받아 매개변수에 저장 
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		MVCBoardDAO dao = new MVCBoardDAO(); // 서버에 보내기 위함
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if(confirmed) {
			if(mode.equals("edit")) { // 수정모드로 집입
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass); // session 영역에 pass 저장 
				resp.sendRedirect("../mvcboard/edit.do?idx=" + idx); // 수정페이지로 이동
				
			} else if(mode.equals("delete")) { // 삭제모드로 진입
				dao = new MVCBoardDAO();
				MVCBoardDTO dto = dao.selectView(idx);
				int result = dao.deletePost(idx);
				dao.close();
				
				if(result == 1) { // 삭제한 값이 있으면 1을 반환하므로 , 파일도 같이 삭제해주기
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(req, "/Uploads", saveFileName);
				}
				JSFunction.alertLocation(resp, "삭제되었습니다.", "../mvcboard/list.do");
			}
			
		} else {
//			검증에 실패한 경우 뒤로 되돌아 가기
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
	}
}
