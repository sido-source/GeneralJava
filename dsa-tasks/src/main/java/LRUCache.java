import java.util.HashMap;

/*
Advantages of Time Complexity
Using the HashMap and doubly linked list together gives significant performance advantages:

Add/Remove/Modify First and Last Element:
Using head and tail, adding/removing elements from the front or back of the list is O(1).
This is because the head and tail directly point to the first and last elements.

Accessing Elements:
Using the HashMap, you can access any node in O(1) time.
After finding the node, the doubly linked list makes reordering (e.g., moving a node to the front) efficient.
 */
// #Cache #Node #optimized
class LRUCache {

    // Node class for Doubly Linked List
    class Node {
        int key;
        int value;
        Node prev, next;
        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        // Initialize the head and tail dummy nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Get the value of the key, move the node to the head (most recent)
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1; // Key doesn't exist
        }
        Node node = cache.get(key);
        detach(node);    // Remove from current position
        attachIntoHead(node); // Move to head (most recently used)
        return node.value;
    }

    // Put key-value pair into the cache
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update the node if it exists
            Node node = cache.get(key);
            node.value = value;
            detach(node);
            attachIntoHead(node);
        } else {
            if (cache.size() == capacity) { // ?
                // Remove the least recently used item (tail.prev)
                cache.remove(tail.prev.key);
                detach(tail.prev);
            }
            // Insert new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            attachIntoHead(newNode);
        }
    }

    // Helper function to remove a node from the linked list
    private void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper function to insert a node at the head
    private void attachIntoHead(Node node) {
        // attach node references - to the head and to first element in the current list
        node.next = head.next;
        node.prev = head;
        //attach other nodes referenced to new node address
        // move the first element into next position by linking first.prev with new node
        head.next.prev = node;
        // attach head to node (new element)
        head.next = node;
    }
}

