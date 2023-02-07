package com.pooh.s1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//-context 설정파일의 위치를 알려달라는 것
//**test는 개발할때만 필요하고 배포할때는 필요없다 그래서 경로를 쓸 떄 src~webapp까지도 다 써줘야한다.(얘네들도 똑같이 배포할떄 없는경로이지만, 개발할땐 필요)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"}) // /**/ : spring폴더 아래에 또 다른 폴더가 있어도 된다는 의미
public abstract class MyTestCase {
//230207 5교시 test
	
	//이 클래스의 목적
	//test class 만들때마다 위의 두개를 @Annotation 해줘야한다. 이게 귀찮기 때문에 이 테스트 클래스를 부모클래스로 만들고, 나머지 테스트 클래스들은 이걸 상속받아서 쓰도록 한다

}
