import java.util.LinkedList;
import java.util.Queue;

public class SplayTree extends BTreePrinter{
    Node root;
    
    public SplayTree(Node root){
        this.root = root;
        root.parent = null; // Clear parent of the root (Important for SplayTree)
    }

    // zig() function will move up the node x one level
    // Case 1: x == root
    // Case 2-3: x.parent == root (zig from left, zig from right)
    // Case 4-5: x.parent != root (zig from left, zig from right)
    public void zig(Node x){
        Node y = x.parent;
        if (y == null){
            System.out.println("Cannot perform Zig operation on the root node");
        }
        else if (y == root){ // If the node is a child of the root
            if (x.key < y.key){
                Node t = x.right != null ? x.right : null;
                // move x to y postion
                root = x;
                root.right = y;
                root.parent = null;
                y.parent = root;
                // change inner branch parent
                y.left = t;
                if (t != null) t.parent = y;
            }
            else {
                // same with above just change direction
                Node t = x.left != null ? x.left : null;
                root = x;
                root.left = y;
                root.parent = null;
                y.parent = root;
                
                y.right = t;
                if (t != null) t.parent = y;
            }
        }
        else if (y != root) {
            if (x.key < y.key) {
                Node w = y.parent != null ? y.parent : null;
                Node t = x.right != null ? x.right : null;
                
                // move x to y postion
                if (w.key > x.key) {
                    w.left = x;
                    x.parent = w;
                }
                else {
                    w.right = x;
                    x.parent = w;
                }
                // move y to be a child of x
                x.right = y;
                y.parent = x;
                // change inner branch parent
                y.left = t;
                if (t != null) t.parent = y;
                
            }
            else {
                // same with above just change direction
                Node w = y.parent != null ? y.parent : null;
                Node t = x.left != null ? x.left : null;
                
                if (w.key > x.key) {
                    w.left = x;
                    x.parent = w;
                }
                else {
                    w.right = x;
                    x.parent = w;
                }
                x.left = y;
                y.parent = x;
                y.right = t;
                if (t != null) t.parent = y;
            }
        }
    }
    
    // zigzig() function will move up node x two levels along the outer path
    // Pls call zig() to perform zigzig()
    public void zigzig(Node x){
        zig(x.parent);
        zig(x);
    }
    
    
    // zigzag() function will move up node x two levels along the inner path
    // Pls call zig() to perform zigzag()
    public void zigzag(Node x){
        zig(x);
        zig(x);
    }
    
    // This function will interatively splay (move up) node x all the way to the root
    public void splay(Node x){
        while (x != null && x != root){
            Node y = x.parent;
            // y is root just zig x
            if (y == root){
                zig(x);
                break;
            }
            else {
                Node w = y.parent;   
                // outer path case
                if (w.left != null && w.left.left == x || w.right != null && w.right.right == x) {
                    zigzig(x);
                }
                // inner path case
                else if (w.left != null && w.left.right == x || w.right != null && w.right.left == x) {
                    zigzag(x);
                }
            }
        }  
    }
    
    public void insert(int key) {
        if (root == null) root = new Node(key);
        else {
            // loop through untill we find empty for new node
            Node curr = root;
            while (curr != null) {
                if (curr.left == null && curr.right == null) break;
                if (key < curr.key) {
                    if (curr.left == null) break;
                    else curr = curr.left;
                }
                else {
                    if (curr.right == null) break;
                    else curr = curr.right;
                }
            }
            Node temp = new Node(key);
            if (key < curr.key) {
                curr.left = temp;
                temp.parent = curr;
            }
            else {
                curr.right = temp;
                temp.parent = curr;
            }
            // splay new node to root
            splay(temp);
        }
    }
    
    // Have the splaying feature (if withSplay is true)
    public Node find(int search_key, boolean withSplay){
        Node curr = root;
        // loop through tree and compare key till we find the node
        while (curr != null) {
            if (curr.key == search_key) {
                if (withSplay) splay(curr); // splay when we found the node
                return curr;
            }
            else if (search_key < curr.key) curr = curr.left;
            else if (search_key > curr.key) curr = curr.right;
        }
        return curr;
    }
    
    public void delete(int key) {
        if (root.left == null && root.right == null && key == root.key) {
            root = null;
            return;
        }
        // splay the node that we're deleting to root 
        splay(find(key, false));
        Node leftSub = root.left ;
        Node rightSub = root.right;
        // find min of right subtree
        Node curr = rightSub;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        // splay min of right subtree to root
        splay(curr);
        // reattach left subtree to new root
        root.left = leftSub;
        if (leftSub != null) leftSub.parent = root;
    }
    
    // This function does not have the splaying feature
    public Node findMin() {
        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
    
    // This is another version of height() called iterative method to find BST height
    // This function is complete, no need to edit
    @SuppressWarnings("unchecked")
    public int height(){
        if (root==null)
            return -1;
        Queue<Node> q = new LinkedList();
        q.add(root);
        int height = -1;
        while (true){
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;
            while (nodeCount > 0){
                Node newnode = q.remove();
                if (newnode.left != null)
                    q.add(newnode.left);
                if (newnode.right != null)
                    q.add(newnode.right);
                nodeCount--;
            }
        }
    }
    
    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
    
    public SplayTree() {} // Dummy Constructor, no need to edit
    
    // This is the editable testcase (test#4)
    // No need to edit
    public static void test4(){
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
        SplayTree tree2 = new SplayTree();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.insert(i);

        System.out.println("Time for sequentially inserting " + N + " objects into SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.find((int)(Math.random() * N ), true);
        System.out.println("Time for finding " + N + " different objects in SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        
        System.out.println("Which one is faster: BSTree or SplayTree?");
    }
}