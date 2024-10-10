package com.monolith.java;

import java.util.Optional;

public class Main1 {

    public static void main(String[] args) {
        int[] spy = {0};
        Optional<String> opt = Optional.of("this");
        Optional<String> before = opt.flatMap(s -> {
            spy[0] = spy[0] + 1;
            return Optional.of(s + " and that");
        }); // this and that
        System.out.printf("Case 1 before - spy: %s%n", spy[0]); // 0
        String result = before.orElse("nothing");
        System.out.printf("Case 1 after - result: %s, spy: %s%n", result, spy[0]); //  this and that, 1
    }
}
