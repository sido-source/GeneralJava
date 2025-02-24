package oldApi.text.conversion;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class CharsetConversionExample {
    public static void main(String[] args) {
        String inputFile = "input_iso8859.txt";
        String outputFile = "output_utf8.txt";

        try {
            // Step 1: Read bytes from the file (encoded in ISO-8859-1)
            byte[] bytes = Files.readAllBytes(Paths.get(inputFile));

            // Decode (bytes -> String/text)
            // Step 2: Decode bytes into a String using ISO-8859-1
            String content = new String(bytes, Charset.forName("ISO-8859-1"));

            // Encode (String/text -> bytes)
            // Step 3: Encode the String into bytes using UTF-8
            byte[] utf8Bytes = content.getBytes(StandardCharsets.UTF_8);

            // Step 4: Write the new bytes to a file (encoded in UTF-8)
            Files.write(Paths.get(outputFile), utf8Bytes);

            System.out.println("File converted from ISO-8859-1 to UTF-8 successfully!");
        } catch (IOException e) {
            System.out.println("Error during file conversion");
            e.printStackTrace();
        }
    }
}
