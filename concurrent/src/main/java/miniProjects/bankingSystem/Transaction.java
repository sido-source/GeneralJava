package miniProjects.bankingSystem;

import java.util.concurrent.Callable;

public class Transaction implements Callable<TransactionResult> {
    private final User sender;
    private final User receiver;
    private final double amount;

    public Transaction(User sender, User receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public TransactionResult call() {
        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            String message = "Transaction successful: " + sender.getName() + " sent " + amount + " to " + receiver.getName();
            System.out.println(message);
            return new TransactionResult(true, message, null);
        } else {
            String error = "Insufficient funds";
            String message = "Transaction failed: " + sender.getName() + " could not send " + amount + " to " + receiver.getName();
            System.out.println(message);
            return new TransactionResult(false, message, error);
        }
    }
}

