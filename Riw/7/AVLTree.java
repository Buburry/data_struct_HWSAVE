public class AVLTree extends BTreePrinter{
    Node root;

    public void singleRotateFromLeft(Node y) {
        if (y == null || y.left == null) return;
        
        Node w = y.parent != null ? y.parent : null;
        Node x = y.left;
        Node t = x.right != null ? x.right : null;
        
        // check if there are parent
        if (w != null) {
            // move x to y position
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
            // same with above with need to deal with parent
            x.parent = null;
            if (y == root) root = x;
            x.right = y;
            y.parent = x;
            y.left = t;
            if (t != null) t.parent = y;
        }
    }

    // same procedure with singleRotateFromLeft just change direcion
    public void singleRotateFromRight(Node y) { 
        if (y == null || y.right == null) return;
        
        Node w = y.parent != null ? y.parent : null;
        Node x = y.right;
        Node t = x.left != null ? x.left : null;
        
        if (w != null) {
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
        else {
            x.parent = null;
            if (y == root) root = x;
            x.left = y;
            y.parent = x;
            y.right = t;
            if (t != null) t.parent = y;
        }
    }

    public void doubleRotateFromLeft(Node y) {
        if (y == null || y.left == null) return;
        
        Node w = y.parent != null ? y.parent : null;
        Node x = y.left;
        Node z = x.right != null ? x.right : null;
        if (z == null) return;
        Node t1 = z.left != null ? z.left : null;
        Node t2 = z.right != null ? z.right : null;
        
        // check if there are parent
        if (w != null) {
            // move z to y position
            if (w.key > z.key) {
                w.left = z;
                z.parent = w;
            }
            else {
                w.right = z;
                z.parent = w;
            }
            // move y and x to be a child of z
            z.right = y;
            y.parent = z;
            z.left = x;
            x.parent = z;
            
            // change inner branch parent
            x.right = t1;
            if (t1 != null) t1.parent = x;
            y.left = t2;
            if (t2 != null) t2.parent = y;  
        } else {
            // same with above but no need to deal with parent
            z.parent = null;
            if (y == root) root = z;
            z.right = y;
            y.parent = z;
            z.left = x;
            x.parent = z;
            
            x.right = t1;
            if (t1 != null) t1.parent = x;
            
            y.left = t2;
            if (t2 != null) t2.parent = y;
        }
    }

    // same procedure with doubleRotateFromLeft just change direcion
    public void doubleRotateFromRight(Node y) {
        if (y == null || y.right == null) return;
        
        Node w = y.parent != null ? y.parent : null;
        Node x = y.right;
        Node z = x.left != null ? x.left : null;
        if (z == null) return;
        Node t1 = z.left != null ? z.left : null;
        Node t2 = z.right != null ? z.right : null;
        
        if (w != null) {
            if (w.key > z.key) {
                w.left = z;
                z.parent = w;
            }
            else {
                w.right = z;
                z.parent = w;
            }
            
            z.left = y;
            y.parent = z;
            z.right = x;
            x.parent = z;
            
            x.left = t2;
            if (t2 != null) t2.parent = x;
            
            y.right = t1;
            if (t1 != null) t1.parent = y;  
        } else {
            z.parent = null;
            if (y == root) root = z;
            z.left = y;
            y.parent = z;
            z.right = x;
            x.parent = z;
            
            x.left = t2;
            if (t2 != null) t2.parent = x;
            
            y.right = t1;
            if (t1 != null) t1.parent = y;
        }
    }

    public void rebalance(AVLTree tree, Node node){
        if (node == null) return;    
        int diff = height(node.left) - height(node.right);
        // check if height of the left and right subtrees is greater than 2
        if (diff > 1 || diff < 1) {                          
            // left subtree is heavier than right subtree
            if (diff > 1) {                      
                // if outer subtree is heavier than inner subtree then singleRotate
                if (height(node.left.left) > height(node.left.right)) {                  
                    singleRotateFromLeft(node);
                    System.out.println("Perform SingleRotationFromLeft(Node " + node.key +")"); 
                }else{ // else doubleRotate
                    doubleRotateFromLeft(node);
                    System.out.println("Perform DoubleRotationFromLeft(Node " + node.key +")");   
                }
            // right subtree is heavier than left subtree
            }else if (diff < -1){
                // if outer subtree is heavier than inner subtree then singleRotate
                if (height(node.right.right) > height(node.right.left)){
                    singleRotateFromRight(node);
                    System.out.println("Perform SingleRotationFromRight(Node " + node.key +")");
                }else{ // else doubleRotate
                    doubleRotateFromRight(node);
                    System.out.println("Perform DoubleRotationFromRight(Node " + node.key +")");
                }
            }
        }
    }
    
    public void insert(AVLTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        }else if (key < node.key) { // go left
            if (node.left == null) { 
                // lf left child is empty insert it as left child and rebalance untill we reach root
                node.left = new Node(key);
                node.left.parent = node;

                Node curr = node.left;
                while (curr != null) {
                    rebalance(tree, curr);
                    curr = curr.parent;
                }

            }else {
                // recursively go left 
                insert(tree, node.left, key);
            }
        }else{ // go right and same with above but change direction
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
    
                Node curr =  node.right;
                while (curr != null) {
                    rebalance(tree, curr);
                    curr = curr.parent;
                }
                
            }else {
                insert(tree, node.right, key);
            }
        }
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(this, root, key);
        }
    }

    public void delete(int key) {
        // check if we're deleting root node
        if (key == root.key) {
            // find min of right subtree
            Node minRight = root.right != null ? findMin(root.right) : null;
            if (minRight != null) {
                // replace root with min of right subtree and delete min of right of subtree
                root.key = minRight.key;
                delete(this, minRight);
            }
            else {
                // if can't find min of right sub use left child instead
                if (root.left != null) {
                    root.key = root.left.key;
                    delete(this, root.left);
                } 
                // if can't find any child root is null
                else root = null;
            }
        }
        else {
            // not root case
            delete(this, find(key));
        }
        
    }
    
    public void delete(AVLTree tree, Node node){
        Node curr = node.parent;
        // check if node have no child remove node
        if (node.left == null && node.right == null) {
            if (curr.key <= node.key) curr.right = null;
            else curr.left = null;
        }
        // check if node has right child remove node and change child parent
        else if (node.left == null && node.right != null) {
            if (curr.key <= node.key) {
                node.right.parent = curr;
                curr.right = node.right;
            }
            else {
                node.right.parent = curr;
                curr.left = node.right;
            }
        }
        // check if node has left child remove node and change child parent
        else if (node.left != null && node.right == null) {
            if (curr.key <= node.key) {
                node.left.parent = curr;
                curr.right = node.left;
            }
            else {
                node.left.parent = curr;
                curr.left = node.left;
            }
        }
        else {
            // if node has both child
            // replace node with min of right subtree and delete min of right of subtree
            Node minRight = findMin(node.right);
            node.key = minRight.key;
            curr = null; // don't need to rebalance on this call
            delete(this, minRight);
        }
        // rebalance untill we reach root
        while (curr != null) {
            rebalance(tree, curr);
            curr = curr.parent;
        }
    }

    public Node find(int search_key){
        return find(root, search_key);
    }
    
    public static Node find(Node node, int search_key){
        if (node.key == search_key) return node;
        if (node.left != null && search_key < node.key) return find(node.left, search_key);
        else if (node.right != null && search_key >= node.key) return find(node.right, search_key);
        
        return null;
    }

    // This function is complete, no need to edit
    public static Node findMin(Node node){
        if (node == null) return null;
         
        if (node.left == null) return node;
        else return findMin(node.left);
    }

    public static Node findMax(Node node){
        // just go to rightmost node
        if (node == null) return null;
    
        if (node.right == null) return node;
        else return findMax(node.right);
    }

    public static boolean isMergeable(Node r1, Node r2){
        // merging with with null node
        if (r1 != null && r2 == null || r1 == null && r2 != null) return true;
        // check if max of r1 is less than min of r2
        return findMax(r1).key < findMin(r2).key;
    }

    public Node mergeWithRoot(Node r1, Node r2, Node t){ // t can't be null
        if (r1 == null && r2 == null) {
            return t;
        }
        if (isMergeable(r1, r2)) {
            int diff = height(r1) - height(r2);
            // height diff < 1 just attach r1, r2 to t
            if (Math.abs(diff) <= 1) {
                t.left = r1;
                if (r1 != null) r1.parent = t;
                t.right = r2;
                if (r2 != null) r2.parent = t;
                return t;
            }
            // if threre're subtree that heavier than the other one.
            // go down that subtree untill we can merge with subtree of same height
            else if (diff > 1) {
                Node newR = mergeWithRoot(r1.right, r2, t);
                r1.right = newR;
                newR.parent = r1;
                rebalance(this, r1);
                return r1;
            }
            else if (diff < -1) {
                Node newR = mergeWithRoot(r1, r2.left, t);
                r2.left = newR;
                newR.parent = r2;
                rebalance(this, r2);
                return r2;
            }
            
            return t;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
    
    public void merge(AVLTree tree2){
        if (isMergeable(this.root, tree2.root)){
            // find max node in T1 and delete it
            Node t = findMax(this.root);
            delete(t.key);
            // merge T1 wtih T2 with node t as root
            mergeWithRoot(this.root, tree2.root, t);
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }
    }

    public Node[] split(int key){
        return split(root, key);
    }
    
    public Node[] split(Node r, int key){ 
        Node[] arr = new Node[2];
        // if root is return null
        if (r == null) {
            arr[0] = null;
            arr[1] = null;
            return arr;
        }
        else if (key < r.key) {
            // split left subtree
            arr = split(r.left, key);
            // clear all relations with parent
            Node temp = null;
            if (r.parent != null) {
                if (r.parent.key > r.key) {
                    r.parent.left = null;
                    r.parent = null;
                }
                else {
                    r.parent.right = null;
                    r.parent = null;
                }
            }
            if (r.right != null) {
                temp = r.right;
                r.right.parent = null;
                r.right = null;
            }
            if (arr[1] != null) arr[1].parent = null;
            r.parent = null;
            // merge T2 with right subtree with r as root
            Node newR = mergeWithRoot(arr[1], temp, r);
            while (newR.parent != null) newR = newR.parent;  
            arr[1] = newR;
            return arr;
        }
        else if (key >= r.key) {
            // split right subtree
            arr = split(r.right, key);
            // clear all relations with parent
            Node temp = null;
            if (r.parent != null) {
                if (r.parent.key > r.key) {
                    r.parent.left = null;
                    r.parent = null;
                }
                else {
                    r.parent.right = null;
                    r.parent = null;
                }
            }
            if (r.left != null) {
                temp = r.left;
                r.left.parent = null;
                r.left = null;
            }
            if (arr[0] != null) arr[0].parent = null;
            r.parent = null;
            // merge T1 with left subtree with r as root
            Node newR = mergeWithRoot(temp, arr[0], r);
            while (newR.parent != null) newR = newR.parent;  
            arr[0] = newR;
            return arr;
        }
    
        return arr;
    }

    public int height() {
        return height(root);
    }
    // Use this function to check the node height
    // This function is complete, no need to edit
    public static int height(Node node){
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
    
    public AVLTree() {} // Dummy Constructor, no need to edit
}