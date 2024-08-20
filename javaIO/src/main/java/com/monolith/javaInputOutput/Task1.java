package com.monolith.javaInputOutput;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Task1 {

    public static void main(String[] args) {
        Charset charset = StandardCharsets.UTF_8;
        String relativePath = "javaIO/src/main/resources/task1.txt"; // relative path, the catalog root is generalJava folder
        File file = new File(relativePath);

        try {
            // Ensure parent directories exist
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Created parent directories: " + parentDir.getAbsolutePath());
                } else {
                    System.out.println("Failed to create parent directories: " + parentDir.getAbsolutePath());
                }
            }

            // Create the file if it doesn't exist
            if (file.createNewFile()) {
                System.out.println("Created new file: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error during file creation");
            e.printStackTrace();
            return; // Exit if the file cannot be created
        }

        // Write to the file
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, charset))) {
            System.out.println("Writing to UTF-8 file");
            bufferedWriter.write("Hi it's my first I/O task \u2764");
            bufferedWriter.newLine(); // Newline for better formatting
            bufferedWriter.write(new char[]{'e', 's', 's', 'a'});
            // No explicit close needed, try-with-resources will close the writer
        } catch (IOException e) {
            System.out.println("Error while writing");
            e.printStackTrace();
        }

        maybeBetterApproachIfPathIsCorrect();
    }

    /*
    after I set some new 'path' i got :

    java.io.FileNotFoundException: src/main/resources/task1.txt (No such file or directory)
	at java.base/java.io.FileOutputStream.open0(Native Method)
	at java.base/java.io.FileOutputStream.open(FileOutputStream.java:289)
	at java.base/java.io.FileOutputStream.<init>(FileOutputStream.java:230)
	at java.base/java.io.FileOutputStream.<init>(FileOutputStream.java:118)
	at java.base/java.io.FileWriter.<init>(FileWriter.java:144)
	at com.monolith.javaInputOutput.Task1.main(Task1.java:16)


     */
    public static void maybeBetterApproachIfPathIsCorrect() {

        // path is quite problematic
        String path = "javaIO/src/main/resources/task12.txt";

        Charset charset = java.nio.charset.StandardCharsets.UTF_8;
        java.io.File file = new File(path);

        try (
                java.io.BufferedWriter bw = new BufferedWriter(new FileWriter(path, charset));
                java.io.BufferedReader br = new BufferedReader(new FileReader(path, charset))
        ) {
            // writing
            System.out.println("Writing to utf8 file");
            bw.write("Hi it's my first io task \u2764");
            bw.write("\n");
            bw.write("\n");
            bw.write(new char[]{'e', 's', 's', 'a'});
            bw.close(); // Close the writer explicitly

            //reading
            System.out.println("Reading utf-8 file");
            String line = br.readLine();
            int index = 1;
            while (line != null) {
                System.out.println("Line number " + index++ + ": " + line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("IO error occured");
            e.printStackTrace();
        }
    }
}
