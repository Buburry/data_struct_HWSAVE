package hw5;

public class Stack {
    Node[] arr; // regular array
    int capacity;
    int size;

    public Stack(int cap){
        
    }
    
    public void push(Node node){
        if (!isFull()){
            // do something
        }else{
            System.out.println("Stack Overflow!!!");
        }
    }
    public Node pop(){
        if (!isEmpty()){
            // do something
        }else{
            System.out.println("Stack Overflow!!!");
        }
        return null; // fix this (out of place)
    }
    public boolean isFull(){
        return false; // fix this
    }
    public boolean isEmpty(){
        return false; // fix this
    }
    
    public void printStack(){
        if (!isEmpty()) {
            System.out.print("[Bottom] ");
            // do something here
            System.out.println("[Top]");
        } else {
            System.out.println("Empty Stack!!!");
        }
    }
}
