How BufferedInputStream Works

BufferedInputStream, the underlying mechanism is designed to improve efficiency by reading data in chunks rather than one byte at a time. This approach significantly reduces the number of I/O operations required.

Buffering Mechanism:
When you call read() on a BufferedInputStream, it doesn't directly read from the file each time. Instead, it reads a larger block of data (typically 8KB) from the file into an internal buffer.
The buffer is an array that temporarily holds data that has been read from the file but not yet processed by your application.

Single Byte Reads:
The first call to read() fills the buffer from the file and then returns the first byte from the buffer.
Subsequent calls to read() retrieve bytes from this buffer until it's exhausted.

Refilling the Buffer:
Once the buffer is empty (i.e., all the buffered data has been consumed), the next read() call will trigger another bulk read operation from the file to refill the buffer.
Benefits of Buffering

Reduced I/O Operations: By reading larger chunks of data at once, the number of interactions with the underlying file system is minimized, which is a relatively slow operation compared to accessing data in memory.
Performance: Fewer read operations lead to better performance, especially for disk-based I/O, where seek time and latency are significant factors.
System Resource Efficiency: Fewer system calls translate to reduced CPU overhead for managing these operations.

BufferedReader and BufferedWriter
Purpose: Improve performance for character-based I/O, with methods for "line-by-line"!! reading and writing.
Usage:

BufferedReader br = new BufferedReader(new FileReader("file.txt"));
BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"));

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyWithBuffer {

    public static void copyFileWithBuffer(String sourcePath, String destinationPath) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destinationPath))) {

            int byteData;
            while ((byteData = bis.read()) != -1) {
                bos.write(byteData);
            }
        }
    }

    public static void main(String[] args) {
        String source = "largeFile.txt";
        String destination = "copiedFileWithBuffer.txt";

        long startTime = System.currentTimeMillis();
        try {
            copyFileWithBuffer(source, destination);
            long endTime = System.currentTimeMillis();
            System.out.println("Copy with buffer completed in: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
}


------------------------------
BufferedWriter, work similarly to buffered input streams but on the output side. They provide a significant performance improvement by writing data in larger chunks to the underlying output stream, reducing the number of write operations. Here's how buffered writing works and how it boosts performance:

How BufferedWriter Works
Buffering Mechanism:
A BufferedWriter maintains an internal buffer (default size is 8192 characters or 8KB) that accumulates characters written by the application.
When you write data to a BufferedWriter, it is stored in this internal buffer rather than being immediately written to the underlying output stream (like a file or socket).

Flushing the Buffer:
The buffer is automatically flushed to the underlying output stream when it is full, or it can be manually flushed using the flush() method.
Flushing writes all buffered data to the underlying stream in a single operation.
Writing Efficiency:
By grouping data and writing it in bulk, buffered writers reduce the number of write operations. This is especially beneficial for disk I/O, which is relatively slow compared to in-memory operations.
Benefits of Using BufferedWriter
Reduced I/O Operations: Writing larger chunks of data at once minimizes the number of interactions with the underlying file system or network, which reduces latency and overhead.
Improved Performance: This is especially noticeable when writing small amounts of data repeatedly, such as writing characters in a loop.
Efficient Resource Usage: Fewer system calls translate to less CPU time spent managing these operations.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteWithBuffer {

    public static void writeToFileWithBuffer(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Line " + i + "\n");
            }
            // Explicit flush not needed as close() will flush
        }
    }

    public static void main(String[] args) {
        try {
            writeToFileWithBuffer("outputWithBuffer.txt");
            System.out.println("Data written with buffering.");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
