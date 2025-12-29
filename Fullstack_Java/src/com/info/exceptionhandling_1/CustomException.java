package com.info.exceptionhandling_1;

class InvalidBalanceException extends Exception {
    InvalidBalanceException(String message) {
        super(message);
    }
}

public class CustomException {

    static void withdraw(int balance) throws InvalidBalanceException {
        if (balance < 3000) {
            throw new InvalidBalanceException("Insufficient balance");
        }
        System.out.println("Withdrawal successful");
    }

    public static void main(String[] args) {
        try {
            withdraw(1000);
        } catch (InvalidBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}