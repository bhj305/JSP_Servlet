package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil
{
//	파일 업로드 메서드
	public static String uploadFile(HttpServletRequest req, String sDirectory) 
			throws ServletException, IOException{
//		Part 객체를 통해 서버로 전송된 파일명 읽어오기
		Part part = req.getPart("ofile");
		
//		Part 객체의 헤더값 중 content-disposition 읽어오기
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader="+ partHeader); //디버깅용으로 출력해보기
		
//		헤더값에서 파일명 잘라내기. split() 메서드로 분리한 후 더블쿼테이션 제거
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"","");
		
//		전송된 파일이 있다면 디렉토리에 저장
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName);
		}
//		원본 파일명 반환
		return originalFileName;
	}
	
//	파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".")); // lastIndexOf를 사용하는 이유는 점(.)이 2개 이상 포함될 수 있기 떄문
		String now = new SimpleDateFormat("yyyyMMdd_HmmsS").format(new Date());
		String newFileName = now + ext;
		
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
//		변경된 파일명 반환
		return newFileName;
				
	}
}
