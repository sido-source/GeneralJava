package com.monolith.java;

public interface Worker {

    static String country = "PL";

    default void printCountry() {
        System.out.println("Country: " + country);
    }

    void work(String workDescription);
    double getSalary(); // Implicitly public abstract
}

interface OfficeWorker extends Worker {
    final int maxHoursInFrontMonitor = 8;

    @Override
    default void work(String description) {
        System.out.println("Office worker is working...");
        System.out.println(description);
    }

    int deskReservation(Worker worker);
}
