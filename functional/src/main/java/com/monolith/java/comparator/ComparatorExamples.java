package com.monolith.java.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Vehicle class definition
class Vehicle {
    public String brand;
    public int horsepower;

    public Vehicle(String brand, int horsepower) {
        this.brand = brand;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return brand + " (" + horsepower + " HP)";
    }
}

public class ComparatorExamples {

    public static void main(String[] args) {
        Vehicle car1 = new Vehicle("Toyota", 180);
        Vehicle car2 = new Vehicle("Honda", 220);
        Vehicle car3 = new Vehicle("Bmw", 520);

        List<Vehicle> list = new ArrayList<>();
        list.add(car1);
        list.add(car2);
        list.add(car3);

        // 🔥 Sort by horsepower (Descending)
        Comparator<Vehicle> horsePowerDescending = (a, b) -> b.horsepower - a.horsepower;
        list.sort(horsePowerDescending);
        System.out.println("Sorted by horsepower (descending):");
        list.forEach(System.out::println);

        Comparator<Vehicle> horsePowerAscending = Comparator.comparing(a -> a.horsepower);
        list.sort(horsePowerAscending);
        System.out.println("Sorted by horsepower (ascending):");
        list.forEach(System.out::println);

        // ✨ Sort by brand length (Ascending)
        Comparator<Vehicle> brandLengthAscending = Comparator.comparing(a -> a.brand.length());
        list.sort(brandLengthAscending);
        System.out.println("\nSorted by brand length (ascending):");
        list.forEach(System.out::println);

        // 💡 Sort by brand lexicographically (Ascending)
        Comparator<Vehicle> brandNameAscending = Comparator.comparing(a -> a.brand);
        list.sort(brandNameAscending);
        System.out.println("\nSorted by brand name (ascending):");
        list.forEach(System.out::println);

        // 🎯 Combined Comparator (horsepower desc, then brand asc)
        Comparator<Vehicle> combinedComparator = horsePowerDescending.thenComparing(brandNameAscending);
        list.sort(combinedComparator);
        System.out.println("\nSorted by horsepower (desc) then brand name (asc):");
        list.forEach(System.out::println);
    }
}