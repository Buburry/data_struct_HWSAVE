package com.DataStructure.Stack;

public class Stack implements List{
    // Implement Stack using Linked List without tail - LIFO
    Node head;
    
    public void push(Node node){
        if (head == null){
            head = node;
        }else{
            node.next = head;
            head = node;
        }
    }
    
    public void pop(){
        if (head != null){
            head = head.next;
        }else{
            System.out.println("Error: Stack Underflow");
        }
    }

    public Node top(){
        return head;
    }
}
