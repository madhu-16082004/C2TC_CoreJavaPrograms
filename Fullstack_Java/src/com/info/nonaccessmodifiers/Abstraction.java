package com.info.nonaccessmodifiers;

abstract class shape_demo{
	
	abstract void draw(); 		
}

class pentagon extends shape_demo{
	
	void draw() {
		System.out.println("draw a pentagon");
	}
}
public class Abstraction {

	public static void main(String[] args) {
		
		shape_demo s =new pentagon();
		s.draw();

	}

}
