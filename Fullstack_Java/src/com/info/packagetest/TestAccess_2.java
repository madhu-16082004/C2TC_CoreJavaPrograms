package com.info.packagetest;

import com.info.packagetest.AccessModifiers;

public class TestAccess_2 {

	public static void main(String[] args) {
		
		AccessModifiers amd = new AccessModifiers();
		
		System.out.println("Public Variable: "+amd.publicVar);
		//System.out.println("Protected Variable: "+amd.protectedvar);
		//System.out.println("Private Variable: "+amd.privateVar);
		//System.out.println("Default Variable: "+amd.defaultvar);

	}

}

