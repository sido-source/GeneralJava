package jdk5.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Intro {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
    }
}
