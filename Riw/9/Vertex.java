
public class Vertex {
    
    // Vertex Information
    String strKey;
    int intKey;
    
    // For use only in BFS and DFS
    int ccNum;
    boolean visited;
    int dist;
    
    // This previous pointer is needed for constructing shortest-path tree
    // Hint: This variable is used only in functions BFS() and getShortestPathList()
    Vertex prev; 
    
    public Vertex(String key){
        this.strKey = key;
    }
    
    public Vertex(int key){
        this.intKey = key;
        this.strKey = Integer.toString(key);
    }

  
}
