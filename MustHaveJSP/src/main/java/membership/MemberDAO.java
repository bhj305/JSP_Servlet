package membership;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

// DB 연결은 자주 사용하므로 상속으로 처리하면 편리하다.
public class MemberDAO extends JDBConnect
{
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw); // 부모클래스의 생성자
	} 

	public MemberDAO(ServletContext application)
	{
		super(application); // web.xml에 접근하여 파라미터를 얻어옴
	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		try
		{
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
//			하나의 정보만 가져오므로 if로 가져옴, 게시판같은 경우는 반복문으로 가져와야 함.
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return dto;
	}
	
//	회원가입을 위한 메서드
	public int insertSignUp(MemberDTO dto) {
		int result = 0;
		
		try
		{
			String query = "INSERT INTO member ( "
					+ " id, pass, name, regidate) "
					+ " VALUES( "
					+ " ?, ?, ?, sysdate) ";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			
			result = psmt.executeUpdate();
					
		} catch (Exception e)
		{
			System.out.println("회원가입 중 예외발생");
			e.printStackTrace();
		}
		return result;
	}
}
