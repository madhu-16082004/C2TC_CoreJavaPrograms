package com.info.nonaccessmodifiers;

public class Static {
	
	static int count;
	
	int variable = 24;	
	
	static {
		
		count = 20;
		
		
		
		System.out.println("display the static variable count:"+count);
		
	}
	
	static void display(){
		System.out.println("display the static method with count:"+count);
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("printing the main method");
		
		display();
		
	}

}
