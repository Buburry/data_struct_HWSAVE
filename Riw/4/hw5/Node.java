package hw5;

public class Node extends BTreePrinter { // Fix this line

    Node left;
    Node right;
    public int data;

    public Node(int data) {
        this.data = data;
    }

    public void printTree() {
        super.printTree(this);
    }

    public void printBFT() {
        Queue q = new Queue(50);
        System.out.print("BFT node sequence [ ");
        
        Node l = left;
        Node r = right;
        while (l != null || r != null) {
            if (l != null) {
                q.enqueue(l);
                l = l.left;
            }
            if (r != null) {
                q.enqueue(r);
                r = r.right;
            }
        }
        
        q.printQueue();
        
        System.out.println("]");
    }

    public void printDFT() { // PreOrder
        Stack s = new Stack(50);
        System.out.print("DFT node sequence [ ");
        // Do something
        System.out.println("]");
    }
}
