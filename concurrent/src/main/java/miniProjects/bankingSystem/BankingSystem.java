package miniProjects.bankingSystem;

import java.util.concurrent.*;

public class BankingSystem {
    private final ExecutorService executor;
    private final BlockingQueue<Future<TransactionResult>> transactionResults;

    public BankingSystem(int numberOfThreads) {
        this.executor = Executors.newFixedThreadPool(numberOfThreads);
        this.transactionResults = new LinkedBlockingQueue<>();
    }

    public void executeTransaction(Transaction transaction) {
        Future<TransactionResult> result = executor.submit(transaction);
        transactionResults.add(result);
    }

    public void scheduleTransaction(Transaction transaction, long delay, TimeUnit unit) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            Future<TransactionResult> result = executor.submit(transaction);
            transactionResults.add(result);
        }, delay, unit);
        scheduler.shutdown();
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void printTransactionResults() throws InterruptedException, ExecutionException {
        while (!transactionResults.isEmpty()) {
            Future<TransactionResult> result = transactionResults.poll();
            if (result != null) {
                System.out.println(result.get());  // Print the transaction result
            }
        }
    }
}

