package com.monolith.javaInputOutput;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class Task3b {


    // use for small files
    public static void coppyFile(String sourcePath, String targetPath) {

        if ( sourcePath == null || targetPath == null) return;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcePath))) {

            bis.skip(2);
            byte [] text = bis.readAllBytes();


            try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetPath))) {
                bos.write(text);
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public class FileCopyWithBuffer {

        private static final int BUFFER_SIZE = 1024; // Buffer size can be adjusted

//        public static void copyFile(String sourcePath, String destinationPath) {
//            try (FileInputStream inputStream = new FileInputStream(sourcePath);
//                 FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
//
//                java.nio.ByteBuffer buffer;
////                inputStream.getChannel().truncate(2).read(buffer)
//                byte[] buffer = new byte[BUFFER_SIZE];
//                int bytesRead;
    //                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//                System.out.println("File copied successfully (buffered).");
//
//            } catch (IOException e) {
//                System.err.println("An error occurred during file copying: " + e.getMessage());
//            }
        }

        public static void main(String[] args) {
            String sourceFile = "source.bin"; // Path to your source file
            String destinationFile = "destination_with_buffer.bin"; // Path to your destination file
            //copyFile(sourceFile, destinationFile);
        }



     class FileCopyByteByByte {

        public static void copyFile(String sourcePath, String destinationPath) {
            try (FileInputStream inputStream = new FileInputStream(sourcePath);
                 FileOutputStream outputStream = new FileOutputStream(destinationPath)) {

                int byteData;
                while ((byteData = inputStream.read()) != -1) {
                    outputStream.write(byteData);
                }
                System.out.println("File copied successfully (byte-by-byte).");

            } catch (IOException e) {
                System.err.println("An error occurred during file copying: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            String sourceFile = "source.bin"; // Path to your source file
            String destinationFile = "destination_byte_by_byte.bin"; // Path to your destination file
            copyFile(sourceFile, destinationFile);
        }
    }

//    public static void main(String[] args) {
//        //coppyFile("javaIO/src/main/resources/Task3_source.txt", "javaIO/src/main/resources/Task3_target.txt");
//    }


     class FileCopyUsingNIO {

        public static void copyFile(String sourcePath, String destinationPath) {
            try (FileChannel sourceChannel = new FileInputStream(sourcePath).getChannel();
                 FileChannel destChannel = new FileOutputStream(destinationPath).getChannel()) {

                // Allocate a direct buffer for efficient native I/O operations
                ByteBuffer buffer = ByteBuffer.allocateDirect(8192); // 8KB buffer size

                while (sourceChannel.read(buffer) != -1) {
                    buffer.flip(); // Prepare buffer for writing
                    while (buffer.hasRemaining()) {
                        destChannel.write(buffer);
                    }
                    buffer.clear(); // Prepare buffer for reading
                }

                System.out.println("File copied successfully using NIO!");

            } catch (IOException e) {
                System.err.println("Error occurred during file copying: " + e.getMessage());
            }
        }

    }
}

