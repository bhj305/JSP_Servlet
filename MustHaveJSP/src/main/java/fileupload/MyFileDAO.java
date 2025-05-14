package fileupload;

import common.DBConnPool;

// DB 연동을 위해 DBConnPool 상속하기
public class MyFileDAO extends DBConnPool
{
//	새로운 게시물 등록시 첨부파일도 함께 저장함.
	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		try
		{
			String query = "INSERT INTO myfile ( "
					+ " idx, title, cate, ofile, sfile) "
					+ " VALUES ( "
					+ " seq_board_num.nextVal, ?, ?, ? , ?)";
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
		return applyResult;
	}
}
