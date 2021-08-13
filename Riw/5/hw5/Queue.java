package hw5;

public class Queue {
    Node[] arr; // circular array
    int capacity;
    int front;
    int back;
    int size;
    
    public Queue(int cap){
        capacity = cap;
        arr = new Node[cap];
        size = 0;
        front = size;
        back = front;
    }
    
    public void enqueue(Node node){
        if (!isFull()) {
            arr[back] = node;
            back = (back + 1) % capacity;
            size++;
            //System.out.println(front + " " + back + " " + size);
        }
        else {
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){
        if (!isEmpty()){
            Node temp = arr[front];
            front = (front + 1) % capacity;
            size--;

            //System.out.println(front + " " + back + " " + size);
            return temp;
        }else{
            System.out.println("Queue Underflow!!!");
            return null; // fix this (out of place)
        }
    }
    
    public boolean isEmpty(){
        return size == 0; // fix this
    }
    
    public boolean isFull(){
        return size == capacity; // fix this
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");

            int last = (back - 1) % capacity;
            
            for (int i = front; i != last; i = (i+1) % capacity) {
                System.out.print(arr[i].data + " ");
            }
            System.out.print(arr[last].data + " ");
            
            System.out.println("[Back]");
        } else{
            System.out.println("Empty Queue!!!");
        }
    }
}