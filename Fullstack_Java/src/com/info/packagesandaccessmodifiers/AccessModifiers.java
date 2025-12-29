package com.info.packagesandaccessmodifiers;

public class AccessModifiers {
	
	public int publicVar = 10;
	protected int protectedvar = 20;
	private int privateVar = 30;
	int defaultvar = 40;
	
	public	void showDetails() {
		System.out.println("Public Variable: "+publicVar);
		System.out.println("Protected Variable: "+protectedvar);
		System.out.println("Private Variable: "+privateVar);
		System.out.println("Default Variable: "+defaultvar);
	}

	public static void main(String[] args) {
		
		AccessModifiers amd = new AccessModifiers();
		amd.showDetails();

	}
}