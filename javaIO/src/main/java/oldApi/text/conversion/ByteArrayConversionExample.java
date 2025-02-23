package oldApi.text.conversion;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteArrayConversionExample {
    public static void main(String[] args) {
        // Simulate a byte array encoded in Windows-1252
        byte[] windows1252Bytes = "Café".getBytes(Charset.forName("Windows-1252"));

        // Step 1: Decode bytes into a String using Windows-1252
        String content = new String(windows1252Bytes, Charset.forName("Windows-1252"));

        // Step 2: Encode the String into bytes using US_ASCII
        byte[] utf16Bytes = content.getBytes(StandardCharsets.US_ASCII);

        // Print the results
        System.out.println("Original String: " + content);
        System.out.println("US_ASCII Bytes: " + new String(utf16Bytes, StandardCharsets.US_ASCII));
    }
}
