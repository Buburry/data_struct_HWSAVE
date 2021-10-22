public class HashTable {
    String[] arr;
    int size;
    int capacity;
    
    // Hashing parameters
    int p; // Big Prime (for PolyHash())
    int x; // Small number (for PolyHash())
    
    public HashTable(int cap, int p, int x){
        arr = new String[cap];
        this.capacity = cap;
        this.p = p;
        this.x = x;
    }
    
    public void addString(String s){
        // Insert the string at that index that no one occupy
        // You should call getIndex()
        arr[getIndex(s)] = s;
    }
    
    // This function has 2 roles
    // [1] Find an empty space to push the string object
    // [2] Find location of the string
    // The algorithm is followed
    // 1. Hash the string and get the index
    // 2. If the arr[index] is empty, then return index
    // 3. If the arr[index] equals the string, then also return index
    // 4. Perform the quadratic probing and repeat 2.-3. until found the location
    public int getIndex(String s){
        int index = polyHash(s, p, x);
        for (int i = 0; i < capacity; i++) {
            index = (index + i) % capacity;
            if (arr[index] == null || arr[index] == s) return index;
        }
        return -1;
    }
    
    public static int polyHash(String s, int p, int x){
        int hash = 0;
        for (int i = s.length() - 1; i > -1; i--) {
            hash = (hash * x + (int)s.charAt(i)) % p;
        }
        return hash; 
    }
    
    // This is editable testcase, no need to edit
    public static void test1(){
        int p = 1100101;    // Big Prime    (Hash key1)
        int x = 1001;       // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = {"a", "b", "c", "A", "B", "BA", "BBA", "aaa", "aaaaa", "0","1","11","111", "abcdABCD01234" };
        for (int i=0; i<names.length; i++)
            System.out.println("HashCode of '" + names[i] + "' is "+HashTable.polyHash(names[i], p, x));
    }
    
    // This is editable testcase, no need to edit
    public static void test2(){
        int p = 1100101;    // Big Prime    (Hash key1)
        int x = 1001;       // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = {"a", "b", "c", "A", "B", "BA", "BBA", "aaa", "aaaaa", "0","1","11","111", "abcdABCD01234" };

        for (int i=0; i<names.length; i++){
            table.addString(names[i]);
            System.out.println("Index of '" + names[i] + "' is "+table.getIndex(names[i]));
        }
    }
    
    public static void test3(){
        int p = 1100101;   // Big Prime    (Hash key1)
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
}