package hw3;

public class SinglyLinkedList {
    Node head;
    String listName;
    
    public SinglyLinkedList(String name){
        listName = name;
        head = null;
    }
    
    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        }
        else{
            Node curr;

            if (head.next == null) {
                head = null;
                return;
            }
            for (curr = head; curr.next.next != null; curr = curr.next) {}
            curr.next = null; 
        }
    }
    
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else {
            head = head.next;
        }
    }
    
    public Node topFront(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return head;
        }
    }
    
    public Node topBack(){ 
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node curr;
            for (curr = head; curr.next != null; curr = curr.next) {}
            return curr;
        }
    }
    
    public void pushFront(Node node){
        if (isEmpty()){
            node.next = null;
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()){
            node.next = null;
            head = node;
        } else {
            Node curr;
            for (curr = head; curr.next != null; curr = curr.next) {}
            node.next = null;
            curr.next = node;
        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node curr;
            for (curr = head; curr.next != null; curr = curr.next) {
                if (curr.student_id == id) {
                    return curr;
                }
            }
            if (curr.student_id == id) {
                return curr;
            }
            return new Node("Student Not Found!");
        }
    }
    
    public Node eraseNode(int id){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node curr;
            if (head.student_id == id) {
                Node temp = head;
                head = head.next;
                return temp;
            }

            for (curr = head; curr.next.next != null; curr = curr.next) {
                if (curr.next.student_id == id) {
                    Node temp = curr.next;
                    curr.next = curr.next.next;
                    return temp;
                }
            }
            if (curr.next.student_id == id) {
                Node temp = curr.next;
                curr.next = curr.next.next;
                return temp;
            }
            return new Node("Student Not Found!");
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){
        node2.next = node1.next;
        node1.next = node2;
    }
    
    public void addNodeBefore(Node node1, Node node2){
        Node curr;

        if (node1 == head) {
            node2.next = node1;
            head = node2;
            return;
        }

        for (curr = head; curr.next.next != null; curr = curr.next) {
            if (curr.next == node1) {
                node2.next = node1;
                curr.next = node2;
            } 
        }
        
        if (curr.next == node1) {
            node2.next = node1;
            curr.next = node2;
        } 

    }
    
    public boolean isEmpty(){
        if (head == null) return true;
        return false;
    }
    public void merge(SinglyLinkedList list){
        Node curr;
        for (curr = head; curr.next != null; curr = curr.next) {}
        curr.next = list.head;
    }
    
    public void printStructure(){
        Node curr;
        System.out.print(listName + ": head -> ");
        if (!isEmpty()) {
            for (curr = head; curr.next != null; curr = curr.next) {
                System.out.print("{" + curr.student_id + "} -> ");
            }
            System.out.print("{" + curr.student_id + "} -> ");
        }

        System.out.print("null\n");
    }
    
    public Node whoGotHighestGPA(){
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Double gpa_max = Double.MIN_VALUE;
            Node temp = null;

            for (Node curr = head; curr != null; curr = curr.next) {
                if (curr.gpa >= gpa_max) {
                    gpa_max = curr.gpa;
                    temp = curr;
                }
            }

            return temp;
        }
    }
}

