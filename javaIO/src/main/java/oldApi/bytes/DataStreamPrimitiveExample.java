package oldApi.bytes;

import java.io.*;

// DataInput/OutputStreams are mainly for binary data like file.bin
public class DataStreamPrimitiveExample {

    public static void main(String[] args) {
        main1();
        main2();
    }

    private static void main1() {
        int[] numbers = {1, 2, 3, 4, 5};
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("array.bin"))) {
            dos.writeInt(numbers.length);
            for (int number : numbers) {
                dos.writeInt(number);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("array.bin"))) {
            int length = dis.readInt();
            numbers = new int[length];
            for (int i = 0; i < length; i++) {
                numbers[i] = dis.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void main2() {
            try {
                // Create a ByteArrayOutputStream to hold the data in memory
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                // Wrap it with a DataOutputStream to write primitive data types
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

                // Writing data to the DataOutputStream
                dataOutputStream.writeInt(123);
                dataOutputStream.writeFloat(3.14f);
                dataOutputStream.writeUTF("Hello, World!");

                // It's good practice to close the stream to release resources
                dataOutputStream.close();

                // Retrieve the byte array containing the written data
                byte[] data = byteArrayOutputStream.toByteArray();

                // Create a ByteArrayInputStream to read the data from the byte array
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                // Wrap it with a DataInputStream to read primitive data types
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

                // Reading data from the DataInputStream
                int number = dataInputStream.readInt();
                float pi = dataInputStream.readFloat();
                String message = dataInputStream.readUTF();

                // Displaying the read data
                System.out.println("Read Integer: " + number);
                System.out.println("Read Float: " + pi);
                System.out.println("Read UTF String: " + message);

                // Close the DataInputStream
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
