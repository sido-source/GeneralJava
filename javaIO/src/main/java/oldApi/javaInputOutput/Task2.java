package oldApi.javaInputOutput;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task2 {

    public static void main(String[] args) {
        // Paths for input and output files
        String inputPath = "javaIO/src/main/resources/task2_windows1250.txt";
        String outputPath = "javaIO/src/main/resources/task2_utf8.txt";

        // Charset definitions
        Charset sourceCharset = Charset.forName("windows-1250");
        Charset targetCharset = StandardCharsets.UTF_8;

        // Writing the example text in Windows-1250 encoding
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputPath), sourceCharset))) {
            String text = "ĎA"; // Original text
            System.out.println(Arrays.toString(text.getBytes(Charset.forName("windows-1250"))));
            System.out.println(Arrays.toString(text.getBytes(Charset.forName("UTF-8"))));
            writer.write(new String(text.getBytes(sourceCharset)));
        } catch (IOException e) {
            System.out.println("Error writing Windows-1250 file.");
            e.printStackTrace();
        }

        // Reading from Windows-1250 file and converting to UTF-8
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), sourceCharset));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), targetCharset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing files.");
            e.printStackTrace();
        }


        String text = "Ď®A"; // Original text
        String text1 = new String();
        byte[] textBytes = text.getBytes(Charset.forName("windows-1252"));

// Convert bytes back to String in UTF-8
        String utf8Text = new String(textBytes, Charset.forName("UTF-8"));
        System.out.println("UTF-8 text: " + utf8Text);
    }
}




