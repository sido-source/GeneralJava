package map;

public class CustomHashMap<K, V> {
    // Implementation of a custom hashmap in Java

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    int size;
    Node<K, V>[] container;

    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        container = (Node<K, V>[]) new Node[capacity];
    }

    public void put(K key, V value) throws IllegalStateException {
        if (!hasSpace()) throw new IllegalStateException("No space left");

        int hashKey = Math.abs(key.hashCode() % capacity);
        Node<K, V> bucket = container[hashKey];
        if (bucket == null) {
            container[hashKey] = new Node<>(key, value);
            size++;
            return;
        }

        Node<K, V> curr = bucket;
        while (curr != null) {
            if (curr.key != null && curr.key.equals(key)) {
                curr.value = value; // Update existing key
                return;
            }
            if (curr.next == null) break;
            curr = curr.next;
        }

        curr.next = new Node<>(key, value); // Add new node to the end of the list
        size++;
    }

    public V get(K key) {
        int hashKey = Math.abs(key.hashCode() % capacity);
        Node<K, V> bucket = container[hashKey];

        Node<K, V> curr = bucket;
        while (curr != null) {
            if (curr.key != null && curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }

        return null; // Key not found
    }

    public int getSize() {
        return size;
    }

    public boolean hasSpace() {
        return capacity > size;
    }
}
