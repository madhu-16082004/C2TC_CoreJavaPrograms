package com.info.assignment;
public class NestedTryCatch {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try started");
            try {
                int a = 10 / 0;  
                System.out.println(a);
            }
            catch (ArithmeticException e) {
                System.out.println("Inner catch: Division by zero");
            }
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer catch: Array index error");
        }
        System.out.println("Program ended safely");
    }
}
