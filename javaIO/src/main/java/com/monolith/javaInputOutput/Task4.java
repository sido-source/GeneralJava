package com.monolith.javaInputOutput;

import java.io.*;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task4 {

    /*
    Read and Write Binary Files
        1. Create a method that generates a byte array representing some data (e.g., an image or a serialized object).
        2. Write the byte array to a file using `FileOutputStream`.
        3. Read the byte array back from the file using `FileInputStream` and verify the content.
     */

    static String GENERATE_PATH = "javaIO/src/main/resources/task4_generatedFile.bin";

    public static java.io.ByteArrayInputStream generateSomeBinaryFile() {
        java.io.File file = new File(GENERATE_PATH);
        //java.io.ByteArrayOutputStream bais = new ByteArrayOutputStream();
        float[] someFloatData = new float[300];

        try(FileOutputStream fos = new FileOutputStream(file)) {

            for ( int i = 0 ; i < 1000 ; i++ ){
                fos.write(new java.util.Random().nextInt(1, Integer.MAX_VALUE));
            }


        } catch (java.io.IOException e) {
            System.out.println("error : " + e);
        }



        try (FileInputStream fis = new FileInputStream(GENERATE_PATH)){
            java.io.ByteArrayOutputStream bais = new ByteArrayOutputStream();
            fis.read(bais.toByteArray());


        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

        return null; //dp poprawy

    }
}
