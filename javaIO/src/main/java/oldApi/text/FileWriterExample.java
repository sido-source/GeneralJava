package oldApi.text;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

// in order to print the formatted text, we need to perform 2 steps:
// 1. create the formatted String object
// 2. pass the formatted String object to write method
public class FileWriterExample {
    public static void main(String[] args) {
        String file = "output.txt";
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, charset))) {
            System.out.println("Writing to UTF-8 file");

            // Example 1: Writing a formatted string with numbers
            // You can use String.format or printf to create formatted strings and then write them using BufferedWriter.
            String formattedString1 = String.format("The value of PI is approximately %.2f", Math.PI);
            bufferedWriter.write(formattedString1);
            bufferedWriter.newLine();

            // Example 2: Writing a formatted string with integers and padding
            int number = 42;
            String formattedString2 = String.format("The answer is %04d", number); // Pads with leading zeros
            bufferedWriter.write(formattedString2);
            bufferedWriter.newLine();

            // Example 3: Writing a formatted string with dates
            LocalDate currentDate = LocalDate.now();
            String formattedString3 = String.format("Today's date is %tF", currentDate); // Date in YYYY-MM-DD format
            bufferedWriter.write(formattedString3);
            bufferedWriter.newLine();

            // Example 4: Writing a formatted string with multiple placeholders
            String name = "Alice";
            int age = 30;
            double salary = 50000.75;
            String formattedString4 = String.format("Name: %s, Age: %d, Salary: %,.2f", name, age, salary);
            bufferedWriter.write(formattedString4);
            bufferedWriter.newLine();

            // Example 5: Writing a formatted string with special characters
            String formattedString5 = String.format("Check this out: %c %s \u2764", '❤', "Unicode heart");
            bufferedWriter.write(formattedString5);
            bufferedWriter.newLine();

            System.out.println("Formatted text written successfully!");
        } catch (IOException e) {
            System.out.println("Error while writing");
            e.printStackTrace();
        }
    }
}
