package twoPointers;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node {
        Node prev, next;
        int val, key;

        public Node(Node prev, Node next, int val, int key) {
            this.key = key; // when the map is full we have to know which key we have to delete
            this.prev = prev;
            this.next = next;
            this.val = val;
        }

        public Node(int val, int key) {
            this.key = key; // when the map is full we have to know which key we have to delete
            this.val = val;
        }
    }
    Map<Integer, Node> map;
    int capacity;
    // doubly linked list
    Node tail;
    Node head;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        tail = new Node(head, null,-1,-1);
        head = new Node(null, tail,-1, -1);
        tail.prev = head;

        map = new HashMap<>(capacity);
    }

    // return the value from the map
    // put the Node at the begining, after the head
    public int get(int key) {
        Node curr;
        if (map.containsKey(key)) {
            curr = map.get(key);
        } else {
            return -1;
        }

        detach(curr);
        addToHead(curr);
        return curr.val;
    }

    // [ H ] - [ 1 ] - [ 2 ] - [ T ]   
    void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    void addToHead(Node node) {
        // add new pointers to node
        node.prev = head;
        node.next = head.next;

        // update the head pointers (x2 from front and back)
        // update the head.next.prev pointer (in base case it is tail)
        head.next.prev = node;
        head.next=node;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Case 1: Node already exists in cache, so update its value
            Node node = map.get(key);
            node.val = value;
            detach(node);  // Detach the old node (if any) from the doubly linked list
            addToHead(node);  // Move the node to the head (mark it as the most recently used)
        } else {
            // Case 2: Node doesn't exist, so we need to add it
            if (map.size() >= capacity) {
                // Evict the least recently used node (LRU)
                Node toRemove = tail.prev;  // The node just before the tail is the LRU node
                detach(toRemove);  // Detach the LRU node from the list
                map.remove(toRemove.key);  // Remove it from the map
            }

            // Create the new node
            Node newNode = new Node(value, key);

            // Insert the new node at the head of the list (mark it as the most recently used)
            addToHead(newNode);

            // Add the new node to the map
            map.put(key, newNode);
        }
    }



    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2,1);
        System.out.println(lruCache.get(2));



        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}

/**
 * Your twoPointers.LRUCache object will be instantiated and called as such:
 * twoPointers.LRUCache obj = new twoPointers.LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */