package com.monolith.java.singleton;

/*
Bill Pugh Singleton (Lazy Initialization using Inner Static Helper Class)
This approach is the most efficient and is widely considered the best practice for implementing a thread-safe singleton in Java.
It takes advantage of class loading and the fact that static inner classes are loaded only when they are referenced.

Pros: Thread-safe without needing synchronization or volatile. Lazy initialization without any overhead. Clean and simple to implement.
Cons: None in most cases, this is often considered the best practice.

 */
public class ThreadSafeSingleton1 {

    // Private constructor to prevent instantiation
    private ThreadSafeSingleton1() {}

    // Static inner class responsible for holding the single instance
    private static class SingletonHelper {
        // The instance is created when SingletonHelper is loaded into memory
        private static final ThreadSafeSingleton1 INSTANCE = new ThreadSafeSingleton1();
    }

    // Public method to provide access to the instance
    public static ThreadSafeSingleton1 getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

