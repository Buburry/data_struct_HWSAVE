public class StockMarket {
    public int maxInvestor;
    public int maxStock;
    public Investor[] investors;// Max investor = 10
    public Stock[] stocks; //Max stocks = 10
    public int numInvestor=0;
    public int numStock=0;
    
    // stockOwnership[i][j] means the amount of shares the investor id i owns stock id j
    public int[][] stockOwnership;

    public Heap[] buyerQueue; // Queue for buyers
    public Heap[] sellerQueue; // Queue for sellers
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public StockMarket(int maxInvestor, int maxStock){
        investors = new Investor[maxInvestor];
        this.maxInvestor = maxInvestor;
        stocks = new Stock[maxStock];
        this.maxStock = maxStock;
        stockOwnership = new int[maxInvestor][maxStock];
        buyerQueue = new Heap[maxStock];
        sellerQueue = new Heap[maxStock];
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void addInvestor(String name, double budget){
        if (numInvestor<maxInvestor){
            investors[numInvestor] = new Investor(numInvestor, name, budget);
            numInvestor++;
        }else{
            System.out.println("ERROR! This market limit number of investors to " + maxInvestor);
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void addStock(String name, double price){
        if (numStock<maxStock){
            stocks[numStock] = new Stock(numStock, name, price);
            numStock++;
        }else{
            System.out.println("ERROR! This market limit number of stocks to " + maxStock);
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public int getInvestorID(String investorName){
        int investorID=-1;
        for (int i=0; i<numInvestor; i++){
            if (investors[i].name.equals(investorName)){
                investorID = i;
                break;
            }
        }
        if (investorID==-1){
            System.out.println("ERROR! Investor is not found");
        }
        return investorID; // return -1 means "Not found"
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public int getStockID(String stockName){
        int stockID=-1;
        for (int j=0; j<numInvestor; j++){
            if (stocks[j].name.equals(stockName)){
                stockID = j;
                break;
            }
        }
        if (stockID==-1){
            System.out.println("ERROR! Stock is not found");
        }
        return stockID; // return -1 means "Not found"
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void giveMeSomeShares(){
        for (int inv_id=0; inv_id<numInvestor; inv_id++){
            // Spend half of his money randomly buys stocks (10 shares each time)
            double initialBalance = investors[inv_id].balance;
            while (investors[inv_id].balance > initialBalance/2){
                // randomly choose stock to buy
                int stock_id = (int)(Math.random()*(numStock));
                // check if he can pay for this amount
                if (10*stocks[stock_id].currentPrice<investors[inv_id].balance){
                    // Add 10 stocks to portfolio
                    stockOwnership[inv_id][stock_id] += 10;
                    // Pay for it
                    investors[inv_id].balance -= 10*stocks[stock_id].currentPrice;
                }
            }
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void loadAssets(){
        // Use saved number of shares
        // maxInvestor = 10, maxStock = 10
        stockOwnership = new int[][]{{10,10,0,0,10,20,10,0,10,0},
                                    {20,0,0,0,20,0,0,0,0,20},
                                    {10,10,10,0,10,10,0,0,10,0},
                                    {10,0,0,10,0,0,10,10,10,0},
                                    {10,20,0,0,0,0,0,0,0,10},
                                    {10,0,0,0,10,0,10,20,10,0},
                                    {10,0,10,0,0,10,0,10,0,0},
                                    {10,10,0,0,10,0,0,0,10,10},
                                    {10,0,0,0,10,10,0,0,10,0},
                                    {10,0,0,0,0,10,20,0,10,0}
                                    };
       // Pay for the stocks
       for (int i=0; i<10; i++){
           for (int j=0; j<10; j++){
               investors[i].balance -= stockOwnership[i][j]*stocks[j].currentPrice;
           }
       }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void showStockOwnership(){
        System.out.print("\t\t\t Number of shares each investor owns \nInv.ID\t");
        for (int i=0; i<numInvestor; i++){
            System.out.print("[" + i + "]\t");
        }
        System.out.println();
        for (int i=0; i<numStock; i++){
            System.out.print("["+stocks[i].name+"]\t");
            for (int j=0; j<numInvestor; j++){
                System.out.print(stockOwnership[j][i] + "\t");
            }
            System.out.println();
        }
    }

    // Pls try to understand my code as if one day you will need to develop your own code
    public void submitBuyOrder(String investorName, String stockName, int amount, double price){
        int investorID = getInvestorID(investorName);
        int stockID = getStockID(stockName);
        
        // Buyer must have sufficient amount of money to buy stocks
        if (investors[investorID].balance < amount*price){
            System.out.println("Error! You are about to spend " +amount*price+ " baht but you have only " + investors[investorID].balance + " bath");
            return;
        }
        
        // Bid price must be greater 0
        if (price > 0) {
            if (buyerQueue[stockID] == null) {
                // true if minHeap, false if maxHeap
                // Set Maximum number of buy orders per stock to 20
                buyerQueue[stockID] = new Heap(false, 20);
            }
            buyerQueue[stockID].push(new Node(price, investorID, amount, false));
            matching('B', stockID);
        } else {
            System.out.println("Error! Bid price must be greater zero");
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void submitSellOrder(String investorName, String stockName, int amount, double price){
        int investorID = getInvestorID(investorName);
        int stockID = getStockID(stockName);
        
        // Seller must have sufficient amount of stock for selling
        if (stockOwnership[investorID][stockID]<amount){
            System.out.println("Error! You cannot sell " +amount+ " shares of "+stockName+" because you have only " + stockOwnership[investorID][stockID]+ " shares of "+stockName);
            return;
        }
        
        // Offer price must be greater 0
        if (price > 0) {
            if (sellerQueue[stockID] == null) {
                // true if minHeap, false if maxHeap
                // Set Maximum number of sell orders per stock to 20
                sellerQueue[stockID] = new Heap(true, 20);
            }
            sellerQueue[stockID].push(new Node(price, investorID, amount, true));
            //Use the offer price to update current price
            stocks[stockID].currentPrice = price;
            matching('S', stockID);
        } else {
            System.out.println("Error! Offer price must be greater zero");
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void matching(char orderCode, int stockID){
        // check the matching if there is at least one seller and one buyer
        if (sellerQueue[stockID] != null && sellerQueue[stockID].top()!=null && buyerQueue[stockID] != null && buyerQueue[stockID].top()!=null) {
            Node bestBuyer = buyerQueue[stockID].top();
            Node bestSeller = sellerQueue[stockID].top();

            // match if there is a seller and the price on the buy side >= price on the sell side
            while (bestBuyer.price >= bestSeller.price) {
                int transferAmount;
                if (bestBuyer.amount < bestSeller.amount) {
                    bestBuyer = buyerQueue[stockID].pop(); // all shares will be transfered
                    bestSeller = sellerQueue[stockID].top(); // only some shares will be transfered
                    transferAmount = bestBuyer.amount;
                    bestSeller.amount -= transferAmount; // transfer only the matched amount
                } else if (bestBuyer.amount > bestSeller.amount) {
                    bestSeller = sellerQueue[stockID].pop(); // all shares will be transfered
                    bestBuyer = buyerQueue[stockID].top(); // only some shares will be transfered
                    transferAmount = bestSeller.amount;
                    bestBuyer.amount -= transferAmount; // transfer only the matched amount
                } else {
                    bestSeller = sellerQueue[stockID].pop(); // all shares will be transfered
                    bestBuyer = buyerQueue[stockID].pop(); // all shares will be transfered
                    transferAmount = bestBuyer.amount;
                }

                System.out.println("-----------------------------------------------------------------------------------------");
                // transfer the stocks from the seller to the buyer
                System.out.println("Matched!!! Now " + transferAmount + " shares of Stock " + stocks[stockID].name + " are transferred from '" + investors[bestSeller.investorID].name + "' to '" + investors[bestBuyer.investorID].name+"'");
                stockOwnership[bestSeller.investorID][stockID] -= transferAmount;
                stockOwnership[bestBuyer.investorID][stockID] += transferAmount;
                
                // transfer the money from the buyer to the seller
                double dealPrice;
                if (orderCode=='S')// Just submit sell order then match submitted buy order
                    dealPrice = bestBuyer.price;
                else// Just submit buy order then match submitted sell order
                    dealPrice = bestSeller.price;
                System.out.println("Also, "+(dealPrice*transferAmount)+" baht is transferred from '" + investors[bestBuyer.investorID].name + "' to '" + investors[bestSeller.investorID].name+"'");
                investors[bestBuyer.investorID].balance -= dealPrice*transferAmount;
                investors[bestSeller.investorID].balance += dealPrice*transferAmount;
                System.out.println("-----------------------------------------------------------------------------------------");
                
                // Use the best offer price to update current price
                stocks[stockID].currentPrice = bestSeller.price;
                // update the best buyer and seller to the next person with highest priority
                bestSeller = sellerQueue[stockID].top();
                bestBuyer = buyerQueue[stockID].top();
                if (bestSeller==null || bestBuyer==null){
                    break;
                }
            }
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void portfolio(String investorName){
        int investorID = getInvestorID(investorName);
        if (investorID>-1){
            double totalPortValue=investors[investorID].balance;
            System.out.println("\n-------------------------------------------");
            System.out.println("     Portfolio of '" + investorName+"', ID = "+investorID);
            System.out.println("Current balance is " + totalPortValue + " baht (Buying power)");
            System.out.println("-------------------------------------------");
            System.out.println("Stock\t Amount(shares)   Position Value(baht)");
            for (int i=0; i<numStock; i++){
                // stockValue = stockPrice * numStockOwn;
                double stockValue = stocks[i].currentPrice*stockOwnership[investorID][i];
                System.out.println(stocks[i].name +"\t\t"+stockOwnership[investorID][i]+ "\t\t"+stockValue);
                totalPortValue+=stockValue;
            }
            System.out.println("-------------------------------------------");
            System.out.println("Total portfolio values = " +totalPortValue+" bath");
            System.out.println("-------------------------------------------");
        }else{
            System.out.println("Error! Investor not found");
        }
    }
    
    // Pls try to understand my code as if one day you will need to develop your own code
    public void quote(String stockName){
        int stockID = getStockID(stockName);
        double bestBidPrice = 0, bestOfferPrice = 0;
        int bidAmount = 0, offerAmount = 0;
        if (stockID > -1) {
            if (buyerQueue[stockID] != null && buyerQueue[stockID].top() != null) {
                bestBidPrice = buyerQueue[stockID].top().price;
                bidAmount = buyerQueue[stockID].top().amount;
            }
            if (sellerQueue[stockID] != null && sellerQueue[stockID].top() != null) {
                bestOfferPrice = sellerQueue[stockID].top().price;
                offerAmount = sellerQueue[stockID].top().amount;
            } else {
                bestOfferPrice = stocks[stockID].currentPrice;
            }

            System.out.println("Stock '" + stockName + "':\t[Best Bid = " + bidAmount +"x" +bestBidPrice + " baht,\t Best Offer = " + offerAmount+ "x"+bestOfferPrice + " baht]");
        } else {
            System.out.println("Error! Stock not found");
        }
    }
}
