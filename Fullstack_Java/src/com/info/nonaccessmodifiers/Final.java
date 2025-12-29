package com.info.nonaccessmodifiers;

public class Final {
    int x = 100;

    final void print() {
        System.out.println("Print the value of x: " + x);
    }
    public static void main(String[] args) {

        Final fe = new Final();
        fe.print();

        fe.x = 200;
        fe.print();
    }
}
class Example extends Final {
    
}
