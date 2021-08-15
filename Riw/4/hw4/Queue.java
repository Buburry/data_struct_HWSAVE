package hw4;

public class Queue implements List{
    // Implement Queue using Linked List with tail.
    Node head;
    Node tail;
    
    // pushBack so END node is at the tail position.
    public void push(Node node) {
        if (head == null) { // check if linked-list is empty.
            // set head and tail to input node.
            head = node;
            tail = head;
        } 
        else {
            // check if linked-list have only 1 node and set tail to input node.
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
    
    // popFront so FRONT node is pop out.
    public void pop(){
        if (head != null) { // check if linked-list is not empty.
            // remove head node
            head = head != tail ? head.next : null;
        } 
        else {
            // print this if queue empty.
            System.out.println("Error: Queue Underflow");
        }
    }
    
    public Node top() {
        // get FRONT node.
        return head;
    }
    
}

