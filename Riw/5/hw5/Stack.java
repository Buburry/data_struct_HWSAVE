package hw5;

public class Stack {
    Node[] arr; // regular array
    int capacity;
    int size;

    // initialize an array
    public Stack(int cap){
        capacity = cap;
        arr = new Node[cap];
        size = 0;
    }
    
    // pushBack so top of stack is at the end of array
    public void push(Node node){
        if (!isFull()){
            arr[size] = node;
            size++;
        }else{
            System.out.println("Stack Overflow!!!");
        }
    }
    
    // popBack to get top of stack
    public Node pop(){
        if (!isEmpty()){
            Node temp = arr[size - 1];
            size--;
            return temp;
        }else{
            System.out.println("Stack Underflow!!!");
            return null;
        }
    }
    public boolean isFull(){
        return size == capacity;
    }
    public boolean isEmpty(){
        return size == 0; 
    }
    
    // print stack from bottom to top
    public void printStack(){
        if (!isEmpty()) {
            System.out.print("[Bottom] ");
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i].data + " ");
            }
            System.out.println("[Top]");
        } else {
            System.out.println("Empty Stack!!!");
        }
    }
}