package com.DataStructure.Tree;

public class Queue {
    Node[] arr; // circular Queue
    int capacity;
    int front;
    int back;
    int size;
    
    public Queue(int cap){
        capacity = cap;
        arr = new Node[cap];
    }
    
    public void enqueue(Node node){
        if (!isFull()){
            if(back + 1 == capacity) {
                arr[back] = node;
                back = 0;
            } else {
                arr[back] = node;
                back++;
            }
            size++;
        }else{
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){
        if (!isEmpty()){
            size--;
            if (front + 1 == capacity) {
                front = 0;
                return arr[capacity - 1];
            } else {
                front++;
                return arr[front-1];
            }
        }else{
            System.out.println("Queue Underflow!!!");
            return null;
        }
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public boolean isFull(){
        return size == capacity;
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");
            for(int i = 0; i < size; i++) {
                int index;
                index = i+front;
                if(index >= capacity) index -= capacity;
                System.out.print(arr[index].data + " ");
            }
            System.out.println("[Back]");
        }else{
            System.out.println("Empty Queue!!!");
        }
    }
}
