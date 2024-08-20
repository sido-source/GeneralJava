package com.monolith.javaInputOutput;

import java.io.*;
import java.nio.charset.*;

public class Tasks2b {

    public static void main(String[] args) throws IOException {
        // Input file with ISO-8859-8 encoding
        File inputFile = new File("javaIO/src/main/resources/task2a_input.txt");
        // Output file with Windows-1250 encoding
        File outputFile = new File("javaIO/src/main/resources/Task2b_res.txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), Charset.forName("ISO-8859-8")))) {

            String line = reader.readLine();
            System.out.println(line);

            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Charset.forName("windows-1250")))) {
                bw.write(line);
            }

        } catch(IOException e) {
            System.out.println("error: " + e);
        }
    }
}
