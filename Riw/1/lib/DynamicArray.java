package lib;

public class DynamicArray {
    private int[] arr;
    private int capacity;
    private int size; // Last element can be indexed at size-1
    
    public DynamicArray(int cap){ // Class Constructor
        arr = new int[cap];
        this.capacity = cap;
        size = 0;
    }
    
    public void pushBack(int data){
        // FIXED THIS
        if (size == capacity) {
            this.capacity = capacity * 2;
            int[] temp = new int[capacity];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        arr[size] = data;
        size++;
    }
    public int popBack(){
        // FIXED THIS
        
        if (size == 0) {
            System.out.println("ERROR");
            return 0;
        }

        int out = arr[size];
        arr[size] = 0;
        size--;

        return out;
    }

    public int get(int i) {
        // FIXED THIS
        if (i >= size || i < 0) {
            System.out.println("ERROR");
            return 0;
        }

        return arr[i];
    }
    public void set(int i, int value){
        // FIXED THIS
        if (i >= size || i < 0) {
            System.out.println("ERROR");
        }
        arr[i] = value;
    }
    
    public void remove(int i){
        // FIXED THIS
        if (i >= size || i < 0) {
            System.out.println("ERROR");
            return ;
        }
        for (int j = i; j < size - 1; j++) {
            arr[j] = arr[j + 1];
        }
        arr[size - 1] = 0;
        size--;
    }
    
    public boolean isEmpty(){
        // FIXED THIS
        if (size == 0) return true;
        return false;
    }
    
    public int getSize(){
        // FIXED THIS
        return size;
    }
    
    public void printStructure(){
        // FIXED THIS
        if (size > 0) {
            System.out.print("Size = " + size + ", Cap = " + capacity + ", arr = [ ");
            for (int i = 0; i < size; i++) {    
                if (i != size - 1) System.out.print(arr[i] + ", ");
                else System.out.print(arr[i] + " ]\n");
            }   
        }
        else System.out.println("Size = " + size + ", Cap = " + capacity + ", arr = [ ]");
        
    }
}

