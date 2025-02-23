package newApi.buffers;

import java.nio.ByteBuffer;

public class ByteBufferDemo {
    public static void main(String[] args) {
        // Create a ByteBuffer with a capacity of 10 bytes
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // Step 1: Fill the buffer with data
        System.out.println("Step 1: Writing data to the buffer");
        buffer.put((byte) 'H');
        buffer.put((byte) 'e');
        buffer.put((byte) 'l');
        buffer.put((byte) 'l');
        buffer.put((byte) 'o');
        printBufferState(buffer);

        // Step 2: Switch to read mode and read the data
        System.out.println("\nStep 2: Switching to read mode and reading data");
        buffer.flip(); // Switch to read mode
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get() + " ");
        }
        printBufferState(buffer);

        // Step 3: Rewind the buffer and read the data again
        System.out.println("\nStep 3: Rewinding the buffer and reading data again");
        buffer.rewind(); // Reset position to 0
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get() + " ");
        }
        printBufferState(buffer);

        // Step 4: Clear the buffer and write new data
        System.out.println("\nStep 4: Clearing the buffer and writing new data");
        buffer.clear(); // Switch to write mode
        buffer.put((byte) 'W');
        buffer.put((byte) 'o');
        buffer.put((byte) 'r');
        buffer.put((byte) 'l');
        buffer.put((byte) 'd');
        printBufferState(buffer);

        // Step 5: Switch to read mode and read the new data
        System.out.println("\nStep 5: Switching to read mode and reading new data");
        buffer.flip(); // Switch to read mode
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get() + " ");
        }
        printBufferState(buffer);
    }

    private static void printBufferState(ByteBuffer buffer) {
        System.out.println("Buffer state: position=" + buffer.position() +
                ", limit=" + buffer.limit() +
                ", capacity=" + buffer.capacity());
    }
}
