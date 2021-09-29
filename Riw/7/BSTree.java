public class BSTree extends BTreePrinter{
    Node root;
    
    public Node find(int search_key){
        return find(root, search_key);
    }

    public static Node find(Node node, int search_key){
        /* we check if we already find the node we want,
        else go left if search_key < this node key and
        go right if search_key >= this node key. */

        if (node.key == search_key) return node;
        if (node.left != null && search_key < node.key) return find(node.left, search_key);
        else if (node.right != null && search_key >= node.key) return find(node.right, search_key);
        
        return null;
    }

    public static Node findMin(Node node){
        // just go to leftmost node
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

    public void insert(int key) {
        if (root == null) root = new Node(key);
        else insert(root, key);
    }

    public static void insert(Node node, int key) {
        if (key < node.key) {
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            }
            else insert(node.left, key);
        }
        else {
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
            }
            else insert(node.right, key);
        }
    }
    
    public void delete(int key) {
        // find node with this key
        Node node = find(key);
        
        if (root == null) System.out.println("Empty Tree!!!");
        else if (node == null) System.out.println("Key not found!!!");
        else if (node == root) { // root node case
            if (root.left == null && root.right == null) {
                root = null;
            }
            else if (root.left != null && root.right == null) {
                root.left.parent = null;
                root = root.left;
            }
            else {
                // find min of right sub-tree and replace root with that node remove that node
                Node n = findMin(root.right);
                root.key = n.key;
                delete(n);
            }
        }
        else { // not root node case
            delete(node);
        }
        
    }
    
    // Use this function to delete non-root nodes
    public static void delete(Node node){
        if (node == null) return;
        
        // leaf node case 
        if (node.left == null && node.right == null) {
            if (node.key < node.parent.key) {
                node.parent.left = null;
            }
            else {
                node.parent.right = null;
            }
            
            return;
        } 
        else if (node.left != null && node.right == null) { // right sub-tree is empty case
            if (node.left.key < node.parent.key) {
                node.left.parent = node.parent;
                node.parent.left = node.left;
            }
            else {
                node.left.parent = node.parent;
                node.parent.right = node.left;
            }
            return;
        }
        else if (node.right != null && node.left == null) { // left sub-tree is empty case
            if (node.right.key < node.parent.key) {
                node.right.parent = node.parent;
                node.parent.left = node.right;
            }
            else {
                node.right.parent = node.parent;
                node.parent.right = node.right;
            }
            return;
        }
        else { // full node case
            // find min of right sub-tree and replace node with it's key and recursively delete it
            Node n = findMin(node.right);
            node.key = n.key;
            delete(n);
        }
    }

    
    public static boolean isMergeable(Node r1, Node r2){
        // merging with with null node
        if (r1 != null && r2 == null || r1 == null && r2 != null) return true;
        // check if max of r1 is less than min of r2
        return findMax(r1).key < findMin(r2).key;
    }

    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        if (isMergeable(r1, r2)) {
            // attach r1 and r2 to t as new root
            t.left = r1;
            r1.parent = t;
            t.right = r2;
            r2.parent = t;
            return t;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
    
    public void merge(BSTree tree2){
        if (isMergeable(this.root, tree2.root)){
            Node t = findMax(this.root);
            delete(t.key);
            this.root = mergeWithRoot(this.root, tree2.root, t);
        }else{
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