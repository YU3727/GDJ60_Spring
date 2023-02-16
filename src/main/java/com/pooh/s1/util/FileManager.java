package com.pooh.s1.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.print.attribute.standard.OrientationRequested;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
//230216 file upload
	
	//File을 HDD에 저장하는 역할
	//공통적으로 사용하기 위해 각각의 Service에 작성해야할 코드를 클래스로 따로 뻈음
	public String fileSave(MultipartFile multipartFile, String path) throws Exception{
		//1. 어디에 저장할 것인가????
		//	/resources/upload/bankBook/...
		//이 프로젝트를 배포했을때의 경로를 생각해야한다. 파일의 관리는 OS(windows)가 담당하므로, 경로를 알려줄때 주의해야한다
		//OS(windows)에서의 /(root)는 "c://"이며, Linux는 c가 없다....
		
		//2. 저장관리는 OS(windows)가 담당한다
		// -> 이런 문제를 해결하기 위해 이 프로젝트와 OS의 통신이 필요한데 이것을 담당하는 내장객체가 Application이다(단, java에서는 ServletContext 타입)
		
		File file = new File(path);
		
		//폴더가 존재하지 않으면 폴더 생성
		if(!file.exists()) {
			file.mkdirs();
		}
		

		//3. 파일명 생성
		//파일의 이름 중복을 피하기 위해 현재시간을 ms로 바꾼 값을 사용. 또는 UUID 사용
//		Calendar ca = Calendar.getInstance();
//		ca.getTimeInMillis();
		
		//절대 중복되지 않는 이름을 만드는 클래스. UUID(Universial Unique IDentified Name)
		String name = UUID.randomUUID().toString();
		
		//4.확장자 추가.
		//1) OriginalName에서 subString을 이용해서 확장자 추출
		//2. OriginalName을 그냥 붙여넣는 방법
		name = name+"_"+multipartFile.getOriginalFilename();
		
		System.out.println(name);
		
		//5. 파일 저장
		//정보 준비 - 어느경로에 어느이름으로 저장할거냐는 것을 파일객체를 통해 정보를 줘야함
		file = new File(file, name);
		
		//1) multipartFile 객체의 transferTo 메서드 사용
//		multipartFile.transferTo(file); //위에 있는 file의 경로에 해당 name으로 저장하라는 의미
		
		//2) Spring API FileCopyUtils 객체의 copy 메서드 사용
		FileCopyUtils.copy(multipartFile.getBytes(), file); //multipartFile에서 byte 배열을 꺼내고, file에 저장된 정보로 파일을 올리기
		
		//이제 어느 BankBook에 파일이 삽입되어야 하는가 특정지어줘야한다.
		//만들어진 파일 이름을 return
		
		return name;
	}
	
}
