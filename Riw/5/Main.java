import hw5.*;

public class Main {
    
    public static void main(String[] args) {
        Queue q = new Queue(4);
        q.enqueue(new Node(5));
        q.enqueue(new Node(6));
        q.enqueue(new Node(7));
        q.enqueue(new Node(8));
        System.out.println(q.dequeue().data);
        q.enqueue(new Node(13));
        System.out.println(q.dequeue().data);
        q.enqueue(new Node(14));
        q.printCircularIndices();
        q.printQueue();
    }
}
