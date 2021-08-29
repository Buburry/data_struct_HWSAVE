package com.DataStructure.main;

public class DynamicArray {
    private int[] arr;
    private int size = 0;
    private int capacity;

    public DynamicArray(int capacity) {
        arr = new int[capacity];
        this.capacity = capacity;
    }

    public int get(int index) {
        if (index > size - 1 || index < 0) {
            System.out.println("ERROR");
            return 0;
        }
        return arr[index];
    }

    //    public void insert(int index, int value) {
    //        if(index > size - 1) {
    //            System.out.println("ERROR");
    //            return;
    //        }
    //
    //        if (size >= capacity) {
    //            resizeArray(capacity * 2);
    //        }
    //
    //        for (int i = 0; i < size - index; i++) {
    //            arr[size - i] = arr[size - i - 1];
    //        }
    //        arr[index] = value;
    //        size++;
    //    }

    public void set(int index, int value) {
        if (index > size - 1 || index < 0) {
            System.out.println("ERROR");
            return;
        }
        arr[index] = value;
    }

    public void remove(int index) {
        if (index > size - 1 || index < 0) {
            System.out.println("ERROR");
            return;
        }

        if (size <= capacity / 2) {
            //Shrink array cap
            resizeArray(capacity / 2);
        }

        for (int i = 0; i < size - index - 1; i++) {
            arr[index + i] = arr[index + i + 1];
        }
        size--;
    }

    public int popBack() {
        if(size == 0) {
            System.out.println("ERROR");
            return 0;
        }

        int retrieved = arr[size - 1];
        remove(size - 1);
        return retrieved;
    }

    public void pushBack(int value) {
        if (size >= capacity) {
            resizeArray(capacity * 2);
        }
        arr[size] = value;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void printStructure() {
        String arrStr = "[ ]";
        if (!isEmpty()) {
            StringBuilder arrStrBuilder = new StringBuilder("[ ");
            for (int i = 0; i < size; i++) {
                arrStrBuilder.append(arr[i]);
                if (i < size - 1) {
                    arrStrBuilder.append(", ");
                }
            }
            arrStrBuilder.append(" ]");
            arrStr = arrStrBuilder.toString();
        }
        System.out.println("Size = " + size + ", Cap = " + capacity + ", arr = " + arrStr);
    }

    private void resizeArray(int newCap) {
        capacity = newCap;
        int[] temp = new int[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }   
}
