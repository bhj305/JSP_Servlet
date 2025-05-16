package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	// 14-7 파일 다운로드, 명시한 파일을 찾아 다운로드
	public static void download(HttpServletRequest req, HttpServletResponse resp, 
			String directory, String sfileName, String ofileName) {
		
		String sDirectory = req.getServletContext().getRealPath(directory);
		
		try
		{
//			파일을 찾아 입력스트림 생성
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
//			한글 파일명 꺠짐 방지 (13장 Download.jsp에서 복사 붙여넣기)
			String client = req.getHeader("User-Agent");
		    if (client.indexOf("WOW64") == -1) {
		        ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1"); // 익스플로러가 아닌 경우
		    }
		    else {
		    	ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1"); // 익스플로러인 경우
		    }
			
		    resp.reset(); // 응답 헤더 초기화
		    resp.setContentType("application/octet-stream"); 
		    /* 파일 다운로드 창이 뜰때 원본 파일명이 뜨도록 설정 */
		    resp.setHeader("Content-Disposition", 
		                       "attachment; filename=\"" + ofileName + "\"");
		    resp.setHeader("Content-Length", "" + file.length() );
		    
		    
		    // response 내장 객체로부터 새로운 출력 스트림 생성
		    OutputStream oStream = resp.getOutputStream();  

		    // 출력 스트림에 파일 내용 출력 (버퍼를 사용하면 처리속도가 빨라짐)
		    byte b[] = new byte[(int)file.length()];
		    int readBuffer = 0;    
		    while ( (readBuffer = iStream.read(b)) > 0 ) {
		    	oStream.write(b, 0, readBuffer);
		    }
		    
//		    입출력 스트림 닫음
		    iStream.close();
		    oStream.close();
		    
		} catch (FileNotFoundException e)
		{
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("예외가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
		if(file.exists()) {
			file.delete();
		}
	}
}
