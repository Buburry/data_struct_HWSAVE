package com.DataStructure.HW3;

public class DoublyLinkedList {
    Node head;
    Node tail;
    String listName;

    public DoublyLinkedList(String name){
        listName = name;
    }

    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            // Check whether the linked-list has more than 1 node
            if(tail != head) {
                // Set tail to previous one and remove the last node
                tail = tail.previous;
                tail.next = null;
            } else {
                // 1 node left, also set tail to null
                head = null;
                tail = null;
            }
        }
    }

    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            head = head.next;
            head.previous = null;
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
            return tail;
        }
    }

    public void pushFront(Node node){
        if (isEmpty()){
            head = node;
            tail = node;
        }else{
            node.next = head;
            head = node;
        }
    }

    public void pushBack(Node node) {
        if (isEmpty()) {
            head = node;
            tail = head;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
    }

    // Find and return the first found node with matching student_id
    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node n = head;
            // loop through all nodes and check student_id
            do {
                if(n.student_id == id) {
                    return n;
                }
                n = n.next;
            } while (n != null);

            return new Node("Student Not Found!");
        }
    }

    // Find and remove the first found node with matching student_id
    // and then return that node
    public Node eraseNode(int id){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node n = head;

            // loop through the list to find node to erased
            do {
                if(n.student_id == id) {
                    if(n == head) {
                        popFront();
                    } else if(n == tail) {
                        popBack();
                    } else {
                        // Erase node in between
                        n.previous.next = n.next;
                        n.next.previous = n.previous;
                    }
                    return n;
                }
                n = n.next;
            } while (n != null);

            return new Node("Student Not Found!");
        }
    }

    // Insert node2 after node1
    public void addNodeAfter(Node node1, Node node2){
        if(node1 == tail) {
            pushBack(node2);
            return;
        }

        node2.next = node1.next;
        node1.next = node2;
        node2.previous = node1;
        node2.next.previous = node2;
    }

    // Insert node2 in front of node1
    public void addNodeBefore(Node node1, Node node2){
        if(node1 == head) {
            pushFront(node2);
            return;
        }

        node2.previous = node1.previous;
        node1.previous.next = node2;
        node1.previous = node2;
        node2.next = node1;
    }

    public boolean isEmpty(){
        return head == null;
    }

    // Append all nodes from another list to this list
    public void merge(DoublyLinkedList list){
        list.head.previous = tail;
        tail.next = list.head; // Link this list to appended list
        tail = list.tail;
    }

    public void printStructure(){
        Node current = head;
        System.out.print(listName + ": head <-> ");
        while(current != null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.next;
        }
        System.out.print("tail\n");
    }

    // This may be useful for you for implementing printStructure()
    public void printStructureBackward(){
        Node current=tail;
        System.out.print(listName + ": tail <-> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.previous;
        }
        System.out.println("head");
    }

    // Find and return closest node to head with the highest gpa
    public Node whoGotHighestGPA(){
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node topStudent = head;
            Node current = head;
            double maxGpa = head.gpa;

            // loop through all nodes to find max gpa
            while (current != null) {
                if(maxGpa <= current.gpa) {
                    maxGpa = current.gpa;
                    topStudent = current;
                }
                current = current.next;
            }

            return topStudent;
        }
    }
}
