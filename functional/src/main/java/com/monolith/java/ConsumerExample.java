package com.monolith.java;

import java.util.function.Consumer;
import java.util.stream.Stream;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class ConsumerExample {

    static Consumer<String> consumerString = ConsumerExample::processingMethod; // Fixed here

    public static void main(String[] args) {

        // way 1
        Stream.generate(() -> "Hello, world!")
                .limit(2)
                .forEach(consumerString); // Shortened

        // way 2
        Stream.generate(() -> "Hello, Poland!")
                .limit(2)
                .forEach(ConsumerExample::processingMethod); // Fixed here
    }

    public static void processingMethod(String str) {
        System.out.println("starting processing method");
        System.out.println("I get arg: " + str);
        System.out.println("finish processing method");
    }
}
