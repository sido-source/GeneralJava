package com.monolith.java.singleton;

/*
Lazy Initialization with volatile and Double-Checked Locking
For lazy initialization (creating the instance only when it is first needed), volatile is used in
conjunction with double-checked locking to ensure thread safety while avoiding the performance overhead of synchronized blocks in every call to getInstance().
 */
public class ThreadSafeSingleton {

    // volatile ensures visibility of changes across threads
    private static volatile ThreadSafeSingleton instance;

    // Private constructor to prevent instantiation
    private ThreadSafeSingleton() {}

    // Double-checked locking pattern for thread safety
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {  // First check (without locking)
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {  // Second check (with locking)
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

