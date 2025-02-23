package newApi.buffers;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class CharsetEncoderDecoderExample {

    public static void main(String[] args) throws CharacterCodingException {
        // Step 1: Create a char array
        char[] chars = {'H', 'e', 'l', 'l', 'o', ' ', '世', '界'};
        System.out.println("Original char array: " + new String(chars));

        // Step 2: Encode the char array using UTF-8 encoder
        CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        ByteBuffer encodedBytes = encoder.encode(CharBuffer.wrap(chars));

        // Convert ByteBuffer to byte array for printing
        byte[] byteArray = new byte[encodedBytes.remaining()];
        encodedBytes.get(byteArray);
        System.out.println("Encoded bytes (UTF-8): ");
        for (byte b : byteArray) {
            System.out.printf("%02x ", b); // Print bytes in hex format
        }
        System.out.println();

        // Step 3: Decode the encoded bytes using UTF-8 decoder
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        encodedBytes.flip(); // Reset position for decoding
        CharBuffer decodedChars = decoder.decode(encodedBytes);

        // Convert CharBuffer to char array for printing
        char[] charArray = new char[decodedChars.remaining()];
        decodedChars.get(charArray);
        System.out.println("Decoded char array: " + new String(charArray));
    }

}
