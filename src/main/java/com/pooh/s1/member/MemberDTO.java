package com.pooh.s1.member;

import java.util.List;

public class MemberDTO {
//230202 8교시
	
	//Annotation으로 객체를 생성하면 프로젝트 전체를 통틀어서 하나의 객체만 만들기 때문에, Data를 담아두는 DTO에는 Annotation으로 객체를 생성하지 않는다
	
	private String id;
	private String pw;
	private String memberName;
	private String memberPhone;
	private String email;
	private List<RoleDTO> roleDTOs;
	
	public MemberDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
