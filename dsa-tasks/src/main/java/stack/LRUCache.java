package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    //Explanation:
    //1. LinkedHashMap with 3-Parameter Constructor:
    //
    //new LinkedHashMap<>(capacity, 0.75f, true):
    //capacity: The initial capacity of the map. This is the number of elements it can hold before resizing.
    //0.75f: This is the load factor. It determines when the map should be resized. A load factor of 0.75 means that when the map is 75% full, it will resize to a larger capacity.
    //true: This specifies the access order. If set to true, the map maintains entries in the order they were last accessed (most recently used entries move to the end).
    //2. removeEldestEntry Method:
    //
    //removeEldestEntry(Map.Entry<Integer, Integer> eldest):
    //This is an overridden method in LinkedHashMap that determines when to remove the least recently used (LRU) entry.
    //Condition: size() > capacity. This ensures that if the map exceeds the specified capacity, the oldest entry (i.e., the least recently accessed one) is removed from the map.


    // Use LinkedHashMap for tracking order of access
    private final Map<Integer, Integer> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        // Initialize LinkedHashMap with capacity and accessOrder set to true
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                // Remove the least recently used element if size exceeds capacity
                return size() > capacity;
            }
        };
        this.capacity = capacity;
    }

    // Get the value for the key, or return -1 if the key doesn't exist
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // Since LinkedHashMap is access-ordered, calling get() moves the entry to the end
        return cache.get(key);
    }

    // Put the key-value pair in the cache
    public void put(int key, int value) {
        cache.put(key, value);
    }
}

/*
The removeEldestEntry() method in LinkedHashMap is called automatically after each insertion (put() operation) to check if the size of the map exceeds the specified capacity.

Here’s the detailed explanation of how and when it gets called:

When removeEldestEntry() is Triggered:
During the put() Operation: Every time a new key-value pair is inserted into the LinkedHashMap via put(), the LinkedHashMap checks whether the size of the map exceeds the capacity. If it does, it calls the removeEldestEntry() method to determine whether the oldest (least recently accessed) entry should be removed.
Eldest Entry: The eldest entry is the entry that was inserted or accessed least recently (depending on whether the LinkedHashMap is in insertion-order or access-order mode). In the case of LRU Cache, we use access-order mode, so the eldest entry is the least recently accessed entry.
How removeEldestEntry() Works:
By overriding the removeEldestEntry() method, you can control when the eldest entry should be removed.
If the method returns true, the eldest entry is removed from the map.
If the method returns false, no entry is removed, and the map keeps growing.
In the LRU Cache implementation, we override removeEldestEntry() to return true when the size of the cache exceeds the specified capacity. This ensures that the least recently used entry is automatically removed when the cache is full.
 */
