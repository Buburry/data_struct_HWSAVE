public class AVLTree extends BTreePrinter {
    Node root;

    public AVLTree() {
    } // Dummy Constructor, no need to edit

    public AVLTree(Node root) {
        this.root = root;
    }

    public void singleRotateFromLeft(Node y) {
        if (y == null) return;

        Node x = y.left;
        Node w = y.parent;

        if (x != null) {
            y.left = x.right;
            if (y.left != null) {
                y.left.parent = y;
            }

            x.right = y;
            y.parent = x;

            if (w != null) {
                if (w.left == y) {
                    w.left = x;
                } else if (w.right == y) {
                    w.right = x;
                }
                x.parent = w;
            } else {
                root = x;
                x.parent = null;
            }
        }
    }

    public void singleRotateFromRight(Node y) {
        if (y == null) return;

        Node x = y.right;
        Node w = y.parent;

        if (x != null) {
            y.right = x.left;
            if (y.right != null) {
                y.right.parent = y;
            }

            x.left = y;
            y.parent = x;

            if (w != null) {
                if (w.left == y) {
                    w.left = x;
                } else if (w.right == y) {
                    w.right = x;
                }
                x.parent = w;
            } else {
                root = x;
                x.parent = null;
            }
        }
    }

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

    // Find the closest node x (imbalance node) to a node in the path to root and rebalance node x
    // Use in insertion
    private static void rebalanceClosestNodeX(AVLTree tree, Node node) {
        Node x = node;
        int balanceFactor = height(x.left) - height(x.right);

        // if node is balanced, then find the closest imbalance node
        if (Math.abs(balanceFactor) <= 1) {
            // Find node x - closest node that needs rebalancing
            do {
                x = x.parent;
                if (x == null) return; // happens when it reaches root, meaning no rebalancing is needed
                balanceFactor = height(x.left) - height(x.right); // Calculate balance factor of each passing node
            } while (Math.abs(balanceFactor) <= 1); // Loop ends when found the first imbalance node.
        }

        rebalanceAtNode(tree, x);
    }

    // Traverse up from a node to the root, check and rebalance all imbalance nodes in the entire path (in order of traversal)
    // Use in deletion
    private static void rebalanceWholePath(AVLTree tree, Node node) {
        while (node != null) {
            rebalanceAtNode(tree, node);
            node = node.parent;
        }
    }

    // Check and rebalance that node, if it is balanced, then ignore
    public static void rebalanceAtNode(AVLTree tree, Node node) {
        int balanceFactor = height(node.left) - height(node.right);

        if (balanceFactor > 1) {                                                // Use balanceFactor to check if left heavy
            int bf2 = height(node.left.left) - height(node.left.right);         // To check the direction of imbalance
            if (bf2 > 0) {                                                      // left-left
                // Fix Outer left-left and perform single rotation from left
                System.out.println("Perform SingleRotationFromLeft(Node " + node.key + ")");
                tree.singleRotateFromLeft(node);
            } else {                                                            // left-right
                // Fix Inner left-right and perform double rotation from left
                System.out.println("Perform DoubleRotationFromLeft(Node " + node.key + ")");
                tree.doubleRotateFromLeft(node);
            }
        } else if (balanceFactor < -1) {                                         // Use balanceFactor to check if right heavy
            int bf2 = height(node.right.left) - height(node.right.right);       // To check the direction of imbalance
            if (bf2 > 0) {                                                      // right-left
                // Fix Inner right-left and perform double rotation from right
                System.out.println("Perform DoubleRotationFromRight(Node " + node.key + ")");
                tree.doubleRotateFromRight(node);
            } else {                                                            // right-right
                // Fix Outer right-right and perform single rotation from right
                System.out.println("Perform SingleRotationFromRight(Node " + node.key + ")");
                tree.singleRotateFromRight(node);
            }
        }
    }

    // This function is complete, no need to edit
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(this, root, key);
        }
    }

    // Fix this function to have the rebalancing feature
    public static void insert(AVLTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        } else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
                rebalanceClosestNodeX(tree, node.left);
            } else {
                insert(tree, node.left, key);
            }
        } else {  // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
                rebalanceClosestNodeX(tree, node.right);
            } else {
                insert(tree, node.right, key);
            }
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
                delete(this, min);
            }
        } else {
            Node n = find(key);
            if (n == null) {
                System.out.println("Key not found!!!");
            } else {
                delete(this, n);
            }
        }
    }

    // Use this function to delete non-root nodes
    public static void delete(AVLTree tree, Node node) {
        // There should be 7 cases here
        if (node.left == null && node.right == null) {
            if (node.parent.left == node) node.parent.left = null;
            else if (node.parent.right == node) node.parent.right = null;
            rebalanceWholePath(tree, node);
            node.parent = null;
        } else if (node.left != null && node.right == null) {
            if (node.parent.left == node) {
                node.parent.left = node.left;
            } else if (node.parent.right == node) {
                node.parent.right = node.left;
            }
            node.left.parent = node.parent;
            rebalanceWholePath(tree, node);
            node.parent = null;
        } else if (node.left == null && node.right != null) {
            if (node.parent.left == node) {
                node.parent.left = node.right;
            } else if (node.parent.right == node) {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            rebalanceWholePath(tree, node);
            node.parent = null;
        } else {
            Node min = findMin(node.right);
            node.key = min.key;
            delete(tree, min);
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

    public static Node findMin(Node node) {
        if (node.left == null) return node;
        else return findMin(node.left);
    }

    public static Node findMax(Node node) {
        if (node == null) return null;
        else if (node.right == null) return node;
        else return findMax(node.right);
    }

    public static boolean isMergeable(Node r1, Node r2) {
        // Mergeable when all keys in r1 are less than all keys in r2.
        // Therefore, comparing findMax(r1) < findMax(r2) is enough
        if(r1 == null || r2 == null) return true;
        else return findMax(r1).key < findMax(r2).key;
    }

    public static Node AVLTreeMergeWithRoot(Node r1, Node r2, Node t) {
        if(r1 == null && r2 == null) {
            return t;
        }

        int r1Height = height(r1);
        int r2Height = height(r2);

        if(!isMergeable(r1, r2)) {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }

        if (Math.abs(r1Height - r2Height) <= 1) {
            t.left = r1;
            t.right = r2;
            if (r1 != null) r1.parent = t;
            if (r2 != null) r2.parent = t;
            return t;
        } else if (r1Height > r2Height) {
            Node r = AVLTreeMergeWithRoot(r1.right, r2, t);
            r1.right = r;
            r.parent = r1;
            AVLTree tree = new AVLTree(r1);
            rebalanceAtNode(tree, r1);
            return tree.root;
        } else if (r1Height < r2Height) {
            Node r = AVLTreeMergeWithRoot(r1, r2.left, t);
            r2.left = r;
            r.parent = r2;
            AVLTree tree = new AVLTree(r1);
            rebalanceAtNode(tree, r2);
            return tree.root;
        }

        return t;
    }

    public void merge(AVLTree tree2) {
        if (isMergeable(this.root, tree2.root)) {
            Node t = findMax(this.root);
            this.delete(t.key);
            this.root = AVLTreeMergeWithRoot(this.root, tree2.root, t);
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }
    }

    public Node[] split(int key) {
        return split(root, key); // Calling the static split
    }

    public static Node[] split(Node r, int key) {
        Node[] arr;
        if(r == null) {
            return new Node[]{null, null};
        } else if(key < r.key) {
            arr = split(r.left, key);
            if(arr[1] != null) arr[1].parent = null;
            if(r.right != null) r.right.parent = null;
            arr[1] = AVLTreeMergeWithRoot(arr[1], r.right, r);
            return arr;
        } else {
            arr = split(r.right, key);
            if(arr[0] != null) arr[0].parent = null;
            if(r.left != null) r.left.parent = null;
            arr[0] = AVLTreeMergeWithRoot(r.left, arr[0], r);
            return arr;
        }
    }

    // Use this function to check the node height
    // This function is complete, no need to edit
    public static int height(Node node) {
        if (node == null)
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right));
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