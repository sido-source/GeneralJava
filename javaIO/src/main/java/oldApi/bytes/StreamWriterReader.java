package oldApi.bytes;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamWriterReader {

    public static void main(String[] args) {
        Utf16WriteExample();
        Utf16ReadExample();
    }


    public static void Utf16WriteExample() {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("utf16.txt"), StandardCharsets.UTF_16)) {
            writer.write("A");    // Writes 2 bytes: 00 41 (or 41 00 depending on endianness)
            writer.write("😊");  // Writes surrogate pair (4 bytes in UTF-16)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Utf16ReadExample() {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream("utf16.txt"), StandardCharsets.UTF_16)) {
            int ch;
            while ((ch = reader.read()) != -1) {   // Reads 2 bytes per character
                System.out.print((char) ch);      // Correct character decoding
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
