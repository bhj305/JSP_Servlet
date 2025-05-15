package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil
{
//	파일 업로드 메서드
	public static String uploadFile(HttpServletRequest req, String sDirectory) 
			throws ServletException, IOException
	{
//		Part 객체를 통해 서버로 전송된 파일명 읽어오기
		Part part = req.getPart("ofile");
		
//		Part 객체의 헤더값 중 content-disposition 읽어오기
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader="+ partHeader); //디버깅용으로 출력해보기
		
//		헤더값에서 파일명 잘라내기. split() 메서드로 분리한 후 더블쿼테이션 제거
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"","");
		
//		전송된 파일이 있다면 디렉토리에 저장
		if(!originalFileName.isEmpty()) { // originalFileName 이 empty가 아니면 = 파일이 있다면 
			part.write(sDirectory + File.separator + originalFileName);
		}
//		원본 파일명 반환
		return originalFileName;
	}
	
//	파일명 변경을 위한 메서드
	public static String renameFile(String sDirectory, String fileName) 
	{
		String ext = fileName.substring(fileName.lastIndexOf(".")); // lastIndexOf를 사용하는 이유는 점(.)이 2개 이상 포함될 수 있기 떄문
		String now = new SimpleDateFormat("yyyyMMdd_HmmsS").format(new Date());
		String newFileName = now + ext; //시간타입과 확장자명을 새로운 파일 이름으로 
		
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
//		변경된 파일명 반환
		return newFileName;
	}
	
	
	// multiple 속성 추가로 2개 이상 파일 업로드 
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory) 
			throws ServletException, IOException
	{
//		파일명 저장을 위한 컬렉션 생성
		ArrayList<String> listFileName = new ArrayList<String>();
		Collection<Part> parts = req.getParts(); // Part 객체를 통해 서버로 전송된 파일명 읽어오기
		
		for(Part part: parts) {
			if(!(part.getName().equals("ofile"))) {
				continue;
			}
			String partHeader = part.getHeader("content-disposition");
			
			System.out.println("partHeader=" + partHeader); // 디버깅용 확인 
			
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"","");
			
//			전송된 파일이 있다면 디렉토리에 저장
			if(!originalFileName.isEmpty()) { // originalFileName 이 empty가 아니면 = 파일이 있다면 
				part.write(sDirectory + File.separator + originalFileName);
			}
//			컬렉션에 추가
			
			listFileName.add(originalFileName);
		}
		return listFileName;
	}
}
