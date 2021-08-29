package hw5;

public class Node extends BTreePrinter {

    // public for testing only
    public Node left;
    public Node right;
    public int data;

    public Node(int data) {
        this.data = data;
    }

    public void printTree() {
        // run printTree method on BTreePrinter with this Node as params.
        super.printTree(this);
    }

    public void printBFT() {
        Queue q = new Queue(50);
        System.out.print("BFT node sequence [ ");
        
        // enqueue first node
        q.enqueue(this);
        while(q.size != 0) {
            // dequeue and print it
            Node t = q.dequeue();
            System.out.print(t.data + " ");
            
            // check if the dequeued node has child, if has enqueue it
            // note that we enqueue left child first for BFT printing
            if (t.left != null) {
                q.enqueue(t.left);
            }
            if (t.right != null) {
                q.enqueue(t.right);
            }
        }
        
        System.out.println("]");
    }

    public void printDFT() {
        Stack s = new Stack(50);
        System.out.print("DFT node sequence [ ");

        // push first node to stack
        s.push(this);
        while (s.size != 0) {
            // pop and print it
            Node t = s.pop();
            System.out.print(t.data + " ");
            
            // check if poped node has child, if has push it to stack
            // note that we push right child first so that right child will be pop later than left child
            // this is for Preorder (Root, Left, Right) printing
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
