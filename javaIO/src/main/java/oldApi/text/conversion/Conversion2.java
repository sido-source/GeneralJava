package oldApi.text.conversion;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Conversion2 {

    public static void main(String[] args) {
        String inputPath = "javaIO/src/main/resources/task2_windows1250.txt";
        String outputPath = "javaIO/src/main/resources/task2_utf8.txt";

        Charset sourceCharset = Charset.forName("windows-1250");
        Charset targetCharset = StandardCharsets.UTF_8;

        // ✅ Writing Windows-1250 encoded file
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputPath), sourceCharset))) {
            String text = "ĎA"; // Original text
            System.out.println("Windows-1250 bytes: " + Arrays.toString(text.getBytes(sourceCharset)));
            System.out.println("UTF-8 bytes: " + Arrays.toString(text.getBytes(StandardCharsets.UTF_8)));
            writer.write(text);  // ✅ Fixed: no redundant conversions
        } catch (IOException e) {
            System.out.println("Error writing Windows-1250 file.");
            e.printStackTrace();
        }

        // ✅ Reading Windows-1250 and converting to UTF-8
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), sourceCharset));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), targetCharset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine(); // ✅ Ensuring proper line breaks
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing files.");
            e.printStackTrace();
        }

        // ⚡ Testing problematic charset conversion
        String text = "Ď®A"; // Contains ® which differs across charsets
        byte[] textBytes = text.getBytes(Charset.forName("windows-1252"));
        System.out.println("Windows-1252 bytes: " + Arrays.toString(textBytes));

        // ⚡ Incorrect conversion (illustrates the problem)
        String utf8Text = new String(textBytes, StandardCharsets.UTF_8);
        System.out.println("⚠ Incorrect UTF-8 text: " + utf8Text);

        // ✅ Correct approach: re-encode properly
        try {
            String correctedText = new String(textBytes, Charset.forName("windows-1252"));
            byte[] utf8Bytes = correctedText.getBytes(StandardCharsets.UTF_8);
            String properlyConverted = new String(utf8Bytes, StandardCharsets.UTF_8);
            System.out.println("✅ Correct UTF-8 text: " + properlyConverted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

