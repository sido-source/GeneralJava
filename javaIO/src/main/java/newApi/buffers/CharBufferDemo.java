package newApi.buffers;

import java.nio.CharBuffer;
import java.util.Arrays;

public class CharBufferDemo {

        public static void main(String[] args) {
            CharBuffer buffer = CharBuffer.allocate(20);

            // Step 1: Fill the buffer with data
            System.out.println("Step 1: Writing data to the buffer");
            buffer.put('H');
            buffer.put('e');
            buffer.put('l');
            buffer.put('l');
            buffer.put('o');
            printBufferState(buffer);

            // Step 2: Switch to read mode and read the data
            System.out.println("\nStep 2: Switching to read mode and reading data");
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print(buffer.get() + " ");
            }
            System.out.println();
            printBufferState(buffer);

            // Step 3: Append more data to the buffer
            System.out.println("\nStep 3: Appending more data to the buffer");
//            buffer.clear();
            buffer.limit(buffer.capacity());
            buffer.put('W');
            buffer.put('o');
            buffer.put('r');
            buffer.put('l');
            buffer.put('d');
            printBufferState(buffer);

            // Step 4: Switch to read mode and read the updated data
            System.out.println("\nStep 4: Switching to read mode and reading updated data");
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print(buffer.get() + " ");
            }
            System.out.println();

            //Extract the world string
            buffer.rewind();
            char[] worldChars = new char[buffer.limit()];
            buffer.get(worldChars);
            String worldString = new String(worldChars);
            System.out.println("Extracted World: " + worldString);

            printBufferState(buffer);
        }

        private static void printBufferState(CharBuffer buffer) {
            System.out.println("Buffer state: position=" + buffer.position() +
                    ", limit=" + buffer.limit() +
                    ", capacity=" + buffer.capacity() + "\n" +
                    "toString on buffer = " + buffer.toString() + "\n" +
                    "print entire buffer=" + Arrays.toString(buffer.array()));
        }
}
