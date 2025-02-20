package oldApi.pathFileDirectory;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Logger;

public class PathFileDirectory {

    private static final Logger LOGGER = Logger.getLogger(PathFileDirectory.class.getName());

    public static void main(String[] args) {



        java.nio.file.Path rootFolder = Path.of(System.getProperty("user.dir")); // absolute path
        System.out.println(rootFolder.toString());

        java.nio.file.Path currentDir = java.nio.file.Paths.get(".").toAbsolutePath(); // absolute path
        System.out.println(currentDir.toString());

        // java.nio.file.Path pathToNewFile = rootFolder.resolve("/javaIO/pathFileDirectory/someFile.txt");  first "/" make an error and result pathToNewFile = /javaIO/pathFileDirectory/someFile.txt
        java.nio.file.Path pathToNewFile = rootFolder.resolve("javaIO/pathFileDirectory/someFile.txt"); // relative path
        System.out.println("Directory, where is the file " +  pathToNewFile.getParent().toString());

        // check if directory exists
        if(!Files.exists(pathToNewFile.getParent())) {
            try {
                Files.createDirectory(pathToNewFile.getParent());
                LOGGER.info("Directory created: " + pathToNewFile.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //checks if file exists
        if (!Files.exists(pathToNewFile)) {
            try {
                //Files.copy()/transfer to
                //BasicFileAttributes bfa = Files.readAttributes(pathToNewFile, BasicFileAttributes.class);
                // todo: add some attributes
                Files.createFile(pathToNewFile);
                LOGGER.info("File created: " + pathToNewFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        try {

            // Get the file attributes
            BasicFileAttributes fileAttributes = Files.readAttributes(pathToNewFile, BasicFileAttributes.class);
            LOGGER.info("File size: " + fileAttributes.size());
            LOGGER.info("File creation time: " + fileAttributes.creationTime());
            LOGGER.info("File last modified time: " + fileAttributes.lastModifiedTime());

            // Write to the file
            String content = "Hello, World!\n";
            String newContent = "\n some \n thing . \n akakakak";
            Files.writeString(pathToNewFile, content);
            Files.write(pathToNewFile, newContent.getBytes(StandardCharsets.UTF_8));
            LOGGER.info("Content written to file: " + content);

            // Read from the file
            String readContent = Files.readString(pathToNewFile);
            LOGGER.info("Content read from file: " + readContent);

            // Copy the file to a new location
            Path newFilePath = pathToNewFile.getParent().resolve("example_copy.txt");
            Files.copy(pathToNewFile, newFilePath);
            LOGGER.info("File copied to: " + newFilePath);

            // Move the file to a new location
            Path movedFilePath = pathToNewFile.getParent().resolve("example_moved.txt");
            Files.move(pathToNewFile, movedFilePath);
            LOGGER.info("File moved to: " + movedFilePath);

            // Delete the file
            Files.delete(movedFilePath);
            LOGGER.info("File deleted: " + movedFilePath);
        } catch (IOException e) {
            LOGGER.severe("Error occurred: " + e.getMessage());
        }
    }

}
