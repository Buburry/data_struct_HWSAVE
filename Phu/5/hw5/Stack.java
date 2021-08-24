package com.DataStructure.Tree;

public class Stack {
    Node[] arr; // regular array
    int capacity;
    int size;

    public Stack(int cap){
        capacity = cap;
        arr = new Node[cap];
    }
    
    public void push(Node node){
        if (!isFull()){
            arr[size] = node;
            size++;
        }else{
            System.out.println("Stack Overflow!!!");
        }
    }
    public Node pop(){
        if (!isEmpty()){
            size--;
            return arr[size];
        }else{
            System.out.println("Stack Underflow!!!");
            return null;
        }
    }
    public boolean isFull(){
        return size == capacity;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    
    public void printStack(){
        if (!isEmpty()) {
            System.out.print("[Bottom] ");
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i].data + " ");
            }
            System.out.println("[Top]");
        } else {
            System.out.println("Empty Stack!!!");
        }
    }
}
