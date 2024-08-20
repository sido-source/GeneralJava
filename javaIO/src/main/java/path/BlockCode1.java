package path;


import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class BlockCode1 {

        public static void main(String[] args) {
            // Using System.getProperty to get the current working directory
            String workingDir = System.getProperty("user.dir");
            System.out.println("Get root project absolute path using System.getProperty: " + workingDir);

            // Old way using java.io.File
            java.io.File filePath = new java.io.File("");
            System.out.println("\n[Using java.io.File]");
            System.out.println("Path (old way): " + filePath.getAbsolutePath());
            System.out.println("Does path exist? " + filePath.exists());
            System.out.println("Is path a directory? " + filePath.isDirectory());
            System.out.println("Is path a file? " + filePath.isFile());

            // Using java.nio.Path
            System.out.println("\n[Using java.nio.file.Path]");
            java.nio.file.Path nioPath = java.nio.file.Paths.get("");
            System.out.println("Root project using java.nio.file.Path: " + nioPath.toAbsolutePath());
            System.out.println("Get parent: " + nioPath.getParent());
            System.out.println("Get absolute path: " + nioPath.toAbsolutePath());
            System.out.println("Get file name: " + nioPath.getFileName());

            // Exploring FileSystem class and platform-specific details
            /*
            The Java FileSystem automatically adapts to the underlying operating system. However, it?s crucial to be aware of key differences:

            Windows: Case-insensitive file paths, uses \ as a separator.
            Unix/Linux: Case-sensitive file paths, uses / as a separator, more nuanced file
             */
            FileSystem defaultFileSystem = FileSystems.getDefault();
            System.out.println("\n[FileSystem Details]");
            System.out.println("Class: " + defaultFileSystem.getClass());
            System.out.println("Default separator: " + defaultFileSystem.getSeparator());
            System.out.println("Schema of the default system: " + defaultFileSystem.provider().getScheme());

            System.out.println("FileSystem class: " + nioPath.getFileSystem() + "\n\n");



            // Use an explicit path for clarity
            String explicitPath = "/Users/sajmi/Downloads/GeneralJava";

            // Using java.io.File
           filePath = new java.io.File(explicitPath);
            System.out.println("[Using java.io.File]");
            System.out.println("Path: " + filePath.getAbsolutePath());
            System.out.println("Does path exist? " + filePath.exists());
            System.out.println("Is path a directory? " + filePath.isDirectory());

            // Using java.nio.file.Path
            nioPath = java.nio.file.Paths.get(explicitPath);
            System.out.println("\n[Using java.nio.file.Path]");
            System.out.println("Root project using java.nio.file.Path: " + nioPath.toAbsolutePath());
            System.out.println("Get parent: " + nioPath.getParent());
            System.out.println("Get absolute path: " + nioPath.toAbsolutePath());
        }
}
