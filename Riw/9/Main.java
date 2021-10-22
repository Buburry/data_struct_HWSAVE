public class Main {

    public static void test1(){

        int p = 110001;     // Big Prime    (Hash key1)
        int x = 1001;       // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = {"a", "b", "c", "A", "B", "BA", "BBA", "aaa", "aaaaa", "0","1","11","111", "abcdABCD01234" };
        for (int i=0; i<names.length; i++)
            System.out.println("HashCode of '" + names[i] + "' is "+HashTable.polyHash(names[i], p, x));
        
        for (int i=0; i<names.length; i++){
            table.addString(names[i]);
            System.out.println("Index of '" + names[i] + "' is "+table.getIndex(names[i]));
        }
    }
    
    public static void test2(){
        int p = 110001;     // Big Prime    (Hash key1)
        int x = 101;       // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = { "Abraham","Andrew","Benjamin","Claudia","Gabriel","Esther","Martha","Rebecca","Moses","Timothy"};
        for (int i=0; i<names.length; i++)
            System.out.println("HashCode of " + names[i] + " is "+HashTable.polyHash(names[i], p, x));
        
        for (int i=0; i<names.length; i++){
            table.addString(names[i]);
            System.out.println("Index of " + names[i] + " is "+table.getIndex(names[i]));
        }
    }


    
    
    public static void main(String[] args) {
        HashGraph graph = HashGraph.constructGraph(); // See details in the code
        graph = HashGraph.connectEdges(graph); // See details in the code
        graph.printShortestPath("London", "Budapest");

        /*
        Graph graph = new Graph(32); 
        for (int i=0; i<16; i++)
            graph.addVertex(i);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 6);
        graph.addEdge(4, 8);
        graph.addEdge(5, 9);
        graph.addEdge(6, 7);
        graph.addEdge(6, 10);
        graph.addEdge(6, 9);
        graph.addEdge(7, 14);
        graph.addEdge(8, 9);
        graph.addEdge(8, 13);
        graph.addEdge(8, 12);
        graph.addEdge(10, 14);
        graph.addEdge(11, 15);
        graph.addEdge(13, 14);
        graph.addEdge(14, 15);
        */
        //graph.showAdjacentVertices(0);
        
        /*
        graph.showAdjacentVertices(1);
        graph.showAdjacentVertices(5);
        graph.showAdjacentVertices(14);
        graph.showAdjacentVertices(11);
        
        graph.DFS();
        */
        
        /*
        Graph graph = new Graph(32); 
        for (int i=0; i<16; i++)
            graph.addVertex(i);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 6);
        graph.addEdge(5, 9);
        graph.addEdge(6, 9);
        graph.addEdge(7, 14);
        graph.addEdge(8, 13);
        graph.addEdge(8, 12);
        graph.addEdge(10, 14);
        graph.addEdge(11, 15);
        graph.addEdge(14, 15);
        

        graph.DFS();
        System.out.println("Number of connected components = " + (graph.cc-1));
        
        //for (int i=0; i<16; i++)
        //    graph.showAdjacentVertices(i);
        
        */
        
        /*
        int p = 101111; // Big Prime (Hash key1)
        int x = 101;    // Small number (Hash key2)
        HashGraph graph = new HashGraph(32, p, x); 
        String[] cities = new String[]{"Dublin", "Edinburgh", "Manchester", 
            "Copenhagen", "Lisbon", "London", "Berlin", "Prague", "Madrid", 
            "Paris", "Vienna", "Budapest", "Geneva", "Milan", "Zurich", "Rome"};
        for (int i=0; i<16; i++){
            graph.addVertex(cities[i]);
        }
        
        //graph.showVertexList();
        
        graph.addEdge("Dublin", "Edinburgh");
        graph.addEdge("Dublin", "London");                
        graph.addEdge("Dublin", "Lisbon");
        graph.addEdge("Edinburgh", "Manchester");
        graph.addEdge("Manchester", "London");
        graph.addEdge("Manchester", "Copenhagen");
        graph.addEdge("Copenhagen", "Berlin");
        graph.addEdge("Lisbon", "Madrid");
        graph.addEdge("London", "Paris");
        graph.addEdge("Berlin", "Prague");
        graph.addEdge("Berlin", "Vienna");
        graph.addEdge("Berlin", "Paris");
        graph.addEdge("Prague", "Zurich");
        graph.addEdge("Madrid", "Paris");
        graph.addEdge("Madrid", "Milan");
        graph.addEdge("Madrid", "Geneva");
        graph.addEdge("Vienna", "Zurich");
        graph.addEdge("Budapest", "Rome");
        graph.addEdge("Milan", "Zurich");
        graph.addEdge("Zurich", "Rome");
        
        
        graph.showAdjacentVertices(graph.getListIndex("Paris"));
        graph.showAdjacentVertices(graph.getListIndex("Zurich"));
        graph.showAdjacentVertices(graph.getListIndex("Geneva"));
        
        
        graph.printShortestPath("London", "Budapest");
        graph.printShortestPath("Berlin", "Dublin");
        */
        
        
    }
    
}

