public class Heap {

    int capacity;
    Node[] arr;
    int size;

    boolean isMinHeap;  // true if minHeap, false if maxHeap

    int timer;    // For each push, the timer will increase by 1

    public Heap(boolean isMinHeap, int cap) {
        // Initialize the heap here
        // Binary heap using...
        // the concept of "Complete binary tree based on the array implementation"
        // The 0 index will not be used, The index starts from 1
        this.isMinHeap = isMinHeap;
        this.capacity = cap + 1;
        size = 1;
        arr = new Node[capacity];
    }

    public Node top() {
        return arr[1];
    }

    public void push(Node node) {
        // Increase timer each time you push something into the Queue
        timer++;
        node.timestamp = timer; // Stamp the time number to the node

        // Increase heap capacity, if full
        if (size >= capacity) {
            resize(capacity * 2);
        }

        // Push the new node at the end of the array (via back pointer)
        arr[size] = node;
        size++; // Increase back pointer

        // Sift (percolate) it up the heap
        int childIdx = size - 1; // Index of recently pushed node
        int parentIdx = childIdx / 2; // Index of its parent

        if (size > 2) { // if there is more than 1 node, then sift
            // Repeatedly comparing priority between parent and its child and swapping
            // Starting from recently pushed node (last)
            // Ends when root is reached, or recently pushed node is at the right level(priority)
            while (childIdx >= 2 && parentIdx >= 1) {
                // If child priority is higher than parent's
                if (arr[childIdx].compare(arr[parentIdx])) {
                    swap(parentIdx, childIdx); // Swap parent and its child
                    // Set new parent index and its child index to be compared in the next round
                    childIdx = parentIdx;
                    parentIdx = childIdx / 2;
                } else {
                    break;
                }
            }
        }
    }

    public Node pop() {
        // Mark the root for return
        Node res = arr[1];
        // Move the last node to the root
        arr[1] = arr[size - 1];
        arr[size - 1] = null;
        size--; // Decrease back pointer

        // Shrink heap capacity as necessary
        if (size <= (capacity) / 2) {
            resize((capacity + 1) / 2);
        }

        // Sift (percolate) it down the heap
        int parentIdx = 1; // Index of root
        int childIdx = 2 * parentIdx; // Index of its left child

        // Repeatedly comparing priority between parent and its child and swapping
        // Starting from root
        // Ends when the last node is reached, or it is at the right level(priority)
        while (childIdx < size) {
            // Find the child with the most priority
            int mostPriorityChild = childIdx;
            if(childIdx + 1 < size) { // Compare with right child, if there is one
                mostPriorityChild = arr[childIdx].compare(arr[childIdx + 1]) ? childIdx : childIdx + 1;
            }

            if (arr[mostPriorityChild].compare(arr[parentIdx])) { // Compare parent with the child with most priority
                swap(parentIdx, mostPriorityChild); // Swap
                // Set new parent index to be compared in the next round
                parentIdx = mostPriorityChild;
            } else {
                break;
            }
            // Set new child index to be compared in the next round
            childIdx = 2 * parentIdx;
        }

        return res;
    }

    // This function is complete, no need to edit
    public void swap(int index1, int index2) {
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    // Resize heap
    private void resize(int newCap) {
        capacity = newCap;
        Node[] temp = new Node[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }
}
