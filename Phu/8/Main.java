public class Main {

    public static void nodePriorityTest(){

        Node nodeA = new Node();
        Node nodeB = new Node();
        
        nodeA.price = 30; nodeA.timestamp = 1; nodeA.isMinHeap = true;
        nodeB.price = 20; nodeB.timestamp = 2; nodeB.isMinHeap = true;
        System.out.println("nodeA.compare(nodeB) = " + nodeA.compare(nodeB));
        System.out.println("nodeB.compare(nodeA) = " + nodeB.compare(nodeA));
        
        nodeA.price = 30; nodeA.timestamp = 1; nodeA.isMinHeap = false;
        nodeB.price = 20; nodeB.timestamp = 2; nodeB.isMinHeap = false;
        System.out.println("nodeA.compare(nodeB) = " + nodeA.compare(nodeB));
        System.out.println("nodeB.compare(nodeA) = " + nodeB.compare(nodeA));
        
        nodeA.price = 100; nodeA.timestamp = 1; nodeA.isMinHeap = true;
        nodeB.price = 100; nodeB.timestamp = 2; nodeB.isMinHeap = true;
        System.out.println("nodeA.compare(nodeB) = " + nodeA.compare(nodeB));
        System.out.println("nodeB.compare(nodeA) = " + nodeB.compare(nodeA));

    }
    public static void main(String[] args) {
        
        //nodePriorityTest();
        
        
        StockMarket market = new StockMarket(10, 10); // 10 investos, 10 stocks
        // Add 10 investors and the initial balance
        market.addInvestor("Khanitin", 10000); // ID=0
        market.addInvestor("Kritsanaphong", 10000); // ID=1
        market.addInvestor("Tewarad", 10000); // ID=2
        market.addInvestor("Chanchai", 9000); // ID=3
        market.addInvestor("Chayanon", 9000); // ID=4
        market.addInvestor("Baangkok", 9000); // ID=5
        market.addInvestor("Supak", 8000); // ID=6
        market.addInvestor("Pawaret", 8000); // ID=7
        market.addInvestor("Tanadol", 8000); // ID=8
        market.addInvestor("Tanhatai", 8000); // ID=9
        // Add 10 stocks and the initial price
        market.addStock("PTT", 344);
        market.addStock("CPALL", 60.5);
        market.addStock("SCB", 144);
        market.addStock("KBANK", 170);
        market.addStock("CPF", 27.75);
        market.addStock("TRUE", 7.15);
        market.addStock("CPN", 53);
        market.addStock("BTS", 8.35);
        market.addStock("DTAC", 35.50);
        market.addStock("LH", 8.75);
        
        // market.giveMeSomeShares();
        market.loadAssets(); // load saved assets
        market.showStockOwnership();
        
        market.portfolio("Kritsanaphong");
        market.portfolio("Khanitin");
        
        
        // To uncomment below, you need to implement Heap
//        /*
        market.submitSellOrder("Tewarad", "SCB", 5, 200);
        market.submitSellOrder("Supak", "SCB", 10, 180);
        market.quote("SCB");

        market.submitBuyOrder("Chanchai", "SCB", 6, 250);
        market.submitBuyOrder("Chayanon", "SCB", 6, 250);

        market.portfolio("Chanchai");
        market.portfolio("Chayanon");
        market.portfolio("Tewarad");
        market.portfolio("Supak");

        market.quote("KBANK");

        market.submitBuyOrder("Baangkok", "KBANK", 2, 90);
        market.quote("KBANK");

        market.submitBuyOrder("Supak", "KBANK", 1, 85);
        market.quote("KBANK");
        market.submitBuyOrder("Pawaret", "KBANK", 3, 90);
        market.quote("KBANK");
        market.submitBuyOrder("Tanadol", "KBANK", 2, 100);
        market.quote("KBANK");
        market.submitBuyOrder("Tanhatai", "KBANK", 2, 80);
        market.quote("KBANK");


        market.submitSellOrder("Chanchai", "KBANK", 2, 150);
        market.quote("KBANK");

        market.portfolio("Chanchai");
        market.submitSellOrder("Chanchai", "KBANK", 6, 85);

        market.portfolio("Chanchai");


        market.showStockOwnership();

        market.submitSellOrder("Khanitin", "PTT", 3, 350);
        market.submitSellOrder("Khanitin", "PTT", 3, 360);
        market.submitSellOrder("Khanitin", "PTT", 4, 370);
        market.submitSellOrder("Tewarad", "PTT", 5, 355);
        market.submitSellOrder("Tewarad", "PTT", 5, 365);
        market.submitSellOrder("Tewarad", "PTT", 5, 375);
        market.submitSellOrder("Chayanon", "PTT", 3, 360);
        market.submitSellOrder("Chayanon", "PTT", 3, 370);
        market.submitSellOrder("Chayanon", "PTT", 4, 380);

        market.submitBuyOrder("Kritsanaphong", "PTT", 6, 370);
        market.quote("PTT");
        market.submitBuyOrder("Chanchai", "PTT", 6, 370);
        market.submitBuyOrder("Baangkok", "PTT", 10, 370);
        market.submitBuyOrder("Supak", "PTT", 10, 370);

        market.submitBuyOrder("Pawaret", "PTT", 2, 330);
        market.submitBuyOrder("Pawaret", "PTT", 2, 300);
        market.submitBuyOrder("Pawaret", "PTT", 2, 270);

        market.submitBuyOrder("Tanadol", "PTT", 3, 330);
        market.submitBuyOrder("Tanadol", "PTT", 3, 250);
        market.submitBuyOrder("Tanadol", "PTT", 3, 200);

        market.submitSellOrder("Tanhatai", "PTT", 3, 320);
        market.submitSellOrder("Tanhatai", "PTT", 3, 240);
        market.submitSellOrder("Tanhatai", "PTT", 4, 220);
        market.submitSellOrder("Kritsanaphong", "PTT", 3, 320);
        market.submitSellOrder("Kritsanaphong", "PTT", 3, 240);
        market.submitSellOrder("Kritsanaphong", "PTT", 4, 220);

        market.quote("PTT");

        market.showStockOwnership();

        market.portfolio("Kritsanaphong");

        market.submitBuyOrder("Khanitin", "PTT", 20, 150);
        market.submitBuyOrder("Khanitin", "PTT", 20, 100);
        market.submitBuyOrder("Khanitin", "PTT", 10, 50);

        market.submitSellOrder("Baangkok", "PTT", 10, 125);
        market.submitSellOrder("Supak", "PTT", 10, 75);
        market.submitSellOrder("Pawaret", "PTT", 15, 75);
        market.submitSellOrder("Tanadol", "PTT", 15, 75);

        market.quote("PTT");

        market.portfolio("Kritsanaphong");

        

    }
    
}
