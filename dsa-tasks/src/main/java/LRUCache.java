import java.util.HashMap;

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
        remove(node);    // Remove from current position
        insertAtHead(node); // Move to head (most recently used)
        return node.value;
    }

    // Put key-value pair into the cache
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update the node if it exists
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            insertAtHead(node);
        } else {
            if (cache.size() == capacity) {
                // Remove the least recently used item (tail.prev)
                cache.remove(tail.prev.key);
                remove(tail.prev);
            }
            // Insert new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            insertAtHead(newNode);
        }
    }

    // Helper function to remove a node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper function to insert a node at the head
    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}

