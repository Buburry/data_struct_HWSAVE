package hw3;

public class DoublyLinkedList {
    Node head = new Node();
    Node tail = new Node();
    String listName;
    
    public DoublyLinkedList(String name){
        this.listName = name;
        head.next = tail;
        tail.previous = head;
    }
    
    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        }
        else{
            tail.previous.previous.next = tail;
            tail.previous = tail.previous.previous;
        }
    }
    
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }
        else{
            head.next.next.previous = head;
            head.next = head.next.next;
        }
    }
    
    public Node topFront(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return head.next;
        }
    }
    
    public Node topBack(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return tail.previous;
        }
    }
    
    public void pushFront(Node node){
         if (isEmpty()) {
            head.next = node;
            node.previous = head;
            tail.previous = node;
            node.next = tail;
        } else {
            node.next = head.next;
            head.next.previous = node;
            head.next = node;
            node.previous = head;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()) {
            head.next = node;
            node.previous = head;
            tail.previous = node;
            node.next = tail;
        } else {
            node.next = tail;
            node.previous = tail.previous;
            tail.previous.next = node;
            tail.previous = node;
        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node curr = head.next;
            while (curr != tail) {
                if (curr.student_id == id) {
                    return curr;
                }
                
                curr = curr.next;
            }
            return new Node("Student Not Found!");
        }
    }
    
    public Node eraseNode(int id){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node curr = head.next;
            while (curr != tail) {
                if (curr.student_id == id) {
                    curr.previous.next = curr.next;
                    curr.next.previous = curr.previous;
                    return curr;
                }
                curr = curr.next;
            }
            return new Node("Student Not Found!");
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){
        node1.next.previous = node2;
        node2.next = node1.next;

        node1.next = node2;
        node2.previous = node1;
    }
    
    public void addNodeBefore(Node node1, Node node2){
        node1.previous.next = node2;
        node2.previous = node1.previous;

        node1.previous = node2;
        node2.next = node1;
    }
    
    public boolean isEmpty(){
        if (head.next == tail) return true;
        return false;
    }
    
    public void merge(DoublyLinkedList list){
        tail.previous.next = list.head.next;
        list.head.next.previous = tail.previous;

        list.tail.previous.next = tail;
        tail.previous = list.tail.previous;
    }
    
    public void printStructure(){
        Node curr = head.next;
        System.out.print(listName + ": head <-> ");
        
        while(curr != tail){
            System.out.print("{" + curr.student_id + "} <-> ");
            curr = curr.next;
        }

        System.out.println("tail");
    }
    
    // This may be useful for you for implementing printStructure()
    public void printStructureBackward(){ 
        Node current = tail.previous;
        System.out.print(listName + ": tail <-> ");

        while(current != head){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.previous;
        }
        System.out.println("head");
    }
    
    public Node whoGotHighestGPA(){
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node curr = tail.previous;
            Double gpa_max = Double.MIN_VALUE;
            Node temp = null;

            while (curr != head){
                if (curr.gpa > gpa_max) {
                    gpa_max = curr.gpa;
                    temp = curr;
                }
                
                curr = curr.previous;
            }

            return temp;
        }
    }
}
