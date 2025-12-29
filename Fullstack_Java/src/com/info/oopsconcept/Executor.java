package com.info.oopsconcept;

public class Executor {

	public static void main(String[] args) {
		
		Encapsulation ED = new Encapsulation();
		ED.setName("M S Dhoni");
		ED.setAge(44);
		ED.setJersyNo(7);
		ED.setrole("batting");
		
		System.out.println(ED);
		
		Executor e = new Executor();
		System.out.println(e);

	}
}