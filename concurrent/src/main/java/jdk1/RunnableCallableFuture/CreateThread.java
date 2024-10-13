package jdk1.RunnableCallableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateThread implements Runnable{
    @Override
    public void run() {
        System.out.println("run some subtasks");
    }

    String someGlobalValue = "xd";
    int someSpecificValue = 10;
    volatile static int index = 0;

    public static void main(String[] args) {


        Runnable runnable = () -> {
            System.out.println("functional way of creating thread/task");
        };

        Callable<String> callableWithReturn = () -> {
            return Thread.currentThread() + " is connected with " + index++;
        };

        new Thread(new CreateThread()).start();
        new Thread(runnable).start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future> result = new ArrayList<>();
        for (int i =0; i<1000;i++) {
            result.add(executorService.submit(callableWithReturn));
        }


        result.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
    }
}
