package com.pooh.s1.member;

public class MemberDTO {
//230202 8교시
	
	//Annotation으로 객체를 생성하면 프로젝트 전체를 통틀어서 하나의 객체만 만들기 때문에, Data를 담아두는 DTO에는 Annotation으로 객체를 생성하지 않는다
	
	private String ID;
	private String PW;
	private String memberName;
	private String memberPhone;
	private String email;
	
	public MemberDTO() {
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
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
