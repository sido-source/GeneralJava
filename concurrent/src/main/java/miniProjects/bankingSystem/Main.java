package miniProjects.bankingSystem;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BankingSystem bankingSystem = new BankingSystem(3);

        User alice = new User("Alice", 1000);
        User bob = new User("Bob", 500);
        User charlie = new User("Charlie", 300);

        // Immediate transactions
        bankingSystem.executeTransaction(new Transaction(alice, bob, 200));
        bankingSystem.executeTransaction(new Transaction(bob, charlie, 100));
        bankingSystem.executeTransaction(new Transaction(charlie, alice, 150));

        // Scheduled transactions
        bankingSystem.scheduleTransaction(new Transaction(alice, bob, 300), 2, TimeUnit.SECONDS);
        bankingSystem.scheduleTransaction(new Transaction(bob, charlie, 200), 4, TimeUnit.SECONDS);
        bankingSystem.scheduleTransaction(new Transaction(charlie, alice, 100), 6, TimeUnit.SECONDS);

        // Wait for all transactions to complete
        Thread.sleep(7000); // give enough time for all transactions to finish

        // Print transaction results
        bankingSystem.printTransactionResults();

        // Final balances
        System.out.println("\nFinal Balances:");
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(charlie);

        // Shutdown the banking system
        bankingSystem.shutdown();
    }
}

