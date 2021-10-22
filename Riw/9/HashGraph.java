import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class HashGraph extends Graph{
    
    int p; // Big Prime (for PolyHash())
    int x; // Small number (for PolyHash())
    
    public HashGraph(int cap, int p, int x){
        super(cap); // Forward the parameter to Graph's constructor
        this.p = p;
        this.x = x;
    }
    
    public static int polyHash(String s, int p, int x){
        int hash = 0;
        for (int i = s.length() - 1; i > -1; i--) {
            hash = (hash * x + (int)s.charAt(i)) % p;
        }
        return hash; 
    }
    
    // This function has 2 roles
    // [1] Find an empty space to push the string object
    // [2] Find location of the string
    public int getListIndex(String s){
        int index = polyHash(s, p, x);
        for (int i = 0; i < cap; i++) {
            index = (index + i) % cap;
            if (vertexList[index] == null || vertexList[index].strKey == s) return index;
        }
        return -1;
    }
    
    
    public void addVertex(String key){
        if (size==cap){
            System.out.println("Vertex list is full. You need to recreate the Graph");
            return;
        }
        // Map the String key to the array index (use getListIndex())
        int idx = getListIndex(key);
        // Pls use code from the previous problem as the starter   
        Vertex n = new Vertex(key);
        vertexList[idx] = n;
    }
    
    public void addEdge(String source, String destination){
        // Map String's source and destination (city name) to Integer's u, v (array index)
        int u = getListIndex(source);
        int v = getListIndex(destination);
        // You may call super.addEdge(u, v); for simpler implementation
        if (adjacencyList[u] == null) adjacencyList[u] = new LinkedList<Integer>();
        if (adjacencyList[v] == null) adjacencyList[v] = new LinkedList<Integer>();
        super.addEdge(u, v);
    }
    

    public void BFS(Vertex s){
        // Set all Vertex.dist to Infinity (Use Integer.MAX_VALUE to represent Infinity)
        for (Vertex v : vertexList) {
            if (v != null) {
                v.dist = Integer.MAX_VALUE;
                v.prev = null;
            }
        }

        // Set dist of the start vertex (s) to 0
        // Push the start vertex to an empty queue
        s.dist = 0;
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(s);
        
        // [*] Check if the queue is not empty
        // Pop the queue, and get the current vertex
        // Extract the list of all vertices that are connected to current vertex
        while (q.peek() != null) {
            Vertex v = q.poll();
            int idx = -1;
            for (int i = 0; i < vertexList.length; i++) {
                if (vertexList[i] == v) idx = i;
            }
            // Traverse all the list, and check if the dist value of anyone are still infinity or not
            // If yes,  push that vertex into the queue
            //          increase the dist variable of that vertex by one
            //          set the prev variable of that vertex to the current vertex
            for (Integer i : adjacencyList[idx]) {
                if (vertexList[i].dist == Integer.MAX_VALUE) {
                    q.add(vertexList[i]);
                    vertexList[i].dist = vertexList[idx].dist + 1;
                    vertexList[i].prev = vertexList[idx];
                }
            }
            // Repeat [*]
        }
        
    }

    
    public Stack<Vertex> getShortestPathList(Vertex S, Vertex U){
        // Create a stack
        Stack<Vertex> s = new Stack<Vertex>();
        // Start from city U
        // [*] push the current city into the stack
        // Go back one city using U.prev
        // Repeat [*] until you reach the city S
        while (U != S) {
            s.push(U);
            U = U.prev;
        }
        s.push(S);
        
        // return the stack
        return s;
    }
    
    public void printShortestPath(String s_str, String u_str){
        // Map city names to index numbers
        // Get vertices from the vertexList
        // Run BFS() at the starting city
        // Get shortestPartList(starting city, ending city)
        // Traverse all the stack and print the city name
        int u = getListIndex(u_str);
        int s = getListIndex(s_str);
        BFS(vertexList[s]);
        Stack<Vertex> a = getShortestPathList(vertexList[s], vertexList[u]);
        while (!a.empty()) {
            String cityName = a.pop().strKey;
            System.out.print(cityName + " -> ");
        }
        
    }
    
    // This function is complete, no need to edit
    public void showVertexList(){
        for (int i=0; i<vertexList.length; i++){
            if (vertexList[i]!=null)
                System.out.println("vertexList["+i+"] contains "+vertexList[i].strKey);
            else
                System.out.println("vertexList["+i+"] null");
        }
    }
    
    // This is editable test case, but no need to edit
    public static HashGraph constructGraph(){
        
        int p = 101111; // Big Prime (Hash key1)
        int x = 101;    // Small number (Hash key2)
        HashGraph graph = new HashGraph(32, p, x); 
        
        String[] cities = new String[]{"Dublin", "Edinburgh", "Manchester", 
            "Copenhagen", "Lisbon", "London", "Berlin", "Prague", "Madrid", 
            "Paris", "Vienna", "Budapest", "Geneva", "Milan", "Zurich", "Rome"};
        for (int i=0; i<16; i++){
            graph.addVertex(cities[i]);
        }
        
        return graph;
    }
    
    // This is editable test case, but no need to edit
    public static HashGraph connectEdges(HashGraph graph){
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
        return graph;
    }
}

