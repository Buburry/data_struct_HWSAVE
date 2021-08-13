package hw4;

public class Queue implements List{
    // Implement Queue using Linked List with tail
    Node head;
    Node tail;
    
    public void push(Node node){
        if (head == null){
            head = node;
            tail = head;
        } else {
            if (tail == head) {
                tail = node;
                head.next = tail;
            }
            else {
                tail.next = node;
                tail = node;
            }
        }
    }
    
    public void pop(){
        if (head != null) {
            if (head != tail){
                head = head.next;
            } else {
                head = null;
            }
        } else {
            System.out.println("Error: Queue Underflow");
        }
    }
    
    public Node top(){
        return head;
    }
    
}

