public class BSTree extends BTreePrinter {
    Node root;

    public void doubleRotateFromLeft(Node y) {
        if (y == null) return;

        Node x = y.left;
        Node w = y.parent;

        if (x != null) {
            Node z = x.right;
            if (z != null) {
                x.right = z.left;
                y.left = z.right;
                if (x.right != null) x.right.parent = x;
                if (y.left != null) y.left.parent = y;

                z.left = x;
                x.parent = z;

                z.right = y;
                y.parent = z;

                if (w != null) {
                    if (w.left == y) {
                        w.left = z;
                    } else if (w.right == y) {
                        w.right = z;
                    }
                    z.parent = w;
                } else {
                    root = z;
                    z.parent = null;
                }
            }
        }
    }

    public void doubleRotateFromRight(Node y) {
        if (y == null) return;

        Node x = y.right;
        Node w = y.parent;

        if (x != null) {
            Node z = x.left;
            if (z != null) {
                x.left = z.right;
                y.right = z.left;
                if (x.left != null) x.left.parent = x;
                if (y.right != null) y.right.parent = y;

                z.right = x;
                x.parent = z;

                z.left = y;
                y.parent = z;

                if (w != null) {
                    if (w.left == y) {
                        w.left = z;
                    } else if (w.right == y) {
                        w.right = z;
                    }
                    z.parent = w;
                } else {
                    root = z;
                    z.parent = null;
                }
            }
        }
    }

    // Implement this function using the findClosestLeaf technique
    // Do not implement the recursive function
    public void insert(int key) {
        // Implement insert() using the non-recursive version
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = findClosestLeaf(key);
            if (key < parent.key) {
                parent.left = newNode;
                newNode.parent = parent;
            } else if (key > parent.key) {
                parent.right = newNode;
            }
            newNode.parent = parent;
        }
    }

    // This function is for deleting the root node
    // If the node is not the root, please call the recursive version
    public void delete(int key) {
        // There should be 6 cases here
        // Non-root nodes should be forwarded to the static function
        if (root == null) {
            System.out.println("Empty Tree!!!");
        } else if (key == root.key) {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left != null && root.right == null) {
                root = root.left;
            } else if (root.left == null && root.right != null) {
                root = root.right;
            } else {
                Node min = findMin(root.right);
                root.key = min.key;
                delete(min);
            }
        } else {
            Node n = find(key);
            if (n == null) {
                System.out.println("Key not found!!!");
            } else {
                delete(n);
            }
        }
    }

    // Use this function to delete non-root nodes
    public static void delete(Node node) {
        // There should be 7 cases here
        if (node.left == null && node.right == null) {
            if (node.parent.left == node) node.parent.left = null;
            else if (node.parent.right == node) node.parent.right = null;
            node.parent = null;
        } else if (node.left != null && node.right == null) {
            if (node.parent.left == node) {
                node.parent.left = node.left;
            } else if (node.parent.right == node) {
                node.parent.right = node.left;
            }
            node.left.parent = node.parent;
            node.parent = null;

        } else if (node.left == null && node.right != null) {
            if (node.parent.left == node) {
                node.parent.left = node.right;
            } else if (node.parent.right == node) {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = null;
        } else {
            Node min = findMin(node.right);
            node.key = min.key;
            delete(min);
        }
    }

    public Node find(int search_key) {
        return find(root, search_key); // Call the recursive version
    }

    public static Node find(Node node, int search_key) {
        if (node == null) {
            return null;
        } else if (node.key == search_key) {
            return node;
        } else if (search_key < node.key) {
            return find(node.left, search_key);
        } else {
            return find(node.right, search_key);
        }
    }

    public Node findClosestLeaf(int search_key) {
        return findClosestLeaf(root, search_key); // Call the recursive version
    }

    public static Node findClosestLeaf(Node node, int search_key) {
        if (search_key == node.key) {
            return node.parent;
        } else if (search_key < node.key) {
            if (node.left != null)
                return findClosestLeaf(node.left, search_key);
        } else if (search_key > node.key) {
            if (node.right != null)
                return findClosestLeaf(node.right, search_key);
        }

        return node;
    }

    public Node findMin() {
        return findMin(root); // Call the recursive version
    }

    public static Node findMin(Node node) {
        if (node.left == null) return node;
        else return findMin(node.left);
    }

    public Node findMax() {
        return findMax(root); // Call the recursive version
    }

    public static Node findMax(Node node) {
        if (node.right == null) return node;
        else return findMax(node.right);
    }

    public static boolean isMergeable(Node r1, Node r2) {
        // Mergeable when all keys in r1 are less than all keys in r2.
        // Therefore, comparing findMax(r1) < findMax(r2) is enough
        return findMax(r1).key < findMax(r2).key;
    }

    public static Node mergeWithRoot(Node r1, Node r2, Node t) {
        if (isMergeable(r1, r2)) {
            t.left = r1;
            t.right = r2;
            r1.parent = t;
            r2.parent = t;
            return t;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }

    public void merge(BSTree tree2) {
        if (isMergeable(this.root, tree2.root)) {
            Node t = findMax(this.root);
            this.delete(t.key);
            this.root = mergeWithRoot(this.root, tree2.root, t);
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
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