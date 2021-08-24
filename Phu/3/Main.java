package com.DataStructure.HW3;

public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        SinglyLinkedList list = new SinglyLinkedList("list7");
        Node node = list.topFront();
        node.printIDName();
        node = list.topBack();
        node.printIDName();
        list.pushBack(new Node(5906001, "Matthew", 3.50));
        list.pushBack(new Node(5906002, "Mark", 2.75));
        list.pushBack(new Node(5906003, "Luke", 3.00));
        node = list.topFront();
        node.printIDName();
        node = list.topBack();
        node.printIDName();

    }
}
