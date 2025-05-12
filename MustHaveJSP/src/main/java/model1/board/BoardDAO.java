package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;
import membership.MemberDTO;

public class BoardDAO extends JDBConnect
{
//	인수생성자에서 application 내장객체를 매개변수로 전달
	public BoardDAO(ServletContext application)
	{
		super(application); // web.xml에 접근하여 파라미터를 얻어옴
	}
	public int selectCount(Map<String, Object> map) 
	{
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM board";
		if(map.get("searchWord") != null) 
		{
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next(); // 첫번째 커서로 이동하여 레코드를 읽음(결과값이 있는지 확인)
			totalCount = rs.getInt(1); //결과 값의 첫번째 인덱스를 가져오슈
		} catch (Exception e)
		{
			System.out.println("게시물 수를 구하는 중 예외발생");
			e.printStackTrace();
		}
		
		return totalCount; //int 형 결과값
	}
//	작성된 게시물 반환, 여러개의 레코드를 반환하므로 List Collection 으로 받음. - Set은 순서가 보장되지 않으므로 사용할 수 없음.
	public List<BoardDTO> selectList(Map<String, Object> map)
	{
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 결국엔 여러개의 참조값이 존재하게됨.
		
		String query = "SELECT * FROM board ";
		if(map.get("searchWord") != null) 
		{
//			띄어쓰기 중요!!!
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
//		상단에 최근게시물을 노출시키기 위해 내림차순으로 정렬 
		query += " ORDER BY num DESC ";
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
//				하나의 레코드를 저장할 수 있도록 DTO 객체 생성
				BoardDTO dto = new BoardDTO();
//				BoardDTO의 setter()를 이용해서 각 컬럼의 값 저장.
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
//				저장된 컬럼 값을 List 에 저장 
				bbs.add(dto);
			}
		} catch (Exception e)
		{
			System.out.println("게시물 조회 중 예외발생");
			e.printStackTrace();
		}
		return bbs;
	}

	public int insertWrite(BoardDTO dto) 
	{
		int result = 0;
		
		try
		{
			String query = "INSERT INTO board ( "
					+ " num, title, content, id, visitcount) "
					+ " VALUES ("
					+ " seq_board_num.NEXTVAL, ?, ?, ?, 0)"; // 조회수의 초기값은 0
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate(); //업데이트 갯수를 결과값에 넣기
			
		} catch (Exception e)
		{
			System.out.println("게시물 입력 중 예외발생");
			e.printStackTrace();
		}
		
		return result; // 행의 갯수의 값을 jsp 로 반환
	}
	
//	인수로 전달된 게시물의 일련번호로 하나의 게시물 인출
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
//		member 와 board 테이블 조인해서 name 가져오기 
		String query = "SELECT B.*, M.name "
				+ " FROM member M INNER JOIN board B "
				+ " ON M.id=B.id "
				+ " WHERE num=?";
		
		try
		{
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e)
		{
			System.out.println("게시물 상세보기 중 예외발생");
			e.printStackTrace();
		}
		return dto;
	}
	
//	조회수 증가를 위한 매소드
	public void updateVisitCount(String num) {
//		visitcount컬럼은 number 타입이므로 사칙연산 가능 
		String query = "UPDATE board SET "
				+ " visitcount = visitcount+1 "
				+ " WHERE num=? ";
		
		try
		{
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
//			psmt.executeUpdate(); 
			psmt.executeQuery();
			
		} catch (Exception e)
		{
			System.out.println("게시물 조회수 증가 중 예외발생");
			e.printStackTrace();
		}
	}
	
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		try
		{
//			특정 일련번호에 해당하는 게시물 수정 
			String query = "UPDATE board SET "
					+ " title =?, content=? "
					+ " WHERE num=? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			result = psmt.executeUpdate(); // 행을 반납해야하므로 executeUpdate() 사용 
			
			
		} catch (Exception e)
		{
			System.out.println("게시물 수정 중 예외발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deletePost(BoardDTO dto) {
		int result = 0;
		try
		{
			String query = "DELETE FROM board WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			result = psmt.executeUpdate();
			
		} catch (Exception e)
		{
			System.out.println("게시물 삭제 중 예외발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 페이징 기능 추가
	public List<BoardDTO> selectListPage(Map<String, Object> map){
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		String query = "SELECT * FROM ( "
				+ "  SELECT Tb.*, ROWNUM rNum FROM ( "
				+ "    SELECT * FROM board ";
		
		if(map.get("searchWord") != null) 
		{
			query += " WHERE " + map.get("searchField") 
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "    ORDER BY num DESC "
				+ "    ) Tb "
				+ "  ) "
				+ " WHERE rNum BETWEEN ? AND ?";
		
		try
		{
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
				
			}
			
		} catch (Exception e)
		{
			System.out.println("게시물 조회(페이징) 중 예외발생");
			e.printStackTrace();
		}
		return bbs;
	}
	

}
