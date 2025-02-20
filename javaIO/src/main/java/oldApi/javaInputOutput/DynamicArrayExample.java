package oldApi.javaInputOutput;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DynamicArrayExample {

    public static byte[] readInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];  // Buffer size of 1KB
        int bytesRead;

        // Read data from input stream into the buffer
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

        // Convert the ByteArrayOutputStream to a byte array
        return buffer.toByteArray();
    }

    public static void main(String[] args) {
        String filePath = "oldApi/path/to/your/input/file.bin";

        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] result = readInputStreamToByteArray(inputStream);
            System.out.println("Read " + result.length + " bytes from the input stream.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

