import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.nio.charset.Charset;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("Welcome");
    }



    @Test
    void FileReadWriteTest() {
        Path filePath = Path.of("example.txt");

        if (Files.exists(filePath)) {
            System.out.println("File exists: " + filePath);
            try {
                Files.delete(filePath);
                System.out.println("File deleted: " + filePath);
            } catch (IOException e) {
                System.out.println("Error deleting file: " + filePath);
                throw new RuntimeException(e);
            }
        }


        Charset charset = Charset.defaultCharset();

        // dummy Object which will play crucial role with locking the read/write operation
        Object lock = new Object();


        RunnableWriter writer = new RunnableWriter(200, filePath, charset, lock);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        RunnableReader reader = new RunnableReader(500, filePath, charset, lock);

        Thread readerThread = new Thread(reader);
        Thread writerThread = new Thread(writer);

        writerThread.start();
        readerThread.start();

        try {
            Thread.sleep(9000); // Let the threads run for 9s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------------Interrupt threads------------------------");
        writerThread.interrupt();
        readerThread.interrupt();

        try {
            writerThread.join();
            readerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RunnableReader implements Runnable {
    int sleepTime = 0;
    Path path;
    Charset charset;
    private final Object lock;

    RunnableReader(int sleepTime, Path path, Charset charset, Object lock) {
        this.sleepTime = sleepTime;
        this.path = path;
        this.charset = charset;
        this.lock = lock;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()), charset))) {
            String line;
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lock) {
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Reader: " + line);
                        Thread.sleep(sleepTime); // Simulate processing time
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            //Thread.currentThread().interrupt(); // Restore interrupted status
            if (e instanceof InterruptedException) {
                System.out.println("Reader ended due to InterruptedException");
            } else {
                System.out.println("Reader ended due to IOException");
            }
            e.printStackTrace();
        }
    }
}

class RunnableWriter implements Runnable {
    int speed = 0;
    Path path;
    Charset charset;
    private final Object lock;

    RunnableWriter(int speed, Path path, Charset charset, Object lock) {
        this.speed = speed;
        this.path = path;
        this.charset = charset;
        this.lock = lock;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path.toFile(), true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.write(128); // Write a byte to indicate the start of a new message
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, charset))) {
            Random rand = new Random();
            StringBuilder stringBuilder = new StringBuilder(20);

            while (!Thread.currentThread().isInterrupted()) {
                stringBuilder.setLength(0); // Reset the StringBuilder for new message
                for (int i = 0; i < 20; i++) {
                    int letter = rand.nextInt(26) + 'a';
                    stringBuilder.append((char) letter);
                }

                synchronized (lock) {
                    writer.write(stringBuilder.toString());
                    writer.newLine();
                    writer.flush(); // Ensure the message is written to the file
                    System.out.println("Writer: " + stringBuilder.toString());
                }
                Thread.sleep(speed); // Simulate delay between writes
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status

            if (e instanceof InterruptedException) {
                System.out.println("Writer ended due to InterruptedException");
            } else {
                System.out.println("Writer ended due to IOException");
            }
            e.printStackTrace();
        }
    }
}



/*

To solve the problem of coordinating the reading and writing to the same file, we need to ensure that the writer and reader do not access the file simultaneously, which would lead to inconsistent data being read or written. We can achieve this by using synchronization and some form of queuing mechanism or lock to control access to the shared resource (the file).

        ### Customizing the Code

Below is your customized code with synchronization added to ensure proper coordination between the reader and writer threads.

        ### Explanation of the Code

1. **Synchronization**:
        - The key here is the use of a shared `lock` object to synchronize the access to the file. Both the `RunnableReader` and `RunnableWriter` synchronize on this lock before performing file operations. This ensures that only one thread can access the file at a time.
   - The `synchronized` block ensures that the critical section (file reading or writing) is accessed by only one thread at a time.

        2. **Handling Thread Interruption**:
        - The `run()` methods of both `RunnableReader` and `RunnableWriter` are designed to periodically check if the thread has been interrupted using `Thread.currentThread().isInterrupted()`. If interrupted, the thread will exit gracefully.
        - The `try-with-resources` statement is used to automatically close the `BufferedReader` and `BufferedWriter` when done, ensuring that file handles are released even if an exception occurs.

3. **File Access**:
        - The writer appends to the file, while the reader reads from the file. The writer uses `flush()` to ensure that the data is actually written to the file, which the reader can then pick up.

4. **Polling Delay**:
        - The reader thread includes a small polling delay (`Thread.sleep(100)`) to avoid busy-waiting when it doesn't have data to read. This helps to balance CPU usage.

        ### Scenario Where This Concept is Needed

**Synchronization and Queuing in Real-world Scenarios**:
        - **Chat Applications**: In a chat application, synchronization is needed to ensure that messages sent by multiple users are correctly ordered and written to the chat log (e.g., a file or database). If two users send messages at the same time, synchronization ensures that one message is logged before the other.
        - **Logging Systems**: In a multi-threaded server, different threads might want to write logs to the same file. Synchronization ensures that log entries from different threads do not interleave, which would make the log file unreadable.
        - **Banking Transactions**: Consider a scenario where multiple ATMs are accessing the same account. Synchronization ensures that all deposits and withdrawals are processed in the correct order, preventing inconsistencies in the account balance.
        - **Concurrent File Processing**: When multiple processes or threads need to read and write to the same file (e.g., a shared configuration file), synchronization ensures that one process doesn't read incomplete or inconsistent data.

This task teaches you the importance of synchronization when dealing with shared resources, ensuring data consistency and thread safety in concurrent applications.


*/
