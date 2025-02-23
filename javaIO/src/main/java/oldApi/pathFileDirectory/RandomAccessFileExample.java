package oldApi.pathFileDirectory;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileExample {

    public static void main(String[] args) {
        String filename = "random_access.bin"; // Replace with your file

        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            raf.writeInt(12345); // Write an integer
            raf.writeDouble(3.14159); // Write a double
            raf.writeUTF("Random Access"); // Write a UTF-8 string

            raf.seek(0); // Move the file pointer to the beginning

            int intValue = raf.readInt();
            double doubleValue = raf.readDouble();
            String stringValue = raf.readUTF();

            System.out.println("Integer: " + intValue);
            System.out.println("Double: " + doubleValue);
            System.out.println("String: " + stringValue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
