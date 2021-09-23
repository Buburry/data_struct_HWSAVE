import java.util.LinkedList;
import java.util.Queue;

public class Main {
   
    public static BSTree generateTree1(){
        int[] keyList = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTree tree = new BSTree();
        for (int i=0; i<keyList.length; i++)
            if (i==0){
                tree.root = new Node(keyList[i]);
            }else{
                Main.insert(tree.root, keyList[i]);
            }
        return tree;
    }

    public static AVLTree generateTree2(){
        AVLTree tree = new AVLTree();
        int[] keyList = {51, 30, 69, 18, 42, 63, 87, 12, 24, 36, 45, 57, 66, 81, 93, 15, 21, 27, 33, 39, 48, 54, 60, 75, 84, 90, 96, 72, 78};
        for (int i=0; i<keyList.length; i++){
            if (i==0){
                tree.root = new Node(keyList[i]);
            }else{
                Main.insert(tree.root, keyList[i]);
            }
        }
        return tree;
    }
    
    public static AVLTree generateTree3(){
        AVLTree tree = new AVLTree();
        int[] keyList = {21, 8, 34, 3, 16, 26, 42, 2, 5, 11, 18, 23, 31, 37, 47,
            1, 4, 6, 9, 13, 17, 19, 22, 24, 28, 33, 35, 40, 45, 52, 7, 10, 12,
            14, 20, 25, 27, 30, 32, 36, 38, 41, 43, 46, 49, 53, 15, 29, 39, 44,
            48, 51, 54, 50};
        for (int i=0; i<keyList.length; i++){
            if (i==0){
                tree.root = new Node(keyList[i]);
            }else{
                Main.insert(tree.root, keyList[i]);
            }
        }
        return tree;
    }
    
    public static AVLTree generateTree4(){
        AVLTree tree = new AVLTree();
        int[] keyList = {10, 5, 13, 3, 8, 11, 15, 1, 4, 7, 9, 12, 14, 2, 6};
        for (int i=0; i<keyList.length; i++){
            if (i==0){
                tree.root = new Node(keyList[i]);
            }else{
                Main.insert(tree.root, keyList[i]);
            }
        }
        return tree;
    }
    
