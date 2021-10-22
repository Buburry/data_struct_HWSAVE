public class BSTree2 extends BTreePrinter {
    Node root;

    // Implement this function using iterative method
    // Do not use recursion
    public Node find(int search_key) {
        Node current = root;
        while (current != null) {
            if (search_key < current.key) {
                current = current.left;
            } else if (search_key > current.key) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    // Implement this function using iterative method
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
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else if (key > parent.key) {
                    if (parent.right == null) {
                        parent.right = newNode;
                        newNode.parent = parent;
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

    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
}