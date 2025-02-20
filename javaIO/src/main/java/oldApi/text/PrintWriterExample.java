package oldApi.text;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

// in order to print the formatted text, we need to just use the printf method :)
public class PrintWriterExample {
    public static void main(String[] args) {
        String file = "output.txt";

        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            System.out.println("Writing to UTF-8 file using PrintWriter");

            // Example 1: Writing a formatted string with numbers
            printWriter.printf("The value of PI is approximately %.2f%n", Math.PI);

            // Example 2: Writing a formatted string with integers and padding
            int number = 42;
            printWriter.printf("The answer is %04d%n", number); // Pads with leading zeros

            // Example 3: Writing a formatted string with dates
            LocalDate currentDate = LocalDate.now();
            printWriter.printf("Today's date is %tF%n", currentDate); // Date in YYYY-MM-DD format

            // Example 4: Writing a formatted string with multiple placeholders
            String name = "Alice";
            int age = 30;
            double salary = 50000.75;
            printWriter.printf("Name: %s, Age: %d, Salary: %,.2f%n", name, age, salary);

            // Example 5: Writing a formatted string with special characters
            printWriter.printf("Check this out: %c %s \u2764%n", '❤', "Unicode heart");

            // Example 6: Writing raw text (no formatting)
            printWriter.println("This is a raw text line without formatting.");

            System.out.println("Formatted text written successfully using PrintWriter!");
        }
    }
}
