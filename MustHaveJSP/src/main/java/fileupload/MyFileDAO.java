package fileupload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;

// DB 연동을 위해 DBConnPool 상속하기
public class MyFileDAO extends DBConnPool
{
//	새로운 게시물 등록시 첨부파일도 함께 저장함.
	public int insertFile(MyFileDTO dto) {
		int applyResult = 0; // Insert 결과 담을 변수 선언
		try
		{
//			테이블에 Insert 할 쿼리 작성
			String query = "INSERT INTO myfile ( "
					+ " idx, title, cate, ofile, sfile) "
					+ " VALUES ( "
					+ " seq_board_num.nextVal, ?, ?, ? , ?)";
//			?(인파라미터로 값을 받아오니 동적으로 psmt 선언
			psmt = con.prepareStatement(query);
			
//			인파라미터 설정
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getCate());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			
//			쿼리문 실행 applyResult에 담아주기 
			applyResult = psmt.executeUpdate();
			
		} catch (Exception e)
		{
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		return applyResult; // 메서드를 public int 로 선언하였으므로 return 값을 줘야졔 
	}
//	파일 목록 구현을 위해 select 쿼리문 실행
	public List<MyFileDTO> myFileList(){
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		
		String query = "SELECT * FROM myFile ORDER BY idx DESC";
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MyFileDTO dto = new MyFileDTO();
//				오라클 인덱스 순으로 가져오기때문에 1부터 시작함
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setPostdate(rs.getString(6));
				
				fileList.add(dto); // 가져온 값을 넣어주기 !
				
			}
					
		} catch (Exception e)
		{
			System.out.println("SELECT 중 예외 발생");
			e.printStackTrace();
		}
		
		return fileList; // list 객체 반납
	}
	
}
