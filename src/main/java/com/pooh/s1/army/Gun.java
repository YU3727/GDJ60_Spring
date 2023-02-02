package com.pooh.s1.army;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Gun {
//230202 4교시
	@Autowired
	private Bullet bullet;
	
	
	public Gun() {
		// TODO Auto-generated constructor stub
	}
	

	public Gun(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public void trigger() {
		bullet.sound();
	}
}
