package com.monolith.java;

class SecretAbstract {
    private String secret = "My secret";

    protected  String shareSecret() {
        return secret;  // Accessing private field
    }
}

public abstract class Person extends SecretAbstract {
    protected String name;
    protected int age;
    protected double height;

    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
        logCreation();  // Log the creation of the person
    }

    // Private helper method (only accessible within Person)
    private void logCreation() {
        System.out.println("Person created: " + name);
    }

    // Protected method (accessible to subclasses)
    protected boolean isTallerThan(double threshold) {
        return height > threshold;
    }

    // Concrete method
    public boolean isAdult() {
        return age >= 18;
    }

    // Abstract method (no implementation) - must be public!!
    public abstract String getOccupation();
}

// Concrete subclass
class Teacher extends Person {
    public Teacher(String name, int age, double height) {
        super(name, age, height);
        String secret = shareSecret();
        System.out.println("I am able to see secret: " + secret);
    }

    @Override
    public String getOccupation() {
        return "Teacher";
    }

    public void checkHeight() {
        if (isTallerThan(1.8)) { // Accessing protected method
            System.out.println("This person is tall!");
        }
    }
}
