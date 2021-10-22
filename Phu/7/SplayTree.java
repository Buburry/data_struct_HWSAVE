import java.util.LinkedList;
import java.util.Queue;

public class SplayTree extends BTreePrinter {
    Node root;

    public SplayTree(Node root) {
        this.root = root;
        root.parent = null; // Clear parent of the root (Important for SplayTree)
    }

    // zig() function will move up the node x one level
    // Case 1: x == root
    // Case 2-3: x.parent == root (sig from left, zig from right)
    // Case 4-5: x.parent != root (sig from left, zig from right)
    public void zig(Node x) {
        Node y = x.parent;
        if (y == null) {
            System.out.println("Cannot perform Zig operation on the root node");
        } else if (y == root) { // If the node is a child of the root
            if (x.key < y.key) {// Zig from left
                y.left = x.right;
                if (x.right != null) x.right.parent = y;
                x.right = y;
                x.parent = y.parent;
                y.parent = x;
                root = x;
            } else if (x.key > y.key) {// Zig from right
                y.right = x.left;
                if (x.left != null) x.left.parent = y;
                x.left = y;
                x.parent = y.parent;
                y.parent = x;
                root = x;
            }
        } else {// if y != root
            Node w = x.parent.parent;
            if (x.key < y.key) {// Zig from left
                y.left = x.right;
                if (x.right != null) x.right.parent = y;
                x.right = y;
                x.parent = w;
                y.parent = x;
            } else if (x.key > y.key) {// Zig from right
                y.right = x.left;
                if (x.left != null) x.left.parent = y;
                x.left = y;
                x.parent = w;
                y.parent = x;
            }
            if (x.key < w.key) w.left = x;
            else if (x.key > w.key) w.right = x;
        }
    }

    // zigzig() function will move up node x two levels along the outer path
    // Pls call zig() to perform zigzig()
    public void zigzig(Node x) {
        zig(x.parent);
        zig(x);
    }


    // zigzag() function will move up node x two levels along the inner path
    // Pls call zig() to perform zigzag()
    public void zigzag(Node x) {
        zig(x);
        zig(x);
    }

    // This function will iteratively splay (move up) node x all the way to the root
    public void splay(Node x) {
        while (x != null && x != root) {
            if (x == root.left || x == root.right) {
                zig(x);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {//outer right-right
                zigzig(x);
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {//outer left-left
                zigzig(x);
            } else if (x == x.parent.left && x.parent == x.parent.parent.right) {// Inner left-right
                zigzag(x);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {// Inner right-left
                zigzag(x);
            }
        }
    }

    // Modify this function to have the splaying feature
    // This can be done by calling the splay() function
    // Implement this function using iterative method
    // Do not use recursion
    public Node find(int search_key, boolean withSplay) {
        Node current = root;
        while (current != null) {
            if (search_key < current.key) {
                current = current.left;
            } else if (search_key > current.key) {
                current = current.right;
            } else {
                if (withSplay) splay(current);
                return current;
            }
        }
        return null;
    }

    // Implement this function using iterative method
    // This function does not have the splaying feature
    // Do not use recursion
    public Node findMin() {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Implement this function using iterative method
    // Do not use recursion
    public Node findMax() {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // Implement this function using iterative method
    // Do not use recursion
    public void insert(int key) {
        // Implement insert() using the non-recursive version
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = root;
            while (true) {
                if (key < parent.key) {
                    if (parent.left == null) {
                        parent.left = newNode;
                        newNode.parent = parent;
                        splay(newNode);
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else if (key > parent.key) {
                    if (parent.right == null) {
                        parent.right = newNode;
                        newNode.parent = parent;
                        splay(newNode);
                        break;
                    } else {
                        parent = parent.right;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void delete(int key) {
        find(key, true); // Find and Splay the node to delete
        SplayTree rightSubtree = new SplayTree(); // Temp tree as right subtree
        rightSubtree.root = root.right;
        Node minNode = rightSubtree.findMin();
        Node newRoot = new Node(minNode.key); // new root from min of right subtree
        minNode.parent.left = null; // delete min node to proceed makine it a new root

        // Connecting new root, and abandoning old root(node to delete)
        newRoot.left = root.left;
        root.left.parent = newRoot;
        newRoot.right = root.right;
        root.right.parent = newRoot;
        newRoot.parent = null;
        root = newRoot;

    }


    // This is another version of height() called iterative method to find BST height
    // This function is complete, no need to edit
    @SuppressWarnings("unchecked")
    public int height() {
        if (root == null)
            return -1;
        Queue<Node> q = new LinkedList();
        q.add(root);
        int height = -1;
        while (true) {
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;
            while (nodeCount > 0) {
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

    public SplayTree() {
    } // Dummy Constructor, no need to edit

    // This is the editable testcase (test#4)
    // No need to edit
    public static void test4() {
        BSTree2 tree1 = new BSTree2();
        long start = System.currentTimeMillis();
        int N = 40000;
        for (int i = 0; i < N; i++)
            tree1.insert(i);
        System.out.println("Time for sequentially inserting " + N + " objects into BST = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree1.find((int) (Math.random() * N));

        System.out.println("Time for finding " + N + " different objects in BST= " + (System.currentTimeMillis() - start) + " msec");
        SplayTree tree2 = new SplayTree();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.insert(i);

        System.out.println("Time for sequentially inserting " + N + " objects into SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.find((int) (Math.random() * N), true);
        System.out.println("Time for finding " + N + " different objects in SplayTree = " + (System.currentTimeMillis() - start) + " msec");

        System.out.println("Which one is faster: BSTree or SplayTree?");
    }
}
