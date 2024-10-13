package com.monolith.java.singleton;

/*
* This is one of the simplest approaches. The singleton instance is created at the time of class loading. Since the instance is created only once and final,
* it is inherently thread-safe without needing volatile.
*
*Pros: Very simple to implement. Thread-safe because the instance is initialized during class loading.
Cons: The instance is created even if it might not be used, potentially wasting memory.
* */
public class EagerSingletonWithFinal {

    // Instance created at the time of class loading
    private static final EagerSingletonWithFinal INSTANCE = new EagerSingletonWithFinal();

    // Private constructor to prevent instantiation
    private EagerSingletonWithFinal() {}

    // Public method to provide the single instance
    public static EagerSingletonWithFinal getInstance() {
        return INSTANCE;
    }
}
