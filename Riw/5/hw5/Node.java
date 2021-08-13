package hw5;

public class Node extends BTreePrinter { // Fix this line

    // public for testing only
    public Node left;
    public Node right;
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
        
        q.enqueue(this);
        while(q.size != 0) {
            Node t = q.dequeue();
            System.out.print(t.data + " ");
            
            if (t.left != null) {
                q.enqueue(t.left);
            }
            if (t.right != null) {
                q.enqueue(t.right);
            }
        }
        
        System.out.println("]");
    }

    public void printDFT() { // PreOrder
        Stack s = new Stack(50);
        System.out.print("DFT node sequence [ ");
        
        s.push(this);
        while (s.size != 0) {
            Node t = s.pop();
            System.out.print(t.data + " ");
            
            if (t.right != null) {
                 s.push(t.right);
            }
            if (t.left != null) {
                s.push(t.left);
            }
        }
        System.out.println("]");
    }
}
