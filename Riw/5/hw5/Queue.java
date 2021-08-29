package hw5;

public class Queue {
    Node[] arr; // circular array
    int capacity;
    int front;
    int back;
    int size;
    
    // initialize an array
    public Queue(int cap){
        capacity = cap;
        arr = new Node[cap];
        size = 0;
        // set front index to 0 and back index to -1
        front = size;
        back = front - 1;
    }
    
    public void enqueue(Node node){
        if (!isFull()) {
            // set back to next empty index and put node in it 
            back = (back + 1) % capacity; // modulus capacity so index can go back to 0 when reach capacity
            arr[back] = node;
            size++;
        }
        else {
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){
        if (!isEmpty()){
            // save temp to item at front index and return it
            Node temp = arr[front];
            front = (front + 1) % capacity; // move front to next index so index can go back to 0 when reach capacity
            size--;

            return temp;
        }else{
            System.out.println("Queue Underflow!!!");
            return null;
        }
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public boolean isFull(){
        return size == capacity;
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");
            
            // loop from front to back index and print node
            for (int i = front; i != back; i = (i+1) % capacity) {
                System.out.print(arr[i].data + " ");
            }
            System.out.print(arr[back].data + " ");

            System.out.println("[Back]");
        } else{
            System.out.println("Empty Queue!!!");
        }
    }
}
