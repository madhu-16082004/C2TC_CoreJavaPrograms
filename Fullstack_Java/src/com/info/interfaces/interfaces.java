package com.info.interfaces;

interface Fruits{
	
 void sweet() ; 
}

public class interfaces implements Fruits {

	public void sweet() {
		System.out.println("The fruits are sweet!!!");
	}
	
	public static void main(String[] args) {
		
		
		interfaces obj1 = new interfaces();
		obj1.sweet();

	}

}
