package hw4;

public class Stack implements List{
    // Implement Stack using Linked List without tail
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
        if (head != null) {
            head = head.next;
        }else{
            System.out.println("Error: Stack Underflow");
        }
    }
    
    public Node top(){
        return head;
    }
    
}
