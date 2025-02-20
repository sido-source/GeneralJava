package oldApi.text;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/*
Question:
Why doesn't the output stream take any charset like UTF-8, but when we want to write to a file, it does?
For example, PrintWriter printWriter = new PrintWriter(System.out)—is it because streams are byte-oriented?
But what if we have a string with Chinese characters and another string with Polish characters, and we want to
display them in a file and then on the console? How would the console know how to display the specific characters?

Answer:
The behavior you're observing is due to the fundamental differences between byte streams and character streams in Java, as well as how the console and files handle character encoding.

-> Console output
When you use System.out (which is a PrintStream), it is a byte stream. It does not explicitly take a Charset because it relies on the default encoding of the system
(usually determined by the environment, such as the terminal or operating system settings).
The console (e.g., System.out) uses the default encoding of the environment (e.g., terminal or IDE). For example:

On Windows, the default encoding might be Windows-1252.
On Linux/macOS, the default encoding is often UTF-8.

If you print characters (e.g., Chinese or Polish) to the console, the console will use its default encoding to display them. If the console's encoding
does not support those characters, they may appear as garbage (e.g., ? or �).

How to set the default encoding in Intelij -> Go to File > Settings > Editor > File Encodings and set all encodings to UTF-8, UTF-16, windows...

-> File output

When writing to a file, you often need to specify the character encoding (e.g., UTF-8) because:

Files are stored as bytes, and the encoding determines how characters are converted to bytes.
Without specifying the encoding, the default system encoding is used, which can lead to inconsistencies (e.g., if the file is read on a system with a different default encoding).
 */
public class MultilingualExample {
    public static void main(String[] args) {
        String file = "output.txt";

        // Write to file with UTF-8 encoding
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            String chineseText = "你好，世界！"; // Chinese text
            String polishText = "Cześć, świat!"; // Polish text

            fileWriter.println("Chinese: " + chineseText);
            fileWriter.println("Polish: " + polishText);
            System.out.println("Text written to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

        // Print to console
        try (PrintWriter consoleWriter = new PrintWriter(System.out, true)) {
            String chineseText = "你好，世界！"; // Chinese text
            String polishText = "Cześć, świat!"; // Polish text

            consoleWriter.println("Chinese: " + chineseText);
            consoleWriter.println("Polish: " + polishText);
        }
    }
}