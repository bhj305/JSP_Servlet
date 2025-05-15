package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

public class ListController extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
//	수정 페이지로 진입해서 기존 내용을 수정폼에 설정함.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		MVCBoardDAO dao = new MVCBoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");

		/* 사용자의 입력한 검색어가 있다면 */
		if(searchWord != null)
		{
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map);

		
		/* 페이징 추가 */
		ServletContext application = getServletContext();
		
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
//		현재 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals("")){
			pageNum = Integer.parseInt(pageTemp); /* 요청 받은 페이지로 수정 */
		} 
//		목록에 출력할 게시물 범위 계산
		int start = (pageNum -1 ) * pageSize +1; // 첫번째 게시물 번호
		int end = pageNum * pageSize; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		/* 페이징 끝 */

		List<MVCBoardDTO> boardLists = dao.selectListPage(map);

		dao.close();
		
		// 뷰에 전달할 매개변수 추가 
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "..//mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
//		request 영역에 생성 
		req.setAttribute("boardLists", boardLists); 
		req.setAttribute("map", map);
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
	}
}
