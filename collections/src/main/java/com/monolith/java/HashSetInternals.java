package com.monolith.java;

import java.util.Arrays;
import java.util.Iterator;

public class HashSetInternals {

    public static void main(String[] args) {


        /*

        public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable

        @java.io.Serial
        static final long serialVersionUID = -5024744406713321676L;

        // Dummy value to associate with an Object in the backing Map
        static final Object PRESENT = new Object();

        transient HashMap<E,Object> map;

        // Add to map the key and dump value
        public boolean add(E e) {
            return map.put(e, PRESENT)==null;
        }


         */

        java.util.Set<String> set = new java.util.HashSet<>(Arrays.asList(null, "hello", "world", "ez", "jest", "ez"));
        System.out.println(set);

        System.out.println("Adding: " + set.size());
        set.add("add");
        if ((set.contains("ez"))) {
            System.out.println("ez is inside set");
        } else {
            System.out.println("ez is not inside set");
        }

        System.out.println("Removing: " + set.size());
        set.remove("add");
        System.out.println("After remove");

        set.add(null);

        set.isEmpty();
        set.size();

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n\nChange set into array");
        String[] array = set.toArray(new String[0]);

        System.out.println("Array elements:");
        for (String str : array) {
            System.out.println(str);
        }
    }
}
