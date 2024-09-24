package com.monolith.java.contains;

import java.util.Arrays;
import java.util.List;

public class ListContainsTarget {

    public static void main(String[] args) {

        List<String> baseList = Arrays.asList("hello", "world", "essayer", "helloh", "helllooaah");
        String target = "hel";

        List<String> collect = baseList.stream()
                .filter(string -> string.contains(target))
                .toList();

        System.out.println(collect);
    }
}
