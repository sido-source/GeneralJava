package oldApi.bytes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FisFosCharsetExample {
    public static void main(String[] args) {
        String text = "Hello, 世界! 😊";

        // Writing using FOS (FileOutputStream) - manual charset handling
        try (FileOutputStream fos = new FileOutputStream("utf16_manual.txt")) {
            byte[] utf16Bytes = text.getBytes(StandardCharsets.UTF_16);
            fos.write(utf16Bytes);
            System.out.println("✅ Written to file (UTF-16 bytes): " + Arrays.toString(utf16Bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading using FIS (FileInputStream) - manual charset handling
        try (FileInputStream fis = new FileInputStream("utf16_manual.txt")) {
            byte[] readBytes = fis.readAllBytes();
            String decodedText = new String(readBytes, StandardCharsets.UTF_16);
            System.out.println("✅ Decoded text from file: " + decodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
