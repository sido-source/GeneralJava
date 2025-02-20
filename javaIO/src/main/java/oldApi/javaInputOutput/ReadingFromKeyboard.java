package oldApi.javaInputOutput;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadingFromKeyboard {

    public static void main(String[] args) {

        // Reading from System.in gives us BYTES!!
        InputStream in = System.in;


        List<String> lines = new ArrayList<>();

        // Convert BYTES to characters using InputStreamReader
        // (which is the bridge between bytes and characters)
        // and wrap with BufferedReader for efficient reading
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            // Try to write some Polish or Chinese letters: ?u??, ????/????
            String line;
            while ((line = br.readLine()) != null) {

                // To skip current iteration and go to next input if "next" is typed
                if (line.equals("next")) {
                    System.out.println("Skipping to next input...");
                    // continue; doesnt work
                    break;
                }

                lines.add(line);
                System.out.println("You entered: " + line);
            }
        } catch (IOException e) {
            System.out.println("Found the IO error: " + e.getMessage());
        }

        // Calling the helper method
        // helper(System.in); doesnt work because the System.in was closed once the Buffered was close in try catch ressource loop

        helper(lines);
    }

    private static void helper(List<String> in) {

        // Create InputStreamReader with default charset
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("Default charset for InputStream is set to: " + defaultCharset);

        // Change the InputStreamReader to use US_ASCII charset
        System.out.println("Default charset for InputStream is set to: " + Charset.defaultCharset());

        // Iterate over the lines and attempt to read them as US_ASCII
        for (String line : in) {
            // Reinterpret each line using the US_ASCII charset
            byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
            String asciiLine = new String(bytes, StandardCharsets.US_ASCII);

            System.out.println("Original: " + line);
            System.out.println("ASCII interpreted: " + asciiLine);
        }

    }
}


/*
Default Encoding:
Java's InputStreamReader uses the default charset of the platform unless explicitly specified.
The default charset depends on the locale and operating system settings. For example, it might be UTF-8 on many Unix-like systems or a locale-specific charset on Windows.
 */