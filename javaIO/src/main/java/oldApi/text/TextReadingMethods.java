package oldApi.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextReadingMethods {

    public static void main(String[] args) {
        String filename = "input_text.txt"; // Replace with your input file

        // 1. FileReader (Basic Character Reading)
        try (FileReader fr = new FileReader(filename)) {
            int charRead;
            while ((charRead = fr.read()) != -1) {
                System.out.print((char) charRead);
                // fr.read(char[] cbuf); // Read characters into an array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n--- FileReader (Basic) ---");

        // 2. BufferedReader (Efficient Character Reading with Buffering)
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                // br.read(char[] cbuf); // Read characters into an array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--- BufferedReader (Efficient) ---");

        // 3. InputStreamReader (Reading text with a specific charset)
        try (FileInputStream fis = new FileInputStream(filename);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            int charRead;
            while ((charRead = br.read()) != -1) {
                System.out.print((char) charRead);
                // br.read(char[] cbuf); // Read characters into an array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n--- InputStreamReader (UTF-8) ---");

        // 4. StringReader (Reading from a String in Memory)
        String text = "In-memory text.\nAnother line.";
        try (StringReader sr = new StringReader(text);
             BufferedReader br = new BufferedReader(sr)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                // br.read(char[] cbuf); // Read characters into an array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--- StringReader (In-Memory) ---");

        // 5. LineNumberReader (Reading with Line Numbers)
        try (FileReader fr = new FileReader(filename);
             LineNumberReader lnr = new LineNumberReader(fr)) {
            String line;
            while ((line = lnr.readLine()) != null) {
                System.out.println(lnr.getLineNumber() + ": " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--- LineNumberReader (Line Numbers) ---");
    }

}
