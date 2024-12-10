package twoPointers;

public class MyHashMap {

    class Node {
        int key, val;
        Node next;

        public Node(int key, int val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    int capacity;
    Node[] buckets;
    int inUse;

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        buckets = new Node[capacity];
        inUse = 0;
    }

    int size() {
        return inUse;
    }

    boolean put(int key, int val) {
        if (inUse >= capacity) {
            return false;
        }

        int bucketNr = hash(key);
        Node node = buckets[bucketNr];

        if (node != null) {
            node = new Node(key, val, null);
            inUse++;
            return true;
        }

         while (node != null) {

             if (node.key == key) {
                 //continue;
                 break;
             }
            node = node.next;
        }

         node.next = new Node(key, val, null);
         inUse++;
         return true;
    }

    int get(int key) {
        int bucketNr = hash(key);
        Node node = buckets[bucketNr];

        Node curr = node;
        while (curr != null) {
            if (curr.key == key) {
                return curr.val;
            }
            curr = curr.next;
        }

        return -1;
    }

    int hash(int key) {
        return Integer.hashCode(key)%capacity;
    }

    public static void main(String[] args) {

        MyHashMap map = new MyHashMap(2);
        map.put(2,3);
        map.put(2,4);
        System.out.println(map.get(2));
        map.put(3,4);
        map.put(5,6);
    }
}
