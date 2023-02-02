package com.pooh.s1.army;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//Annotation 방법. @Component를 쓰면 솔져의 객체를 만드세요의 의미
@Component
public class Soldier {
//230202 4교시 IOC 연습
	
//	@Autowired
//	private Gun gun;
	
	@Autowired //dataType이 일치하는 객체를 먼저 찾음
	@Qualifier("sg") //못찾으면 그후 이름이 sg인것을 찾아서 넣어줌
	private Gun shotGun; //Gun 객체를 안만들고 Gun을 상속받은 ShotGun, Rifle에 autowired를 쓰면 변수명이 같은걸로 연결해준다
	@Autowired
	@Qualifier("rf")
	private Rifle rifle;
	
	private String name;
	private int age;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
