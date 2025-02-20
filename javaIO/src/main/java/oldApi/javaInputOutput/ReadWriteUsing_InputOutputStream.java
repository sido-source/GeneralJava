package oldApi.javaInputOutput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadWriteUsing_InputOutputStream {

    // in order to use input/outputStream we have to have the single byte or the array of bytes !!!

    public static void main(String[] args) {

        //reading and writing from keyboard

        InputStream in = System.in;

        // PrintWriter = System.out write only characters
        OutputStream out = System.out;


        try {
            //value byte is returned as an int in the range 0 to 255.
            int singlebyte = in.read();

            System.out.println("it works using System.out.println, but doesnt using OutputStream.write(singlebyte) " + singlebyte);
            // convert byte to character String
            String stringfrombyte = String.valueOf(singlebyte);

            //todo: why it wrong convert
            String byteValueAsString = "Read byte: " + singlebyte;

            // Write the string representation to the output stream
            out.write(byteValueAsString.getBytes());

            byte[] byteForInput = new byte[10];

            in.read(byteForInput);

            //
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }



    /*
    Java’s byte type is an 8-bit signed integer with a range of -128 to 127.
    Unsigned Interpretation:
    To interpret bytes as unsigned values (0 to 255), you use the bitwise & 0xFF operation.
     */
    @Test
    public void testReadByteValues() throws IOException {
        // Test single-byte input within the range 0-255
        byte[] byteValues = {0, -1, 127, (byte) 255}; // Max byte value 255 is represented as (byte) 255
        InputStream in = new ByteArrayInputStream(byteValues);

        for (byte expected : byteValues) {
            int result = in.read();

            System.out.println("result: " + result);
            //convert to unsign operation
            assertEquals(expected & 0xFF, result, "The byte value read should match the input byte.");
        }

        // Ensure that end of stream is reached
        assertEquals(-1, in.read(), "End of stream should return -1.");
    }


    /*
    Integer 256 in Binary:
    256 in binary is 00000001 00000000.
    This needs two bytes: 00000001 (high byte) and 00000000 (low byte).
     */
    @Test
    public void testReadMultiByteValue() throws IOException {
        int value = 256;

        // Convert the integer to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write((value >> 8) & 0xFF); // Write the high byte
        byteArrayOutputStream.write(value & 0xFF); // Write the low byte

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // Create an InputStream from the byte array
        InputStream inputStream = new ByteArrayInputStream(byteArray);

        // Read bytes from the InputStream
        int highByte = inputStream.read();
        System.out.println("highByte: " + highByte);

        int lowByte = inputStream.read();
        System.out.println("highByte: " + lowByte);

        // Combine bytes to reconstruct the integer value
        int reconstructedValue = (highByte << 8) | lowByte;
        System.out.println("original value: " + reconstructedValue);

        // Assert that the reconstructed value is equal to the original value
        assertEquals(value, reconstructedValue, "The reconstructed value should match the original value.");
    }

    @Test
    public void testReadSingleCharacter() throws IOException {
        // Test input with a single character
        String charInput = "x";
        InputStream in = new ByteArrayInputStream(charInput.getBytes());

        int result = in.read();
        assertEquals('x', result, "The character read should be 'x'.");

        // Ensure that end of stream is reached
        assertEquals(-1, in.read(), "End of stream should return -1.");
    }

    @ParameterizedTest(name = "Testing character: {0}")
    @MethodSource("someInput")
    void testSomeMultiByteCharacter(char character) throws IOException {
        // Convert character to a byte array
        byte[] byteArray = Character.toString(character).getBytes(StandardCharsets.UTF_8);

        // Create an InputStream from the byte array
        InputStream inputStream = new ByteArrayInputStream(byteArray);

        // Read bytes from the InputStream
        int bytesRead;
        int byteCount = 0;
        StringBuilder output = new StringBuilder();

        while ((bytesRead = inputStream.read()) != -1) {
            byteCount++;
            output.append((char) bytesRead);
        }

        // Print results
        System.out.println("Character: " + character);
        System.out.println("Read bytes: " + byteCount);
        System.out.println("Output: " + output.toString());

        // Check if the character is single or multi-byte
        if (Character.toString(character).getBytes(StandardCharsets.UTF_8).length > 1) {
            System.out.println("This is a multi-byte character.");
        } else {
            System.out.println("This is a single-byte character.");
        }

        // Assertion (for demonstration purposes, adjust as needed)
        assertTrue(byteCount > 0, "At least one byte should be read.");
    }

    // Provide a stream of test characters
    public static Stream<Character> someInput() {
        return Stream.of('ł', 'x', 'ó', 'r');
    }


}
