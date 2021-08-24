package com.DataStructure.main;

public class Main {
    public static void main(String[] args) {

        DynamicArray var = new DynamicArray(2);
//        for (int i=0; i<10; i++){
//            var.pushBack(i*i + 1);
//        }
//        var.printStructure();
//        System.out.println(var.get(10));
//        var.set(10, 555);
//        var.remove(10);
//        var.printStructure();
        System.out.println(var.popBack());
    }
}
