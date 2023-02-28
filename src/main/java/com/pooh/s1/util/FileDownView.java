package com.pooh.s1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.pooh.s1.board.BoardFileDTO;

@Component("fileDownView")
public class FileDownView extends AbstractView{
//AbstractView를 상속받은 클래스에 한해서 BeanNameViewResolver가 먼저 탐색을 해본다.
	
	//추상메서드 얘만 만들면 됨.
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("fileDownView");
		//ModelAndView에 adObject를 할 때도 (key, value) 형태로 입력했는데, 이 메서드의 매개변수 또한 Map<key, value>의 형태로 model에 담아서 받아오려한다
		//model에 있는 boardFileDTO를 꺼내보자(파일 이름을 가져오기 위함)
		BoardFileDTO boardFileDTO = (BoardFileDTO)model.get("boardFile");
		//확인용 - 이 결과로 Controller에서 받아온 Model을 잘 받아서 쓸 수 있다는걸 알 수 있다.
//		System.out.println(boardFileDTO.getFileNum());
//		System.out.println(boardFileDTO.getFileName());
		
		//model에 뭐가 들어가있는지 알수 없기 때문에 무슨 키가 있는지 확인
		//set은 collection중 중복이 없는 것. set은 하나씩 꺼내는게 없고 iterator라는 것으로 꺼내줌.(iterator는 java.util의 것을 써야함)
//		Iterator<String> it = model.keySet().iterator();
//		//it에 다음값이 있는지 물어봄. 없으면 false
//		while(it.hasNext()) {
//			//.next()는 값을 꺼내라는 의미
//			String key = it.next();
//			System.out.println("Key : "+key);
//		}
		
		//it(model)에 있는 key 중에서 boardName, boardFile이 필요하다.
		//이제 서버의 파일을 찾아서 클라이언트로 보내는 코드를 짜줘야한다.
		//마찬가지로 파일 이름과 경로가 필요함
		//경로 준비
		String path = (String)model.get("boardName");
		path = "resources/upload/"+path+"/";
		
		//파일을 꺼내려면 OS와 대화해야하므로, ServletContext가 필요하다. 그럼 또 매개변수 넣고 할거냐? 불가능하다. override한 메서드이기 때문..
		//그래서 request가 있다.
		path = request.getSession().getServletContext().getRealPath(path);
		
		//파일 객체 생성
		File file = new File(path, boardFileDTO.getFileName());
		
		//아래는 응답이므로 response에 담아서 내보낸다.
		//파일 이름이 한글일수도 있기 때문에 응답시 한글 인코딩 처리가 필요하다.(Spring에서 다 해주지만 혹시 몰라서)
		//web.xml에 filter 처리를 해두긴 했지만 혹시 몰라서.
		response.setCharacterEncoding("UTF-8");
		
		//총 파일 크기와 시간당 전송되는 데이터량을 알면 파일 다운로드 예상 시간을 알수있다.(시간당전송량은 client PC가 안다)
		//파일의 크기를 header에 담아서 보내준다. 파일 크기 정보는 file에 담겨져있다.
		response.setContentLength((int)file.length());
		
		//다운로드 받을 때 파일의 이름을 지정하고, 인코딩도 설정
		String downName = boardFileDTO.getOriName();
		downName = URLEncoder.encode(downName, "UTF-8"); //URLEncoder 이런건 쓰려면 찾아보는식으로 해야한다. 많기때문에 일일이 알려주기 어려움
		
		//header 정보 설정 - 이거는 그냥 강사님꺼 따라 쓰기
		//client 컴퓨터가 파일을 다운로드 받을때 준비를 하기 위한 정보를 준다.
		response.setHeader("Content-Disposition", "attachment;fileName=\""+downName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary"); //file은 text, img등이 아니라 0과 1로 이루어진 데이터. 즉, binary이다
		
		
		//Client로 전송하기 - 따로 자동으로 해주는 애들이 없어서 수동으로 만들어야한다.
		//1. file 읽어오기(항상 프로그램 기준으로 생각. 프로그램으로는 파일이 들어오는 것)
		FileInputStream fi = new FileInputStream(file); //빨대 꽂아놓은것. 연결선(읽기 위한 준비)
		OutputStream os = response.getOutputStream(); //파일을 읽어들여서 내보내기, url이 필요하므로, 응답객체인 response에 요청한다
		
		//2. file 보내기
		FileCopyUtils.copy(fi, os); //fi스트림에서 읽어와서, os로 copy해서 내보내라는 의미
		
		//3. 연결해제
		os.close();
		fi.close();
		
	}
}
