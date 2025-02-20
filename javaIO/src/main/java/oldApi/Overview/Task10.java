package oldApi.Overview;

import java.io.*;
import java.nio.file.Path;

public class Task10 {

    /*
    Binary files are essentially a sequence of bytes, where each byte can hold a value from 0 to 255. They can represent any kind of data, including ASCII text and numbers.
    To read complex characters (such as Unicode characters used in languages like Chinese and Polish), you need to interpret bytes using a character encoding (like UTF-8 or UTF-16).
     */
    public static void main(String[] args) {

        // for handling binary data
        InputStream inputStream;
        OutputStream outputStream;



        java.nio.file.Path firstBinaryFile = Path.of("binary1.bin");
        try {
            outputStream = new FileOutputStream(firstBinaryFile.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.write(256);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Here's what happens:
        //
        //The binary representation of 256 is 100000000 (9 bits)
        //When use the write() the jdk truncated to 8 bits, the value becomes 00000000 (8 bits)
    }
}
