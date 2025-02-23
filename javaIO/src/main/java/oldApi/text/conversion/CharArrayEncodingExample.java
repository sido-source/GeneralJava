package oldApi.text.conversion;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharArrayEncodingExample {
    public static void main(String[] args) {

        // In Java, a char is always a UTF-16 code unit. This means that a char[] is inherently UTF-16 encoded.

        // Step 1: Create a char array
        char[] chars = {'H', 'e', 'l', 'l', 'o', ' ', '世', '界'};

        // Step 2: Convert the char array to a String
        String str = new String(chars);

        System.out.println(str);
        // Step 3: Encode the String into bytes using a specific charset (e.g., UTF-8)
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_8);

        // Print the bytes
        System.out.println("UTF-8 Bytes: ");
        for (byte b : utf8Bytes) {
            System.out.printf("%02x ", b);
        }
    }
}
