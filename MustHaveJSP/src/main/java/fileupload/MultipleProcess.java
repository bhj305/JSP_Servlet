package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 어노테이션을 통해 요청명에 대한 매핑 진행
@WebServlet("/13FileUpload/MultipleProcess.do")
// 파일 업로드 처리를 위한 서블릿 구성 어노테이션
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1, // 업로드할 개별 파일의 최대 크기지정
	maxRequestSize =  1024 * 1024 * 10 // 멀티파트요청에 포함된 전체 파일의 크기를 10MB로 지정
)

public class MultipleProcess extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		try
		{
			String saveDirectory = getServletContext().getRealPath("/Uploads"); // 첨부파일이 저장될 물리적 경로 가져오기
			ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory); // FileUtil에서 multipleFile 메서드를 이용해 다중 파일 업로드 하기
			
			for (String originalFileName : listFileName) //파일 갯수만큼 반복해주기
			{
				String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName); // 저장된 파일명 변경하기
				insertMyFile(req, originalFileName, savedFileName); // DB에 저장
				
			}
			resp.sendRedirect("FileList.jsp"); // 파일 목록 페이지로 이동시켜주기
			
		} catch (Exception e)
		{
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("MultiUploadMain.jsp").forward(req, resp);
		}
	}

	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName)
	{
//		파일 외 폼값 받기
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
		
		System.out.println("파일 외 폼값: " + title + "\n" + cateBuf);
		
//		DTO 객체를 가져와 DB에 입력하기(Setter)
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
//		DAO를 통해 데이터베이스에 반영 
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close(); // dao 사용 후 자원해제
	}
}
