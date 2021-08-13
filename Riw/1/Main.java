import lib.DynamicArray;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        DynamicArray var = new DynamicArray(2);
        var.printStructure();
        var.pushBack(5);
        var.popBack();
    }
    
}
