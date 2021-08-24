package com.DataStructure.HW3;

public class Node {
    public int student_id;
    public String name;
    public double gpa;
    
    Node next;
    Node previous;
    
    // Constructor 1
    public Node(int id, String name, double gpa){
        student_id = id;
        this.name = name;
        this.gpa = gpa;
    }
    // Constructor 2
    public Node(String name){
        this.name = name;
    }
    // Constructor 3 (dummy)
    public Node(){
        // You can leave this function blank
    }
    
    public void printIDName(){
        System.out.println("StudentID: " + student_id + " , Name: " + name);
    }

}
