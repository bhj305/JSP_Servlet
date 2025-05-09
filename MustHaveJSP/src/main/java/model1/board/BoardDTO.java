package model1.board;

public class BoardDTO
{
//	board 테이블에 생성된 컬럼과 동일해야함.
	private String num; // num은 테이블상 number 인데, 주고 받을 땐 String 으로 하기 
	private String title;
	private String content;
	private String id;
	private java.sql.Date postdate;
	private String visitcount;
	private String name; // member 테이블과 join 하기 위해 멤버변수로 추가해야함.
	
//	이외의 좋아요, 즐겨찾기, 회원등급 등을 위한 컬럼도 고려해 볼 수 있음.
//	특별한 이유가 없다면 생성자는 생략됨.
	
	public String getNum()
	{
		return num;
	}
	public void setNum(String num)
	{
		this.num = num;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public java.sql.Date getPostdate()
	{
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate)
	{
		this.postdate = postdate;
	}
	public String getVisitcount()
	{
		return visitcount;
	}
	public void setVisitcount(String visitcount)
	{
		this.visitcount = visitcount;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
