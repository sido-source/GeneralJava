package oldApi.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class BlockCode2 {

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        java.nio.file.Path path = Path.of(workingDir);

        try {
            Path.of(null);
        } catch (Exception e) {
            System.out.println("class of error : " + e.getClass());
        }

        System.out.println("Path: " + path);
        if (!java.nio.file.Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        if(java.nio.file.Files.isRegularFile(path)) {
            System.out.println("The path is the file");
        } else {
            System.out.println("The path is not the file");
        }

        if (java.nio.file.Files.isDirectory(path)) {
            System.out.println("The path is directory");
        } else {
            System.out.println("The path is a directory");
        }
    }
}
