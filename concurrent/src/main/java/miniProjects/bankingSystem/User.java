package miniProjects.bankingSystem;

import java.util.concurrent.locks.ReentrantLock;

public class User {
    private final String name;
    private double balance;
    private final ReentrantLock lock;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return name + " balance: " + balance;
    }
}

