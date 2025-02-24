package oldApi.bytes;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ByteByByteWritingMethods {

    public static void main(String[] args) {
        String filename = "output.bin"; // Replace with your desired output file
        String textFilename = "output.txt"; // Replace with your desired output file

        // 1. FileOutputStream (Basic Byte Writing)
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] bytesToWrite = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33}; // "Hello World!" in ASCII
            fos.write(bytesToWrite);
            // fos.write(int b); // Write a single byte
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Bytes written to " + filename);

        // 1b. OutputStreamWriter (Writing text with charset)
        try (FileOutputStream fos = new FileOutputStream(textFilename);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            osw.write("Hello, 世界! ❤️"); // Writing Unicode text
            // osw.write(int c); // Write a single character
            // osw.write(char[] cbuf); // Write a character array
            // osw.write(String str); // Write a String
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Text written to " + textFilename);

        // 1b. BufferedWriter (Efficient writing text with charset)
        try (FileOutputStream fos = new FileOutputStream(textFilename, true); // Append
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("\nMore text!");
            // bw.write(int c); // Write a single character
            // bw.write(char[] cbuf); // Write a character array
            // bw.write(String str); // Write a String
            // bw.newLine(); // Write a newline character
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("More text appended to " + textFilename);

        // 2. BufferedOutputStream (Efficient Byte Writing with Buffering)
        try (FileOutputStream fos = new FileOutputStream(filename, true); // Append
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] moreBytes = {65, 66, 67, 68, 69}; // ABCDE
            bos.write(moreBytes);
            // bos.write(int b); // Write a single byte
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("More bytes appended to " + filename);

        // 3. DataOutputStream (Writing Primitive Data Types)
        try (FileOutputStream fos = new FileOutputStream(filename, true); // Append
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(12345);
            dos.writeDouble(3.14159);
            dos.writeUTF("UTF-8 String"); // Write a UTF-8 encoded String
            // dos.writeByte(int v); // Write a single byte
            // dos.writeBoolean(boolean v);
            // dos.writeChar(int v); // Write a char (16-bit)
            // dos.writeShort(int v); // Write a short (16-bit)
            // dos.writeLong(long v);
            // dos.writeFloat(float v);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Primitive data appended to " + filename);

        // 4. ByteArrayOutputStream (Writing to a Byte Array in Memory)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos)) {
            dos.writeInt(98765);
            dos.writeUTF("In-memory String");
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = baos.toByteArray();
        System.out.println("Data written to ByteArrayOutputStream: " + byteArray.length + " bytes");
    }

}
