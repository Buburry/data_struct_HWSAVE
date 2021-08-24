package com.DataStructure.Tree;

public class Main {
    public static Node constructTree1() {
        Node root = new Node(3);
        root.left = new Node(7);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.left.right.left = new Node(1);
        root.left.right.right = new Node(8);

        root.right = new Node(5);
        root.right.right = new Node(9);
        root.right.right.left = new Node(4);

        return root;
    }

    public static Node constructTree2(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);
        root.left.right.right.right = new Node(10);

        root.right = new Node(3);
        root.right.right = new Node(6);
        root.right.right.left = new Node(9);

        return root;
    }
    
    public static void main(String[] args) {
        Node tree = constructTree1();
        tree.printTree();
        tree.printBFT();
        tree.printDFT();
//        Node tree = constructTree2();
//        tree.printTree();
//        Stack s = new Stack(4);
//        s.push(new Node(5));
//        s.push(new Node(6));
//        s.push(new Node(7));
//        s.push(new Node(8));
//        s.push(new Node(9));
//        System.out.println(s.pop().data);
//        System.out.println(s.pop().data);
//        System.out.println(s.pop().data);
//        s.printStack();
//        Queue q = new Queue(4);
//        q.enqueue(new Node(5));
//        q.enqueue(new Node(6));
//        q.enqueue(new Node(7));
//        q.enqueue(new Node(8));
//        q.enqueue(new Node(9));
//        System.out.println(q.dequeue().data);
//        System.out.println(q.dequeue().data);
//        System.out.println(q.dequeue().data);
//        q.printQueue();
    }
}
