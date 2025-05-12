package el;

public class MyELClass
{
//	주민번호를 인수로 받아 성별 판단
	public String getGender(String jumin) {
		String returnStr = "";
		int beginIdx = jumin.indexOf("-") + 1;
		String genderStr = jumin.substring(beginIdx, beginIdx+1); // 성별 구분을 위해 하이픈 뒤 숫자 한개 잘라오기
		int genderInt = Integer.parseInt(genderStr); // 비교를 위해 스트링을 숫자로 변환
		
		if(genderInt == 1 || genderInt == 3) {
			returnStr = "남자";
		} else if(genderInt == 2 || genderInt == 4) {
			returnStr = "여자";
		} else if(genderInt == 5 || genderInt == 6){
			returnStr = "국내 거주 외국인";
		} else {
			returnStr = "주민번호 오류입니다.";
		}
//		모든 문자가 숫자라면 true 반환
		return returnStr;
	}
	
//	문자열이 숫자로만 이루어져 있는지 판단하는 정적메서드
	public static boolean isNumber(String value) {
		char[] chArr = value.toCharArray();
		for(int i = 0; i <chArr.length ; i++) {
			if(!(chArr[i] >= '0' && chArr[i] <= '9')) {
				return false; // 문자인 경우 false 반환
			}
		}
		return true;
	}
	
	public static String showGugudan(int limitDan) {
		StringBuffer sb = new StringBuffer();
		try
		{
			sb.append("<table border='1'>");
			for(int i = 2; i <= limitDan ; i++) {
				sb.append("<tr>");
				for(int j = 1 ; j < 10; j++) {
					sb.append("<td>" + i + " * " + j + "=" + (i*j) + "</td>");
				}
				sb.append("</tr>");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString(); // StringBuffer를 String 으로 형변환 후 반환 
	}
}
