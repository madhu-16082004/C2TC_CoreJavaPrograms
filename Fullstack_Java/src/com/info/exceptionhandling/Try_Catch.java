package com.info.exceptionhandling;

public class Try_Catch {
	public static void main(String[] args) {
		System.out.println("I am in main method");
		int result;
		try {

			result = TryCatch.performDivision(12, 0);
			if (result != 0)
				System.out.println("Divisin is " + result);

			System.out.println("------------------------------");

			result = TryCatch.performDivision(12, 3);
			if (result != 0)
				System.out.println("Division is " + result);

			System.out.println("------------------------------");
		} catch (ArithmeticException e) {
			System.out.println("I am in catch block: " + e.getMessage());
			
			
		}
		System.out.println(TryCatch.performDivision(12f, 5f));

		System.out.println("------------------------------");
		System.out.println(TryCatch.performDivision(12f, 0f));

	}
}
