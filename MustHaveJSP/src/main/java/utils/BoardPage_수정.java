package utils;

public class BoardPage_수정
{
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl, String searchField, String searchWord) {
		String pagingStr ="";
//		검색을 안하고 첫페이지나 마지막페이지를 누르면 아무것도 안뜸 if 로 처리해줘야하나???
		int totalPages = (int)(Math.ceil(((double) totalCount/ pageSize)));
		
		int pageTemp = (((pageNum - 1)/blockPage) * blockPage)+1;
		
		if(searchField == null && searchWord == null) {
			if(pageTemp != 1) {
				pagingStr += "<a href='"+ reqUrl +"?pageNum=1'>[첫 페이지]</a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='"+ reqUrl + "?pageNum=" + (pageTemp - 1) + "'>[이전 블록]</a>";
			}
		} else {
			if(pageTemp != 1) {
				pagingStr += "<a href='"+ reqUrl + "?searchField=" 
						+  searchField  + "&searchWord=" +  searchWord  +"&pageNum=1'>[첫 페이지]</a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='"+ reqUrl + "?searchField=" 
						+  searchField  + "&searchWord=" +  searchWord  + "&pageNum=" + (pageTemp - 1) + "'>[이전 블록]</a>";
			}
		}
		
		
		
//		기존 코드) 번호를 누를 떄 동작됨. 
//		int blockCount = 1;
//		while(blockCount <= blockPage && pageTemp <= totalPages) {
//			if(pageTemp == pageNum) {
//				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
//			}else {
//				pagingStr += "&nbsp;<a href='" + reqUrl +"?pageNum="+ pageTemp
//						+ "'>" + pageTemp + "</a>&nbsp;";
//			}
//			pageTemp++;
//			blockCount++;
//		}
		
		
//		만약 검색이 있다면 같이 연결해서 동작하도록 처리해야함 !! ?searchField=title&searchWord=1
		int blockCount = 1;
		
			while(blockCount <= blockPage && pageTemp <= totalPages) {
				if(pageTemp == pageNum) {
					pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
				} else if(searchWord != null) {
					pagingStr += "&nbsp;<a href='" + reqUrl + "?searchField=" 
							+  searchField  + "&searchWord=" +  searchWord  +"&pageNum="+ pageTemp
							+ "'>" + pageTemp + "</a>&nbsp;";
				}
				else{
					pagingStr += "&nbsp;<a href='" + reqUrl +"?pageNum="+ pageTemp
							+ "'>" + pageTemp + "</a>&nbsp;";
				}
				pageTemp++;
				blockCount++;
			}
		
		
		if(searchField == null && searchWord == null) {
			if(pageTemp <= totalPages){
				pagingStr += "<a href='" + reqUrl +"?pageNum=" + pageTemp
						+ "'>[다음 블록]</a>";
				pagingStr +="&nbsp;";
				pagingStr += "<a href='" + reqUrl +"?pageNum=" + totalPages
						+ "'>[마지막 페이지]</a>";
			}
			
		} else {
			
			if(pageTemp <= totalPages){
				pagingStr += "<a href='" + reqUrl+ "?searchField=" 
						+  searchField  + "&searchWord=" +  searchWord  +"&pageNum=" + pageTemp
						+ "'>[다음 블록]</a>";
				pagingStr +="&nbsp;";
				pagingStr += "<a href='" + reqUrl+ "?searchField=" 
						+  searchField  + "&searchWord=" +  searchWord  +"&pageNum=" + totalPages
						+ "'>[마지막 페이지]</a>";
			}
		}
		
		return pagingStr;
	}
}
