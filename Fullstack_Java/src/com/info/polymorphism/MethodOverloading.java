package com.info.polymorphism;

public class MethodOverloading {
	
	int addition(int a, int b)
	{
		return a + b;
	}
	
	float addition(float a, float b)
	{
		return a + b;
	}

	public static void main(String[] args) {
		
		MethodOverloading mod = new MethodOverloading();
		
		System.out.println("Addtion is: "+mod.addition(5, 10));
		System.out.println("Addtion is: "+mod.addition(5.5f, 7.7f));
		
		
	}
}
