package hw6;

public class Tree extends BTreePrinter { 
    Node root;
      
    public Tree(Node root){
        this.root = root;
    }
    
    public Tree(){} // Dummy constructor (No need to edit)
       
    public void printTree(){
        if (root != null) super.printTree(root);
        else System.out.println("Empty tree!!!");
    }

    public static void printNode(Node node){
        if (node != null) System.out.println(node.key);
        else System.out.println("Node not found!!!");
    }
        
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
    
    public Node findMin(){
        return findMin(root);
    }
    
    public static Node findMin(Node node){
        // just go to leftmost node
        if (node == null) return null;
        
        if (node.left == null) return node;
        else return findMin(node.left);
    
    }
    
    public Node findMax(){
        return findMax(root);
    }
    
    public static Node findMax(Node node){
        // just go to rightmost node
        if (node == null) return null;
        
        if (node.right == null) return node;
        else return findMax(node.right);
        
    }
    
    public Node findClosestLeaf(int search_key){
        return findClosestLeaf(root, search_key);
    }
    
    public static Node findClosestLeaf(Node node, int search_key){
        /* This function find closest node that are not full
        if this node is leaf return it,
        if search_key < node key we check if left child is empty first and if not we go left, 
        if search_key >= node key same as above line. */

        if (node.right == null && node.left == null) return node;
        
        if (search_key < node.key) {
            if (node.left == null) return node;
            else return findClosestLeaf(node.left, search_key);
        }
        else {
            if (node.right == null) return node;
            else return findClosestLeaf(node.right, search_key);
        }
    
    }
    
    public Node findClosest(int search_key){
        /* find closest node to the search_key
        we loop through this tree and always check the difference between search_key and current node key. 
        if diff is less than min_diff we store this node and replace min_diff.
        and we continue to go down this tree untill we reach leaf node.*/
        
        Node current, closest;
        closest = current = root;
        int min_diff = Integer.MAX_VALUE;
        
        while (current != null) {
            int diff = Math.abs(search_key - current.key);
            
            if (current.key == search_key) return current;
            
            if (diff < min_diff) {
                min_diff = diff;
                closest = current;
            }

            if (search_key < current.key) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        
        return closest;
    }
    
    public void insert(int key) {
        /* if tree is empty just set root to new node else,
        we find closest node that is not full by calling findClosestLeaft()
        and we check if key < closestNotFull we insert new node to the left of closestNotFull
        else we insert new node to the right of closestNotFull */

        if (root == null) {
            root = new Node(key);
        } else {
            Node closestNotFull = findClosestLeaf(root, key);
        
            if (key < closestNotFull.key) {
                Node temp = new Node(key);
                closestNotFull.left = temp;
                temp.parent = closestNotFull;
            }
            else {
                Node temp = new Node(key);
                closestNotFull.right = temp;
                temp.parent = closestNotFull;
            }
        }
    }
    
    public void printPreOrderDFT(){
        System.out.print("PreOrder DFT node sequence [ ");
        printPreOrderDFT(root);
        System.out.println("]");
    }
    
    public static void printPreOrderDFT(Node node){
        /* 1. Visit the root.
           2. Traverse the left subtree, i.e., call Preorder(left-subtree)
           3. Traverse the right subtree, i.e., call Preorder(right-subtree) 
        */
        if (node == null) return;
        
        System.out.print(node.key + " ");
        printPreOrderDFT(node.left);
        printPreOrderDFT(node.right);
    }
    
    public void printInOrderDFT(){
        System.out.print("InOrder DFT node sequence [ ");
        printInOrderDFT(root);
        System.out.println("]");
    }
    
    public static void printInOrderDFT(Node node){
        /* 1. Traverse the left subtree, i.e., call Inorder(left-subtree)
           2. Visit the root.
           3. Traverse the right subtree, i.e., call Inorder(right-subtree)
        */

        if (node == null) return;
        printInOrderDFT(node.left);
        System.out.print(node.key + " ");
        printInOrderDFT(node.right);
        
    }
    
    public void printPostOrderDFT(){
        System.out.print("PostOrder DFT node sequence [ ");
        printPostOrderDFT(root);
        System.out.println("]");
    }
    
    public static void printPostOrderDFT(Node node){
        /* 1. Traverse the left subtree, i.e., call Postorder(left-subtree)
           2. Traverse the right subtree, i.e., call Postorder(right-subtree)
           3. Visit the root.
        */

        if (node == null) return;
        printPostOrderDFT(node.left);
        printPostOrderDFT(node.right);
        System.out.print(node.key + " ");
    }

    public static int height(Node node){
        // get the max of the max of left and right subtrees height and add 1 
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
    
    public static int size(Node node){
        // size = number of children + 1(itself)
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }
    
    public static int depth(Node root, Node node){
        // go from node to root and count path along the ways.
        if (node == root) return 0;
        return 1 + depth(root, node.parent);
    }
    
    public int height() { 
        return height(root);
    }
    
    public int size(){ 
        return size(root);
    }
    
    public int depth(){ 
        return height(root);
    }
    
    public Node findKthSmallest(int k){
        return findKthSmallest(root, k);
    }
    
    public static Node findKthSmallest(Node node, int k){
        int s = size(node.left);
        if (k == s + 1) return node;
        
        if (k < s + 1) return findKthSmallest(node.left, k);
        else return findKthSmallest(node.right, k - s - 1);
    }

    public static Node findNext(Node node){
        /* if node has right sub-tree the minimum object in that sub-tree is the next-largest object 
        If there is no right sub-tree, the next largest object (if any) 
        should be somewhere in the path from the node to the root */
        
        if (node.right != null) return leftDescendant(node.right);
        else return rightAncestor(node);
    }
    
    public static Node leftDescendant(Node node){
        // findMin
        if (node.left == null) return node;
        else return leftDescendant(node.left);
    }
    
    public static Node rightAncestor(Node node) {
        // Keep going up and check if node.key < node.parent
        if (node.parent == null) return null;

        if (node.key < node.parent.key) return node.parent;
        else return rightAncestor(node.parent);
    }
    
    public List rangeSearch(int x, int y){
        /* create list to store node and find closest node to x and append it
        and we continue to find next node and append it untill we exceed y
        */
        List nodeList = new List(100); 
        Node n = findClosest(x);
        
        while (n != null && n.key <= y) {
            if (n.key >= x) nodeList.append(n); 
            n = findNext(n);
        }
        
        return nodeList;
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
}