    public static void insert(Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key!!!");
        } else if (key < node.key) {
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            } else {
                insert(node.left, key);
            }
        } else if (node.right == null) {
            node.right = new Node(key);
            node.right.parent = node;
        } else {
            insert(node.right, key);
        }
    }
    
    public static void BFT(AVLTree tree){
        Queue<Node> q = new LinkedList<>();
        q.add(tree.root);
        System.out.print("BFT [ ");
        while (!q.isEmpty()){
            Node node = q.remove();
            System.out.print(node.key + " ");
            if (node.left!=null)
                q.add(node.left);
            if (node.right!=null)
                q.add(node.right);
        }
        System.out.println("]");   
    }
    
    public static void main(String[] args) {
        
        /*
        BSTree tree;

        tree = Main.generateTree1();
        tree.printTree();
        System.out.println("---- Test1 singleRotateFromLeft at Lv 3 ----");
        tree.singleRotateFromLeft(tree.find(6));
        tree.singleRotateFromLeft(tree.find(2));
        tree.printTree();
        */

        /*
        tree = Main.generateTree1();
        System.out.println("---- Test2 singleRotateFromRight at Lv 3 ----");
        tree.singleRotateFromRight(tree.find(10));
        tree.singleRotateFromRight(tree.find(14));
        tree.printTree();
*/
        /*
        tree = Main.generateTree1();
        System.out.println("---- Test3 singleRotateFromLeft at Lv 2 ----");
        tree.singleRotateFromLeft(tree.find(4));
        tree.printTree();
        */
        /*
        tree = Main.generateTree1();
        tree.printTree();
        System.out.println("---- Test4 singleRotateFromRight at Lv 2 ----");
        tree.singleRotateFromRight(tree.find(12));
        tree.printTree();
        System.out.println("---- Test5 singleRotateFromLeft at Lv 1 ----");
        tree.singleRotateFromLeft(tree.find(8));
        tree.printTree();
        System.out.println("---- Test6 singleRotateFromRight at Lv 1 ----");
        tree.singleRotateFromRight(tree.find(4));
        tree.singleRotateFromLeft(tree.find(14));
        tree.printTree();
*/
        /*
        tree = Main.generateTree1();
        tree.printTree();
        System.out.println("---- Test7 doubleRotateFromRight at Lv 1 ----");
        tree.doubleRotateFromRight(tree.find(8));
        tree.printTree();
*/
        /*
        tree = Main.generateTree1();
        System.out.println("---- Test8 doubleRotateFromLeft at Lv 1 ----");
        tree.doubleRotateFromLeft(tree.find(8));
        tree.printTree();
*/
        /*
        tree = Main.generateTree1();
        tree.printTree();
        System.out.println("---- Test9.1 doubleRotateFromLeft at Lv 2 ----");
        tree.doubleRotateFromLeft(tree.find(4));
        tree.printTree();
        System.out.println("---- Test9.2 doubleRotateFromLeft at Lv 2 ----");
        tree.doubleRotateFromLeft(tree.find(12));
        tree.printTree();
        */
        
        /*
        tree = Main.generateTree1();
        tree.printTree();
        System.out.println("---- Test10.1 doubleRotateFromRight at Lv 2 ----");
        tree.doubleRotateFromRight(tree.find(4));
        tree.printTree();
        System.out.println("---- Test10.2 doubleRotateFromRight at Lv 2 ----");
        tree.doubleRotateFromRight(tree.find(12));
        tree.printTree();
*/
        /*
        AVLTree tree = Main.generateTree2();
        Main.BFT(tree);
        tree.insert(73); // must perform SingleRotationFromLeft(Node 81)
        Main.BFT(tree);
        tree.insert(77); // must perform DoubleRotationFromLeft(Node 87)
        Main.BFT(tree);
        tree.insert(76); // must perform SingleRotationFromLeft(Node 78)
        tree.insert(80); // must perform DoubleRotationFromRight(Node 69)
        tree.insert(74); // must perform SingleRotationFromRight(Node 72)
        tree.insert(64); // do nothing
        tree.insert(55); // must perform SingleRotationFromLeft(Node 69)
        tree.insert(70); // must perform DoubleRotationFromRight(Node 51)
        Main.BFT(tree);
        */
        /*
        AVLTree tree = generateTree3();
        Main.BFT(tree);
        tree.delete(35);
        Main.BFT(tree);
        */
        /*
        Node n = tree.find(41);
        System.out.println(AVLTree.height(n.left) - AVLTree.height(n.right));
*/
        /*
        BSTree tree1 = new BSTree();
        int[] keyList = {3, 2, 5, 1, 4, 8, 7, 6};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        BSTree tree2 = new BSTree();
        keyList = new int[]{9, 11, 10};
        for (int i=0; i<keyList.length; i++)
            tree2.insert(keyList[i]);
        tree1.printTree();
        tree2.printTree();
        tree1.merge(tree2);
        tree1.printTree();
*/
        /*
        BSTree tree1 = new BSTree();
        int[] keyList = {3, 2, 5, 1, 4, 8, 7, 6};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        tree1.printTree();
        
        BSTree tree2 = new BSTree();
        keyList = new int[]{9, 11, 10};
        for (int i=0; i<keyList.length; i++)
            tree2.insert(keyList[i]);
        tree2.printTree();
        
        //tree2.merge(tree1);
        //tree2.printTree();
        tree1.merge(tree2);
        tree1.printTree();
*/
        AVLTree tree1 = new AVLTree();
        int[] keyList = {4, 2, 6, 1, 3, 5, 8, 7, 9};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        System.out.println("Tree 1");
        tree1.printTree();
        AVLTree tree2 = new AVLTree();
        keyList = new int[]{12, 11};
        for (int i=0; i<keyList.length; i++)
            tree2.insert(keyList[i]);
        System.out.println("Tree 2");
        tree2.printTree();
        System.out.println("Try to merge tree1 to (the right of) tree2...");
        tree1.merge(tree2);
        tree1.printTree();
        /*
        System.out.println("Try to merge tree2 to (the right of) tree1...");
        tree1.merge(tree2);
        tree1.printTree();
        */
        /*
        AVLTree tree1 = new AVLTree();
        int[] keyList = {1, 15, 3, 13, 5, 11, 9, 10, 8, 4, 12, 7, 2, 6, 14};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        tree1.printTree(); // Do not print if there is no rebalance()

        for (int i=0; i<keyList.length; i++) {
            tree1.delete(keyList[i]);
            tree1.printTree();
        }
        tree1.printTree();
        */
        // int[] keyList = {10, 5, 13, 3, 8, 11, 15, 1, 4, 7, 9, 12, 14, 2, 6};
        /*
        AVLTree tree = Main.generateTree4();
        tree.printTree();
        Node[] rootArray = tree.split(3);
        for (int i=0; i<rootArray.length; i++) {
            System.out.println("Print Tree " + (i+1) + " after splitting with 10");
            tree.root = rootArray[i];
            tree.printTree();
        }
        */
        /*
        System.out.println("Perform AVL Tree spliting...");
        Node[] rootArray = tree1.split(3);
        for (int i=0; i<rootArray.length; i++) {
            System.out.println("Print Tree " + (i+1) + " after splitting with 3");
            tree1.root = rootArray[i];
            tree1.printTree();
        }
        */
        /*
        SplayTree tree = new SplayTree();
        for (int i = 0; i < 4; i++) 
            tree.insert(i+1);       // This should be splaying insert 
        tree.printTree();
        
        Node node;
        
        System.out.println("Zig Node (1)");
        node = tree.find(1, false); // false means do not splay the node
        tree.zig(node);
        tree.printTree();
        
        System.out.println("Zig Node (3)");
        node = tree.find(3, false); // false means do not splay the node
        tree.zig(node); // This should change the tree structure
        tree.printTree();
        
        System.out.println("Zig Node (2)");
        node = tree.find(2, false); // false means do not splay the node
        tree.zig(node); // This should change the tree structure
        tree.printTree();
        
        System.out.println("Zig Node (4)");
        node = tree.find(4, false); // false means do not splay the node
        tree.zig(node); // This should change the tree structure
        tree.printTree();
        */
        /*
        SplayTree tree = new SplayTree();
        int[] keyList = {5, 7, 2, 3, 1, 6, 8};
        for (int i = 0; i < keyList.length; i++) 
            tree.insert(keyList[i]);    // This should be splaying insert
        tree.printTree();
        
        Node node1, node2, node3, node5, node7;
        // This find(key, false) means do not splay the node
        node1 = tree.find(1, false);
        node2 = tree.find(2, false);
        node3 = tree.find(3, false);
        node5 = tree.find(5, false);
        node7 = tree.find(7, false);
        
        System.out.println("ZigZig Node (1)");
        tree.zigzig(node1);
        tree.printTree();
        
        System.out.println("ZigZag Node (5)");
        tree.zigzag(node5);
        tree.printTree();
        
        System.out.println("ZigZag Node (5)");
        tree.zigzag(node5);
        tree.printTree();
        
        System.out.println("ZigZag Node (7)");
        tree.zigzag(node7);
        tree.printTree();
        
        System.out.println("ZigZag Node (2)");
        tree.zigzag(node2);
        tree.printTree();
        
        System.out.println("ZigZag Node (3)");
        tree.zigzag(node3);
        tree.printTree();
        
        System.out.println("ZigZig Node (7)");
        tree.zigzig(node7);
        tree.printTree();
        */
        /*
        SplayTree tree = new SplayTree();
        for (int i=10; i>=1; i--)
            tree.insert(i);
        System.out.println("Initial tree height = " + tree.height());
        int[]keyList = {1, 3, 9, 5, 7, 2};
        for (int i=0; i<keyList.length; i++)
            tree.find(keyList[i], true); // Perform several splaying find()
        tree.printTree();
        System.out.println("Tree height after multiple accesses = " + tree.height());
        for (int i=1; i <= 10; i++) {
            tree.delete(i); 
        }
        tree.printTree();
        System.out.println("Tree height after one deletion = " + tree.height());
        */
        /*
        BSTree2 tree1 = new BSTree2();
        long start = System.currentTimeMillis();
        int N = 40000;
        for (int i = 0; i < N; i++)
            tree1.insert(i);
        System.out.println("Time for sequentially inserting " + N + " objects into BST = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree1.find((int) (Math.random() * N ));

        System.out.println("Time for finding " + N + " different objects in BST= " + (System.currentTimeMillis() - start) + " msec");
        //System.out.println("BSTree height = " + tree1.height());

        SplayTree tree2 = new SplayTree();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.insert(i);

        System.out.println("Time for sequentially inserting " + N + " objects into SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.find((int)(Math.random() * N ), true);
        System.out.println("Time for finding " + N + " different objects in SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        System.out.println("SplayTree height = " + tree2.height());
        
        AVLTree tree3 = new AVLTree();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree3.insert(i);

        System.out.println("Time for sequentially inserting " + N + " objects into AVLTree = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree3.find((int)(Math.random() * N ));
        System.out.println("Time for finding " + N + " different objects in AVLTree = " + (System.currentTimeMillis() - start) + " msec");
        System.out.println("AVLTree height = " + tree3.height());
        */
    }   

}
