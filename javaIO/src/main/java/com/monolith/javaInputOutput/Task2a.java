package com.monolith.javaInputOutput;

import java.io.*;
import java.nio.charset.Charset;

public class Task2a {


    public static void main(String[] args) {

        // 1. Specify the inputPath
        String inputPath = "javaIO/src/main/resources/task2a_input.txt";
        Charset encode = java.nio.charset.Charset.forName("ISO-8859-8");
        String text = null;

        try (java.io.BufferedReader br_utf16 = new BufferedReader(new FileReader(inputPath, encode))) {
            text = br_utf16.readLine();
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }


        Charset decode = Charset.forName("windows-1250");
        String outputPath = "javaIO/src/main/resources/task2a_output.txt";
        try ( java.io.BufferedWriter br = new BufferedWriter(new FileWriter(outputPath, decode))) {
            br.write(text);
        } catch (IOException e) {
            System.out.println("Error during writing: " + e);
        }

        outputPath = "javaIO/src/main/resources/task2a_outputAfterConversionString.txt";
        try ( java.io.BufferedWriter br = new BufferedWriter(new FileWriter(outputPath, decode))) {
            byte[] bytes = text.getBytes(encode);
            String convertedString = new String(bytes, decode);
            br.write(convertedString);
        } catch (IOException e) {
            System.out.println("Error during writing: " + e);
        }
    }
}
