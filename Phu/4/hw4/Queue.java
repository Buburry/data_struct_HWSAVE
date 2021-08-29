package com.DataStructure.Stack;

public class Queue implements List{
    // Implement Queue using Linked List with tail - FIFO
    Node head;
    Node tail;
    
    public void push(Node node){
        if (head == null){
            head = node;
            tail = node;
        }else{
            // (Non-empty list)
            tail.next = node;
            tail = node;
        }
    }
    
    public void pop(){
        if (head != null){
            if (head != tail){
                // (List of many nodes)
                head = head.next;
            }else{
                // (List of a single node)
                head = null;
                tail = null;
            }
        }else{
            System.out.println("Error: Queue Underflow");
        }
    }
    
    public Node top(){
        return head;
    }
}
