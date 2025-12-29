package com.info.assignment;
public class MultipleCatch {
	
    public static void main(String[] args) {

        try {
            int[] numbers = {30, 60, 90};

            for(int i=1;i<60;i++) {
            	numbers[-1]=i*i;
            }
            	for (int i=0;i<60;i++) {
            		numbers[i]=i/i;
            	}
        }
        catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index is out of range");
        }
    }
}
