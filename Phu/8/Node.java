// This node contains information about an investor (investorID) who submits an order (sell order = minHeap, buy order = maxHeap)
// at a certain time (timestamp) and a certain price (price)
public class Node {

    double price;
    int investorID;
    int amount;
    int timestamp; // Heap.push() is the only function that modify this variable
    boolean isMinHeap;

    public Node(double price, int investorID, int amount, boolean isMinHeap) {
        this.price = price;
        this.investorID = investorID;
        this.amount = amount;
        this.isMinHeap = isMinHeap;
    }

    // This function will return true if Priority(thisNode) > Priority(rightNode)
    // minHeap: the lower the price, the higher the priority
    // maxHeap: the lower the price, the lower the priority
    // If same price, the smaller the timestamp is, the higher the priority will be
    public boolean compare(Node rightNode) {
        if (this.price == rightNode.price) {
            return this.timestamp < rightNode.timestamp;
        } else {
            if (isMinHeap) {
                return this.price < rightNode.price;
            } else {
                return this.price > rightNode.price;
            }
        }
    }

    public Node() {
    } // Dummy constructor, no need to edit
}
