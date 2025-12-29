package com.info.interfaces;

public class greeting_executor {

    public static void main(String[] args) {

       
        greeting gd = () -> {
            System.out.println("Hi! Welcome to the Java coding");
        };

        gd.greet();
    }
}

