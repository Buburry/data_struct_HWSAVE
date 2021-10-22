import java.util.LinkedList;

@SuppressWarnings("unchecked") 
public class Graph {
    
    Vertex[] vertexList; // This list contain vertices
    LinkedList<Integer>[] adjacencyList; // Graph implemented by Adjacency List
    
    int cap;
    int size;
    int cc;

    public Graph(int cap){
        vertexList = new Vertex[cap];
        adjacencyList = new LinkedList[cap];
        this.cap = cap;
        size = 0;
    }
    
    public void addVertex(int key){
        if (size==cap){
            System.out.println("Vertex list is full. You need to recreate the Graph");
            return;
        }
        // Create Vertex object and the LinkedList object
        // Add these objects to the corresponding arrays
        // finally, size++;
        Vertex n = new Vertex(key);
        vertexList[size] = n;
        size++;
    }
    
    // Two way edge
    // If you make a path from u to v, you must make a path from v to u
    public void addEdge(int u, int v){
        if (vertexList[u]==null){
            System.out.println("Source node does not exist");
            return;
        } if (vertexList[v]==null){
            System.out.println("Destination node does not exist");
            return;
        }
        
        // Check if there is already an edge from u to v
        if (!isConnected(u, v)){
            // Add edge from Vertex(u) to Vertex(v)
            if (adjacencyList[u] == null) adjacencyList[u] = new LinkedList<Integer>();
            adjacencyList[u].add(v);
            // and then from Vertex(v) to Vertex(u)
            if (adjacencyList[v] == null) adjacencyList[v] = new LinkedList<Integer>();
            adjacencyList[v].add(u);
            
        }else{
            System.out.println("There is already an edge connecting vertex "+u+" and vertex "+v);
        }
    }
    
    // Check if Vertex(u) and Vertex(v) has an edge to one another
    public boolean isConnected(int u, int v){
        if (adjacencyList[u] == null) return false;
        for (Integer i : adjacencyList[u]) {
            if (i == v) return true;
        }
        return false;
    }

    // This function is complete, no need to edit
    // Please study how this function works
    // if you understand this function, you will undertand the whole homework
    public void showAdjacentVertices(int u){
        Vertex v = vertexList[u];
        System.out.print("Vertex " + v.strKey + " connected to the following vertices: ");
        
        LinkedList<Integer> list = adjacencyList[u];
        for (int vertex_index : list) {
            System.out.print(vertexList[vertex_index].strKey + ", ");
        }
        System.out.println();
    }  
    
    public void Explore(Vertex v){
        // Mark this vertex for visited
        if (!v.visited) v.visited = true;
        
        // Stamp the current cc number to this vertex (ccNum)
        v.ccNum = cc;
 
        // Print vertex
        System.out.print(v.strKey +"/"+v.ccNum+" -> ");
         
        // Extract the list of all vertices that are connected to Vertex v
        int idx = -1;
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i] == v) idx = i;
        }

        // Traverse all the list check if anyone are already visited or not
        // If no, then explore the vertex
        for (Integer i : adjacencyList[idx]) {
            if (!vertexList[i].visited) Explore(vertexList[i]);
        }

    }
    
    public void DFS(){
        for (Vertex v : vertexList) {
            if (v != null) v.visited = false;
        }

        // Set cc (connected component number) to 1
        cc = 1;
        // Check all vertices if they are visited or not
        // If no, then explore the vertex, then increase cc by 1
        for (Vertex v : vertexList) {
            if (v != null && !v.visited) {
                Explore(v);
                cc++;
            }
        }
        
        System.out.println();
    }

    
    
    // This is editable test case, but no need to edit
    public static Graph constructGraph1(){
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
        
        return graph;
    }
    
    // This is editable test case, but no need to edit
    public static Graph constructGraph2(){
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
        return graph;
    }
}

