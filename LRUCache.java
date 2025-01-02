import java.util.*;

class CDLLNode {    // Node class for Circular Doubly LinkedList
    int key, val;
    CDLLNode prev, next;

    public CDLLNode(int key, int val) {     // Constructor to initialize key and val;
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

class CDLL {        // Circular Double LinkedList implementation
    CDLLNode head = null;

    public CDLLNode insertBegin(int k, int v) {     // inserting node beginning of circular Double LinkedList
        CDLLNode nn = new CDLLNode(k, v);
        nn.next = nn;
        nn.prev = nn;
        if (head == null) {         // If Circular Double LinkedList is empty
            head = nn;
            return head;
        }
        CDLLNode last = head.prev;      // Atleast one elment present in CDLL
        nn.next = head;
        head.prev = nn;
        last.next = nn;
        nn.prev = last;
        head = nn;
        return head;
    }

    public void print() {       // To print elements in LRU
        if (head == null) {
            System.out.println("CDLL is empty");
            return;
        }
        CDLLNode temp = head;
        System.out.print("Elements in LRU CACHE: ");
        do {
            System.out.print(temp.val + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
    

    public int remove() {       // To remove elements the most recently used
        if (head == null) {     // returning -1 if no elements are present
            return -1;
        }
        CDLLNode last = head.prev;  // removing the last element of CDLL
        if (head == last) {
            int key = head.key;
            head = null;
            return key;
        }
        int key = last.key;
        last.prev.next = head;
        head.prev = last.prev;
        return key;
    }

    public void moveAtFront(CDLLNode nodeToMove) {      // moving elements to from their current position to first position
        if (nodeToMove == head) {
            return;
        }
        nodeToMove.prev.next = nodeToMove.next;
        nodeToMove.next.prev = nodeToMove.prev;
        CDLLNode last = head.prev;
        nodeToMove.next = head;
        head.prev = nodeToMove;
        nodeToMove.prev = last;
        last.next = nodeToMove;
        head = nodeToMove;
    }
}

public class LRUCache {     // Actual implementation of LRU

    int size = 0;           
    int capacity = 0;       // total capacity of LRU
    CDLL LL;
    Map<Integer, CDLLNode> mp;     // Map to store the address of particular nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;
        mp = new HashMap<>();
        LL = new CDLL();
    }

    public int get(int key) {       // accessing the nodes of particular locations and pushing them on front of CDLL
        if (!mp.containsKey(key)) {
            return -1;
        }
        int ret = mp.get(key).val;
        LL.moveAtFront(mp.get(key));
        return ret;
    }

    public void put(int key, int val) {     // Either to place new (key, pair) or to update value of particular key
        if (mp.containsKey(key)) {          //updation of key value
            mp.get(key).val = val;
            LL.moveAtFront(mp.get(key));
        } else {                            // insertion of new nodes
            if (size >= capacity) {
                int del = LL.remove();
                mp.remove(del);
                size--;
            }
            CDLLNode nn = LL.insertBegin(key, val);
            mp.put(key, nn);
            size++;
        }
    }
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        System.out.println("inserting key pair(1,10)");
        cache.put(1, 10);
        cache.LL.print();
        System.out.println("inserting key pair(2,20)");
        cache.put(2, 20);
        cache.LL.print();

        System.out.println("inserting key pair(3,30)");
        cache.put(3, 30);
        cache.LL.print();

        System.out.println("Inserting key pair(4,40)");
        cache.put(4, 40);
        cache.LL.print();

        System.out.println("Get 2: " + cache.get(2));
        cache.LL.print();
        System.out.println("Updating val for key 40 as 60");
        cache.put(4, 60);
        cache.LL.print();

        System.out.println("Get 1: " + cache.get(1));
        cache.LL.print();

        System.out.println("Get 3: " + cache.get(3));
        cache.LL.print();
        System.out.println("Inserting key pair(5,50)");
        cache.put(5, 50);
        cache.LL.print();

    }
}
