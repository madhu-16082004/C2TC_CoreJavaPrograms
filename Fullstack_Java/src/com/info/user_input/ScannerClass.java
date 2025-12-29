package com.info.user_input;

import java.util.Scanner;

public class ScannerClass {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your TNS UID: ");
		int uid = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Enter your Department: ");
		String dept = sc.nextLine();
		
		System.out.println("The TNS UID is: " + uid);
		
		System.out.println("The Dept is: " + dept);
		
		
	}
	


}