package oldApi.javaInputOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GetFilesWithGivenExtension {

    // approach 1
    // #filter #stream #Files #Path
    public static void main(String[] args) {

        String extension = ".bin";
        String contentRootDir = System.getProperty("user.dir");
        Path inputPath = Path.of(contentRootDir, "javaIO", "src", "main", "resources");

        if (!Files.exists(inputPath)) {
            System.out.println("Given path: " + inputPath + " does not exist.");
            return;
        }

        try (Stream<Path> paths = Files.list(inputPath)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(extension))
                    .forEach(path -> System.out.println("Found file: " + path));
        } catch (IOException e) {
            System.err.println("An error occurred while listing files: " + e.getMessage());
        }
    }


    // approach 2
    // #substring #String #Path.of

    // alternatively
    public static void main1() {
        String extension = ".bin";
        String contentRootDir = System.getProperty("user.dir");
        Path inputPath = Path.of(contentRootDir, "javaIO", "src", "main", "resources");

        if (!Files.exists(inputPath)) {
            System.out.println("Given path: " + inputPath + " does not exist.");
            return;
        }

        try (Stream<Path> paths = Files.list(inputPath)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileExtension = getFileExtension(path.toString());
                        String result = switch (fileExtension) {
                            case ".bin" -> "Found binary file: " + path;
                            case ".txt" -> "Found text file: " + path;
                            default -> "Found other file type: " + path;
                        };
                        System.out.println(result);
                    });
        } catch (IOException e) {
            System.err.println("An error occurred while listing files: " + e.getMessage());
        }
    }

    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return (lastIndex != -1) ? fileName.substring(lastIndex) : "";
    }


    // approach 3
    // #filter #regex
    public void main3() {
        String extension = ".bin";
        String contentRootDir = System.getProperty("user.dir");
        Path inputPath = Path.of(contentRootDir, "javaIO", "src", "main", "resources");

        if (!Files.exists(inputPath)) {
            System.out.println("Given path: " + inputPath + " does not exist.");
            return;
        }

        // Create a regex pattern to match the desired file extension
        /*

        The pattern .*\\.bin$ is used to match any file name that ends with .bin.
        .* matches any sequence of characters.
        \\. matches the literal dot (.), which needs to be escaped in regex with \\.
        bin$ ensures that .bin is at the end of the string.

         */
        java.util.regex.Pattern pattern = Pattern.compile(".*\\" + extension + "$");

        try (Stream<Path> paths = Files.list(inputPath)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> pattern.matcher(path.getFileName().toString()).matches())
                    .forEach(path -> System.out.println("Found file: " + path));
        } catch (IOException e) {
            System.err.println("An error occurred while listing files: " + e.getMessage());
        }

    }
    }
