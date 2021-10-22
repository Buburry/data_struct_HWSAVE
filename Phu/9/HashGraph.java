import java.util.*;

@SuppressWarnings("unchecked")
// Graph implemented by Adjacency List with Hash functionality
public class HashGraph extends Graph {

    int p; // Big Prime (for PolyHash())
    int x; // Small number (for PolyHash())

    // This is complete, no need to edit
    public HashGraph(int cap, int p, int x) {
        super(cap); // Forward the parameter to Graph's constructor
        this.p = p;
        this.x = x;
    }

    // Polynomials Hash for hashing string
    public static int polyHash(String s, int p, int x) {
        int hash = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            hash = (hash * x + (int) (s.charAt(i))) % p;
        }
        return hash;
    }

    // Map the String key to the array index
    public int getListIndex(String s) {
        // Hash the String key to get initial index
        int i = polyHash(s, p, x) % cap;

        if (vertexList[i] == null) {
            return i;
        } else if (vertexList[i].strKey.equals(s)) {
            return i;
        } else {
            // Collisions resolution using Quadratic probing
            for (int k = 0; k < cap; k++) {
                i = (i + k) % cap;
                if (vertexList[i] == null) {
                    return i;
                } else if (vertexList[i].strKey.equals(s)) {
                    return i;
                }
            }
        }

        return 0;
    }


    public void addVertex(String key) {
        if (size == cap) {
            System.out.println("Vertex list is full. You need to recreate the Graph");
            return;
        }

        // Map the String key to the array index (use getListIndex())
        int i = getListIndex(key);

        // Create Vertex object and the LinkedList object
        Vertex v = new Vertex(key);
        LinkedList<Integer> connectedV = new LinkedList<>();

        // Add these objects to the corresponding arrays
        vertexList[i] = v;
        adjacencyList[i] = connectedV;
        // finally, size++;
        size++;
    }

    public void addEdge(String source, String destination) {
        // Map String's source and destination (city name) to Integer's u, v (array index)
        int u = getListIndex(source);
        int v = getListIndex(destination);

        super.addEdge(u, v);
    }

    public void BFS(Vertex s) {
        // Set all Vertex.dist to Infinity (Use Integer.MAX_VALUE to represent Infinity)
        for (Vertex v : vertexList) {
            if (v != null) v.dist = Integer.MAX_VALUE;
        }

        // Set dist of the start vertex (s) to 0
        s.dist = 0;
        // Push the start vertex to an empty queue
        Queue<Vertex> q = new LinkedList<>();
        q.add(s);

        // [*] Check if the queue is not empty
        while (!q.isEmpty()) {
            // Pop queue and get the current vertex
            Vertex u = q.remove();
            // Extract the list of all vertices that are connected to current vertex
            LinkedList<Integer> connectedV = adjacencyList[getListIndex(u.strKey)];

            // Traverse all the list check if the dist value of any vertices are still infinity or not
            for (int hashedIndices : connectedV) {
                Vertex v = vertexList[hashedIndices];
                // If yes, push that vertex into the queue
                if (v.dist == Integer.MAX_VALUE) {
                    q.add(v);
                    // increase the dist variable of that vertex by one
                    v.dist = u.dist + 1;
                    // set the prev variable of that vertex to the current vertex
                    v.prev = u;
                }
            }
        }
        // Repeat [*]
    }


    public Stack<Vertex> getShortestPathList(Vertex S, Vertex U) {
        // Create a stack
        Stack<Vertex> stack = new Stack();
        int stackSize = 0; // to use traversing the stack

        // Start from city U
        // [*] push the current city into the stack
        while (U != S) {
            stack.add(U);
            // Go back one city using U.prev
            U = U.prev;
            stackSize++;
        }
        // Repeat [*] until you reach the city S

        // include origin city (S)
        stack.push(S);
        stackSize++;

        // reverse stack
        Stack<Vertex> tmp = new Stack<>(); // temp stack
        // traverse the stack
        for(int i = 0; i < stackSize; i++) {
            // pop the stack and push the returned element to the temp stack
            tmp.push(stack.pop());
        }
        stack = tmp;

        // return the stack
        return stack;
    }

    public void printShortestPath(String s_str, String u_str) {
        // Map city names to index numbers
        int s_idx = getListIndex(s_str);
        int u_idx = getListIndex(u_str);

        // Get vertices from the vertexList
        Vertex S = vertexList[s_idx];
        Vertex U = vertexList[u_idx];

        // Run BFS() at the starting city
        BFS(S);
        // Get shortestPathList(starting city, ending city)
        Stack<Vertex> res = getShortestPathList(S, U);
        // Traverse all the stack and print the city name
        for (Vertex v : res) {
            System.out.print(v.strKey + " -> ");
        }
    }

    // This function is complete, no need to edit
    public void showVertexList() {
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i] != null)
                System.out.println("vertexList[" + i + "] contains " + vertexList[i].strKey);
            else
                System.out.println("vertexList[" + i + "] null");
        }
    }


    // This is editable test case, but no need to edit
    public static HashGraph constructGraph() {

        int p = 101111; // Big Prime (Hash key1)
        int x = 101;    // Small number (Hash key2)
        HashGraph graph = new HashGraph(32, p, x);

        String[] cities = new String[]{"Dublin", "Edinburgh", "Manchester",
                "Copenhagen", "Lisbon", "London", "Berlin", "Prague", "Madrid",
                "Paris", "Vienna", "Budapest", "Geneva", "Milan", "Zurich", "Rome"};
        for (int i = 0; i < 16; i++) {
            graph.addVertex(cities[i]);
        }

        return graph;
    }

    // This is editable test case, but no need to edit
    public static HashGraph connectEdges(HashGraph graph) {
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