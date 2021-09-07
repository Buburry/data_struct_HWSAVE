package hw6;
// This Tree needs to inherit BTreePrinter
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
        if (node.key == search_key) return node;
        if (node.left != null && search_key <= node.key) return find(node.left, search_key);
        else if (node.right != null && search_key >= node.key) return find(node.right, search_key);
        
        return null;
    }
    
    public Node findMin(){
        return findMin(root);
    }
    
    public static Node findMin(Node node){
        if (node == null) return null;
        
        if (node.left == null) return node;
        else return findMin(node.left);
    
    }
    
    public Node findMax(){
        return findMax(root);
    }
    
    public static Node findMax(Node node){
        if (node == null) return null;
        
        if (node.right == null) return node;
        else return findMax(node.right);
        
    }
    
    public Node findClosestLeaf(int search_key){
        return findClosestLeaf(root, search_key);
    }
    
    public static Node findClosestLeaf(Node node, int search_key){
        
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
        // Please use while loop to implement this function
        // Try not to use recursion
        
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
    
    // Implement this function using the findClosestLeaf technique
    // Do not implement the recursive function
    public void insert(int key) {
        // Implement insert() using the non-recursive version
        // This function should call findClosestLeaf()
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
        if (node == null) return;
        printPostOrderDFT(node.left);
        printPostOrderDFT(node.right);
        System.out.print(node.key + " ");
    }

    public static int height(Node node){
        // Use recursion to implement this function
        if (node == null) return -1;
        else return Math.max(height(node.left), height(node.right)) + 1;
    }
    
    public static int size(Node node){
        // Use recursion to implement this function
        // size = #children + 1(itself)
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }
    
    public static int depth(Node root, Node node){
        // Use recursion to implement this function
        // Similar to height() but start from node, go up to root
        // depth = length(path{node->root})
        if (node == root) return 0;
        return 1 + depth(root, node.parent);
    }
    
    public int height(){ // Tree height
        // Hint: call the static function
        return height(root);
    }
    
    public int size(){ // Tree size
        // Hint: call the static function
        return size(root);
    }
    
    public int depth(){ // Tree depth
        // Hint: call the static function
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
        //this function should call other functions
        
        if (node.right != null) return leftDescendant(node.right);
        else return rightAncestor(node);
    }
    
    public static Node leftDescendant(Node node){// Case 1 (findMin)
        if (node.left == null) return node;
        else return leftDescendant(node.left);
    }
    
    public static Node rightAncestor(Node node) {// Case 1 (first right parent)
        if (node.parent == null) return null;

        if (node.key < node.parent.key) return node.parent;
        else return rightAncestor(node.parent);
    }
    
    public List rangeSearch(int x, int y){
        // This function utilizes findCloest() and findNext()
        // Use list.append(node) to add node to the list
        List nodeList = new List(100); 
        Node n = findClosest(x);
        
        while (n != null && n.key <= y) {
            if (n.key >= x) nodeList.append(n); 
            n = findNext(n);
        }
        
        return nodeList;
    }
 
    // This function is for deleting the root node
    // If the node is not the root, please call the recursive version
    public void delete(int key) {
        // There should be 6 cases here
        // Non-root nodes should be forwarded to the static function
        Node node = find(key);
        
        if (root == null) System.out.println("Empty Tree!!!");
        else if (node == null) System.out.println("Key not found!!!");
        else if (node == root) {
            if (root.left == null && root.right == null) {
                root = null;
            }
            else if (root.left != null && root.right == null) {
                root.left.parent = null;
                root = root.left;
            }
            else {
                Node n = findMin(root.right);
                root.key = n.key;
                root.right = null;
            }
        }
        else {
            delete(node);
        }
        
    }
    
    // Use this function to delete non-root nodes
    public static void delete(Node node){
        if (node == null) return;
        
        if (node.left == null && node.right == null) {
            if (node.key < node.parent.key) {
                node.parent.left = null;
            }
            else {
                node.parent.right = null;
            }
            
            return;
        } 
        else if (node.left != null && node.right == null) {
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
        else if (node.right != null && node.left == null) {
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
        else {
            Node n = findMin(node.right);
            node.key = n.key;
            delete(n);
        }
    }
}
