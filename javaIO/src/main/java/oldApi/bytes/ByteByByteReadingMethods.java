package oldApi.bytes;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ByteByByteReadingMethods {

    public static void main(String[] args) {
        String filename = "test.bin"; // Replace with your file

        // 1. FileInputStream (Basic Byte Reading)
        try (FileInputStream fis = new FileInputStream(filename)) {
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                // Process the byte (byteRead)
                // very important that byte here is the 0-255 digit
                // we can convert it to char using ASCII conversion, so we will able to read 32 letters
                // in case of the emoji, polish, chinese letters we have to use the different charset, encoding like UTF-8
                System.out.print((char) byteRead); // ASCII conversion
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("\n--- InputStreamReader ---");
        // 1b. Reading byte by byte text with charset
        String emojiFileUtf8 = "javaIO/src/main/java/oldApi/files/emoji.txt";
        try (FileInputStream fis = new FileInputStream(emojiFileUtf8);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            int charRead;

            /*
            Instead of returning raw bytes, isr.read() returns the Unicode code point of the decoded character as an int.
            Unicode code points can range from 0 to 1,114,111 (0x10FFFF in hexadecimal).
            This means that charRead can hold values far greater than 255.
            Some emojis are in the utf-16, and some old emoji are in the utf-8
            for example: 10084 = ❤️
             */

            //isr.read(new char[1000]) // read to array
            //isr.read(CharBuffer.allocate(1000)); // read to CharBuffer

            while ((charRead = isr.read()) != -1) {
                System.out.print((char) charRead); // Correct emoji output
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 1b. Efficient Reading byte by byte text with charset using BufferedReader
        try (FileInputStream fis = new FileInputStream(filename);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            int charRead;

            //br.read(new char[1000]) // read to array
            //br.read(CharBuffer.allocate(1000)); // read to CharBuffer
            //Stream<String> lines = br.lines();

            while ((charRead = br.read()) != -1) {
                System.out.print((char) charRead); // Correct emoji output
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n--- BufferedInputStream ---");

        // 2. BufferedInputStream (Efficient Byte Reading with Buffering byte by byte from buffer)
        try (FileInputStream fis = new FileInputStream(filename);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            int byteRead;
            while ((byteRead = bis.read()) != -1) {
                // Process the byte (byteRead)
                System.out.print(byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2a. BufferedInputStream (Most Efficient Reading using big chunks and array)
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             BufferedInputStream bis = new BufferedInputStream(fileInputStream)) {
            byte[] buffer = new byte[1024]; // buffer array for reading chunks
            int byteRead;

            while ((byteRead = bis.read(buffer)) != -1) {
                // very important that sometimes not all buffer is read !!
                for (int i = 0; i < byteRead; i++) {
                    System.out.print(byteRead);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n--- DataInputStream ---");

        // 3. DataInputStream (Reading Primitive Data Types Byte-by-Byte)
        try (FileInputStream fis = new FileInputStream(filename);
             DataInputStream dis = new DataInputStream(fis)) {
            int byteRead;
            while ((byteRead = dis.read()) != -1) {
                // Process the byte (byteRead)
                System.out.print(byteRead);
            }
        } catch (EOFException e) {
            // DataInputStream throws EOFException at the end of the file
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- ByteArrayInputStream ---");

        // 4. ByteArrayInputStream (Reading from a Byte Array)
        byte[] byteArray = {65, 66, 67, 68, 69}; // Example byte array
        try (ByteArrayInputStream bais = new ByteArrayInputStream(byteArray)) {
            int byteRead;
            while ((byteRead = bais.read()) != -1) {
                // Process the byte (byteRead)
                System.out.print((char) byteRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
