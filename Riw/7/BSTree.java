public class BSTree extends BTreePrinter{
    Node root;
      
    public void singleRotateFromLeft(Node y) {
        // Do something
    }

    public void singleRotateFromRight(Node y) {
        // Do something
    }
    
    public void doubleRotateFromLeft(Node y) {
        // Do something
    }

    public void doubleRotateFromRight(Node y) {
        // Do something
    }
    
    public Node find(int search_key) {
        // Pls copy the code from the previous homework
        return null; // Fix this
    }
    
    public static Node find(Node node, int search_key) {
        // Pls copy the code from the previous homework
        return null; // Fix this
    }

    public static Node findMin(Node node){
        // Pls copy the code from the previous homework
        return null; // Fix this
    }

    public static Node findMax(Node node){
        // Pls copy the code from the previous homework
        return null; // Fix this
    }

    public void insert(int key) {
        // Pls copy the code from the previous homework
    }

    public static void insert(Node node, int key) {
        // Pls copy the code from the previous homework
    }
    
    public void delete(int key) {
        // Pls copy the code from the previous homework
    }
    
    public static void delete(Node node){
        // Pls copy the code from the previous homework
    }
    
    public static boolean isMergeable(Node r1, Node r2){
        return false;// Fix this
    }

    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        if (isMergeable(r1, r2)) {
            // Fix this
            return null;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
    
    public void merge(BSTree tree2){
        if (isMergeable(this.root, tree2.root)){
            // Do something
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