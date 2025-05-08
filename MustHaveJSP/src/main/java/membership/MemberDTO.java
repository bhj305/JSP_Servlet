package membership;

public class MemberDTO
{
//	멤버변수: member 테이블의 컬럼과 동일하게 설정한다.
	private String id;
	private String pass;
	private String name;
	private String regidate;
	
//	정보은닉을 위해 getter, setter 를 통해 접근한다.
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getRegidate()
	{
		return regidate;
	}
	public void setRegidate(String regidate)
	{
		this.regidate = regidate;
	}
	
	
}
