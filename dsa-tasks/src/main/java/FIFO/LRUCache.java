package FIFO;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // FIFO queue
    Deque<Integer> cache;
    Map<Integer, Integer> lookup;
    int capacity;

    public LRUCache(int capacity) {
        this.cache = new ArrayDeque<>();
        this.lookup = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!lookup.containsKey(key)) {
            return -1;
        } else {
            // Move accessed element to the end of the queue
            cache.remove(key);
            cache.offer(key);
            return lookup.get(key);
        }
    }

    public void put(int key, int value) {
        if (lookup.containsKey(key)) {
            // Update value and move accessed element to the end of the queue
            cache.remove(key);
        } else if (cache.size() == capacity) {
            // Evict the least recently used element before adding new one
            int evictedKey = cache.poll();
            lookup.remove(evictedKey);
        }

        cache.offer(key);
        lookup.put(key, value);
    }
}
