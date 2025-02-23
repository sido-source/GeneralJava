package oldApi.text.conversion;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StreamConversionExample {
    public static void main(String[] args) {
        String inputFile = "input_iso8859.txt";
        String outputFile = "output_utf8.txt";

        try (
                // Step 1: Read bytes from the file and decode using ISO-8859-1
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(inputFile), Charset.forName("ISO-8859-1"));

                // Step 2: Write characters to the file and encode using UTF-8
                OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(outputFile), StandardCharsets.UTF_8)
        ) {
            // Step 3: Read characters and write them to the output file
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }

            System.out.println("File converted from ISO-8859-1 to UTF-8 successfully!");
        } catch (IOException e) {
            System.out.println("Error during file conversion");
            e.printStackTrace();
        }
    }
}
