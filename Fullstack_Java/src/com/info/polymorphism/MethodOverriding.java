package com.info.polymorphism;

class AnimalClass{
	void sound() {
		System.out.println("Animals can make sound");
	}
}

class Elephant extends AnimalClass{
	void sound() {
		System.out.println("Elephant trumpets......");
	}
}

public class MethodOverriding{

	public static void main(String[] args) {
	
		AnimalClass ac = new AnimalClass();
		ac.sound();
		
	}
}