import hw3.*;

public class Main {
        public static void main(String[] args) {
            SinglyLinkedList list = new SinglyLinkedList("list7");
            list.pushBack(new Node(5906001, "Matthew", 3.50));
            list.pushBack(new Node(5906002, "Mark", 2.75));
            list.pushBack(new Node(5906003, "Luke", 3.00));
            list.pushBack(new Node(5906004, "John", 3.75));
            list.pushBack(new Node(5906005, "James", 3.25));
            list.pushBack(new Node(5906006, "Peter", 2.85));
            list.printStructure();
            Node node = list.findNode(5906003);
            list.addNodeAfter(node, new Node(5906007, "John", 2.50));
            list.printStructure();
            (list.eraseNode(5906001)).printIDName();
            list.printStructure();
            (list.eraseNode(5906006)).printIDName();
            list.printStructure();
            (list.eraseNode(5906003)).printIDName();
            list.printStructure(); (list.eraseNode(5906003)).printIDName();
            list.printStructure();
        }
}
