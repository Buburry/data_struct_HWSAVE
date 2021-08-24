package com.DataStructure.HW3;

public class SinglyLinkedList {
    Node head;
    String listName;

    public SinglyLinkedList(String name) {
        listName = name;
    }

    public void popBack() {
        if (isEmpty()) {
            System.out.println("ERROR");
        } else {
            if (head.next == null) {
                head = null;
                return;
            }

            Node current = head;

            // loop through to get the 2nd last node of the list
            // and popBack
            while (current.next != null) {
                // break when 2nd last node is found
                if (current.next.next == null) {
                    break;
                }
                current = current.next;
            }
            current.next = null;
        }
    }


    public void popFront() {
        if (isEmpty()) {
            System.out.println("ERROR");
        } else {
            head = head.next;
        }
    }

    public Node topFront() {
        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return head;
        }
    }

    public Node topBack() {
        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            return current;
        }
    }

    public void pushFront(Node node) {
        if (isEmpty()) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }


    public void pushBack(Node node) {
        if (isEmpty()) {
            head = node;
        } else {
            Node current = head;

            // loop through to the last node of the list
            // and pushBack
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }

    // Find and return the first found node with matching student_id
    public Node findNode(int id) {
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node n = head;
            // loop through all nodes and check student_id
            do {
                if (n.student_id == id) {
                    return n;
                }
                n = n.next;
            } while (n != null);

            return new Node("Student Not Found!");
        }
    }

    // Find and remove the first found node with matching student_id
    // and then return that node
    public Node eraseNode(int id) {
        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node current = head;
            if (current.student_id == id) {
                popFront();
                return current;
            }

            // loop through the list to find node to erased
            while (current.next != null) {
                if (current.next.student_id == id) {
                    Node temp = current.next;
                    current.next = current.next.next;
                    return temp;
                }
                current = current.next;
            }
            return new Node("Student Not Found!");
        }
    }

    // Insert node2 after node1
    public void addNodeAfter(Node node1, Node node2) {
        if (node1.next == null) {
            pushBack(node2);
            return;
        }
        node2.next = node1.next;
        node1.next = node2;
    }

    // Insert node2 in front of node1
    public void addNodeBefore(Node node1, Node node2) {
        Node current = head;
        if (node1 == current) {
            pushFront(node2);
            return;
        }

        // loop through to get the node before node1
        // and insert behind that node (before node1)
        while (current.next != null) {
            if (current.next == node1) {
                node2.next = current.next;
                current.next = node2;
                break;
            }
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Append all nodes from another list to this list
    public void merge(SinglyLinkedList list) {
        pushBack(list.head);
    }

    public void printStructure() {
        Node current = head;
        System.out.print(listName + ": head -> ");
        while (current != null) {
            System.out.print("{" + current.student_id + "} -> ");
            current = current.next;
        }
        System.out.print("null\n");
    }

    // Find and return closest node to head with the highest gpa
    public Node whoGotHighestGPA() {
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node topStudent = head;
            Node current = head;
            double maxGpa = head.gpa;

            // loop through all nodes to find max gpa
            while (current != null) {
                if (maxGpa <= current.gpa) {
                    maxGpa = current.gpa;
                    topStudent = current;
                }
                current = current.next;
            }

            return topStudent;
        }
    }
}
