package hw4;

public class Stack implements List{
    // Implement Stack using Linked List without tail.
    Node head;
    
    // pushFront so head is the top position.
    public void push(Node node) {
        if (head == null) { // check if linked-list is empty.
            // set head to input node.
            head = node;
        }
        else {
            // set input node to head.
            node.next = head;
            head = node;
        }
    }
    
    public void pop() {
        if (head != null) { // check if linked-list is not empty.
            // remove head node.
            head = head.next;
        }
        else {
            // print this if stack empty.
            System.out.println("Error: Stack Underflow");
        }
    }
    
    public Node top() {
        // get top node.
        return head;
    }
    
}


