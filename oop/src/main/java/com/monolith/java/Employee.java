package com.monolith.java;

public class Employee extends Person implements OfficeWorker{
    public Employee(String name, int age, double height) {
        super(name, age, height);
    }

    @Override
    public void work(String description) {
        OfficeWorker.super.work(description);
    }

    @Override
    public int deskReservation(Worker worker) {
        return 0;
    }

    @Override
    public String getOccupation() {
        return "";
    }

    @Override
    public void printCountry() {
        OfficeWorker.super.printCountry();
    }

    @Override
    public double getSalary() {
        return 0;
    }
}
