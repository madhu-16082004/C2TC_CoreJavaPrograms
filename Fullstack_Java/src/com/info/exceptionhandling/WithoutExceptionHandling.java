package com.info.exceptionhandling;

public class WithoutExceptionHandling {

    public static void main(String[] args) {

        System.out.println("code begin....");

        try {
            int data = 100 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception handled: " + e.getMessage());
        }

        System.out.println("code begin....");
    }
}
