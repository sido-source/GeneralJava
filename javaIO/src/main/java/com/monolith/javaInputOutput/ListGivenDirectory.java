package com.monolith.javaInputOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ListGivenDirectory {

    public static void main(String[] args) {

        java.nio.file.Path inputPath = Path.of(System.getProperty("user.dir"));
        java.util.stream.Stream<Path> listedFilesAndDirectories;

        if (!java.nio.file.Files.exists(inputPath)) {
            System.out.println("Given path does not exist. Exit");
            return;
        }
        try {
            listedFilesAndDirectories = java.nio.file.Files.list(inputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listedFilesAndDirectories.forEach(path -> {
            if (Files.isDirectory(path)) System.out.println("Found directory: " + path);
            if (Files.isRegularFile(path)) System.out.println("Found file: " + path);
        });
    }
}
