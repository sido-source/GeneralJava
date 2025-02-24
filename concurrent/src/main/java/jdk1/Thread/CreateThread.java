package jdk1.Thread;

public class CreateThread extends Thread {

    public CreateThread() {

    }

    @Override
    public void run() {
        System.out.println("Start new task");
        System.out.println("Start processing task");
        System.out.println("Finish processing task");
        System.out.println("Finish task");
    }

    public static void main(String[] args) {
        Thread thread = new CreateThread();
        thread.start();
    }
}
