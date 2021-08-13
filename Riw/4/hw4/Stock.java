package hw4;

public class Stock {
    private List list;
    private int totalShares;
    
    public Stock(String costBasis){
        switch (costBasis) {
            case "FIFO":
                list = new Queue();
                break;
            case "LIFO":
                list = new Stack();
                break;
            default:
                System.out.println("Invalid cost basis. Choose FIFO or LIFO");
                break;
        }
    }
    
    public void buy(int boughtShares, double boughtPrice){
        list.push(new Node(boughtShares, boughtPrice));
        totalShares += boughtShares;
    }
    
    public void sell(int soldShares, double soldPrice){
        if (soldShares <= totalShares){
            double realizedGain = 0.0;
            double unrealizedGain = 0.0;

            int shr = soldShares;
            while (shr > 0 && list.top() != null) {
                Node record = list.top();
                
                if (shr < record.shares) {
                    record.shares -= shr;
                    realizedGain += (soldPrice - record.price) * shr; 
                    shr -= shr;
                }
                else {
                    realizedGain += (soldPrice - record.price) * record.shares; 
                    shr -= record.shares;
                    list.pop();
                }
            }

            for (Node curr = list.top(); curr != null; curr = curr.next) {
                unrealizedGain += (soldPrice - curr.price) * curr.shares;
            }
            
            
            totalShares -= soldShares;
            System.out.println("Realized P/L = " + realizedGain + " Unrealized P/L = " + unrealizedGain);
        }else{
            System.out.println("Sell command rejected");
        }
    }
    
    public void showList(){
        Node currentNode = list.top();
        System.out.print("head -> ");
        while (currentNode!=null){
            System.out.print("[" + currentNode.shares + "@" + currentNode.price + "B] -> ");
            currentNode = currentNode.next;
        }
        System.out.println("tail");
    }
}
