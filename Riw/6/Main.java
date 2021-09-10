import hw6.*;

public class Main {

    public static Tree constructTree1(){ // No parent
        Node root = new Node(5);
        root.left = new Node(3);
        root.left.left = new Node(1);
        root.left.left.right = new Node(2);
        root.right = new Node(7);
        root.right.right = new Node(9);
        root.right.right.left = new Node(8);
        root.right.right.right = new Node(10);
        return new Tree(root);
    }
    
    public static Tree constructTree2(){  // No parent
        Node root = new Node(50);
        root.left = new Node(30);
        root.left.left = new Node(10);
        root.left.left.right = new Node(20);
        root.right = new Node(70);
        root.right.right = new Node(90);
        root.right.right.left = new Node(80);
        root.right.right.right = new Node(99);
        return new Tree(root);
    }
    
    public static Tree constructTree3(){ // With parent
        Tree tree = new Tree();
        int[] keyList = {5, 3, 1, 2, 7, 9, 10, 8};
        for (int i=0; i<keyList.length; i++)
            Main.insert(tree, tree.root, keyList[i]);// This function is hidden from students
        return tree;
    }
    
    public static Tree constructTree4(){
        Tree tree = new Tree();
        int[] keyList = {5, 3, 1, 7, 9, 10, 8};
        for (int i=0; i<keyList.length; i++)
            Main.insert(tree, tree.root, keyList[i]);// This function is hidden from students
        return tree;
    }

    
    public static Tree constructTree5(){
        Tree tree = new Tree();
        int[] keyList = {5, 2, 3, 9, 1, 10, 8, 7};
        for (int i=0; i<keyList.length; i++)
            Main.insert(tree, tree.root, keyList[i]);// This function is hidden from students
        return tree;
    }

    public static Tree constructTree6(){
        Tree tree = new Tree();
        int[] keyList = {6, 7, 9, 5, 3, 9, 10, 8, 1};
        for (int i=0; i<keyList.length; i++)
            Main.insert(tree, tree.root, keyList[i]);// This function is hidden from students
        return tree;
    }
    
    public static Tree constructTree7(){
        Tree tree = new Tree();
        int[] keyList = {5,3,7};
        for (int i=0; i<keyList.length; i++)
            Main.insert(tree, tree.root, keyList[i]);// This function is hidden from students
        return tree;
    }
    
    public static Tree constructTree8(){
        Tree tree = new Tree();
        int[] keyList = {5,3,7,1,6,9,2,8,10};
        for (int i=0; i<keyList.length; i++)
            Main.insert(tree, tree.root, keyList[i]);// This function is hidden from students
        return tree;
    }
    
    public static Node find(Node node, int search_key){
        // This function is hidden from students
        return null;
    }
    
    public static void insert(Tree t, Node current, int search_key){ 
        // This function is hidden from students
    }
    
    
    public static void main(String[] args) {
        Tree tree = Main.constructTree1();
        tree.printTree();

        int height = tree.depth();
        System.out.println("Tree Size = "+tree.size());
        System.out.println("Tree Depth = "+ height);
        System.out.println("Tree Height = "+tree.height());
    }
}
