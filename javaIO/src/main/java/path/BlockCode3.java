package path;

import java.nio.file.Path;

public class BlockCode3 {

    public static void main(String[] args) {
        String rootDir = System.getProperty("user.dir");
        java.nio.file.Path currecntDirPath = Path.of(rootDir, "javaIO", "src", "main", "path");
        java.nio.file.Path currecntFilePath = Path.of(rootDir, "javaIO", "src", "main", "path", "example.txt");



    }
}
