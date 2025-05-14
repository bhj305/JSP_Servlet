package fileupload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 어노테이션을 통해 요청명에 대한 매핑 진행
@WebServlet("/13FileUpload/UploadProcess.do")
// 파일 업로드 처리를 위한 서블릿 구성 어노테이션
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1, 
	maxRequestSize =  1024 * 1024 * 10
)

public class UploadProcess extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		try
		{
//			String saveDirectory = "/Users/baehyejin/DevData/JSP_Servlet/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MustHaveJSP/Uploads
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			String originalFileName = FileUtil.uploadFile(req, saveDirectory);
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			insertMyFile(req, originalFileName, savedFileName);
			
			resp.sendRedirect("FileList.jsp"); 
		} catch (Exception e)
		{
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
		}
	}

	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName)
	{
		String title = req.getParameter("title");
		String[] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		
		if(cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		} else {
			for (String s : cateArray)
			{
				cateBuf.append(s + ",");
			}
		}
		
//		DB에 입력하기
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
//		DAO를 통해 데이터베이스에 반영 
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
	}
}
