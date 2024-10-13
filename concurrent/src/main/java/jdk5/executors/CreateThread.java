package jdk5.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThread implements Runnable{

    int index;

    public CreateThread(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("Created thread " + Thread.currentThread());
    }

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {

            for (int i =0; i < 1000; i++) {
                executorService.execute(new CreateThread(i));
            }

        }

    }
}
