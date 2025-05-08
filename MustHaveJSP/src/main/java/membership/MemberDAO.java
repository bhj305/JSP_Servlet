package membership;

import common.JDBConnect;

// DB 연결은 자주 사용하므로 상속으로 처리하면 편리하다.
public class MemberDAO extends JDBConnect
{
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw); // 부모클래스의 생성자
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
}
