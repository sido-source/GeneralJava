package oldApi.text;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TextWritingMethods {

    public static void main(String[] args) {
        String filename = "output_text.txt"; // Replace with your desired output file

        // 1. FileWriter (Basic Character Writing)
        try (FileWriter fw = new FileWriter(filename, Charset.forName("UTF-8"))) {
            fw.write("Hello, Text World!");
            // fw.write(int c); // Write a single character
            // fw.write(char[] cbuf); // Write a character array
            // fw.write(String str); // Write a String
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Text written to " + filename);

        // 2. BufferedWriter (Efficient Character Writing with Buffering)
        try (FileWriter fw = new FileWriter(filename, Charset.defaultCharset(), true); // Append
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("\nMore text with buffering!");
            // bw.write(int c); // Write a single character
            // bw.write(char[] cbuf); // Write a character array
            // bw.write(String str); // Write a String
            bw.newLine(); // Write a platform-specific newline
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("More text appended to " + filename);

        // 3. PrintWriter (Formatted Text Writing)
        try (FileWriter fw = new FileWriter(filename,StandardCharsets.UTF_8, true); // Append
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("Formatted text: " + 123 + " " + 3.14);
            pw.printf("Formatted text using printf: %s %d %f\n", "Example", 456, 2.718);
            // pw.print(Object obj); // Print an object
            // pw.print(String s); // Print a String
            // pw.println(); // Write a newline
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Formatted text appended to " + filename);

        // 4. OutputStreamWriter (Writing text with a specific charset)
        try (FileOutputStream fos = new FileOutputStream(filename, true); // Append
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("Unicode text: 世界 ❤️"); // Writing Unicode text with UTF-8
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Unicode text appended to " + filename);

        // 5. StringWriter (Writing to a String in Memory)
        StringWriter sw = new StringWriter();
        try (BufferedWriter bw = new BufferedWriter(sw)) {
            bw.write("In-memory text.");
            bw.newLine();
            bw.write("Another line.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String writtenString = sw.toString();
        System.out.println("Data written to StringWriter: " + writtenString);
    }
}
