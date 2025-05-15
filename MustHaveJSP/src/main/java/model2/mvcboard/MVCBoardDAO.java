package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool
{

	public MVCBoardDAO()
	{
		super();
	}
	
//	게시물 갯수 조회
	public int selectCount(Map<String, Object> map) {
		
		int totalCount = 0; 
		String query = "SELECT COUNT(*) FROM mvcboard";
		
		if(map.get("searchWord") != null) 
		{
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next(); 
			totalCount = rs.getInt(1); // 검색된 게시물 개수 저장
		} catch (Exception e)
		{
			System.out.println("게시물 카운트 중 예외발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
//	페이징 기능
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map){
		List<MVCBoardDTO> board = new Vector();
		String query = "SELECT * FROM ( "
				+ "  SELECT Tb.*, ROWNUM rNum FROM ( "
				+ "    SELECT * FROM mvcboard ";
		
		if(map.get("searchWord") != null) 
		{
			query += " WHERE " + map.get("searchField") 
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "    ORDER BY idx DESC "
				+ "    ) Tb "
				+ "  ) "
				+ " WHERE rNum BETWEEN ? AND ?";
		
		try
		{
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery(); // 쿼리문 실행
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
			
		} catch (Exception e)
		{
			System.out.println("게시물 조회(페이징) 중 예외발생");
			e.printStackTrace();
		}
		return board; // 목록 반환
	}
	
//	글쓰기 처리 시 첨부파일까지 함께 입력
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		
		try
		{
			String query = "INSERT INTO mvcboard ( "
					+ " idx, name, title, content, ofile, sfile, pass) "
					+ " VALUES ("
					+ " seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass()); // 수정 삭제를 위한 인증 비밀번호
			
			result = psmt.executeUpdate(); //업데이트 갯수를 결과값에 넣기
			
		} catch (Exception e)
		{
			System.out.println("게시물 입력 중 예외발생");
			e.printStackTrace();
		}
		
		return result; // 행의 갯수의 값을 jsp 로 반환
	}
}
