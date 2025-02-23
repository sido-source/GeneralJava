package newApi;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class NioReadingMethods {

    public static void main(String[] args) {
        String filePath = "large_file.txt";
        Path path = Paths.get(filePath);

        try {
            long fileSize = Files.size(path);
            if (fileSize < 1 * 1024 * 1024) { // <1MB
                nioEntireSmallFileRead(filePath);
            } else if (fileSize < 10 * 1024 * 1024) { // 1MB-10MB
                nioEntireMediumFileReadFunctional(filePath);
            } else { // >10MB
                nioFileChunkedRead(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioEntireSmallFileRead(String filePath) {
        Path path = Paths.get(filePath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Small File Content:\n" + content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void nioEntireMediumFileReadFunctional(String filePath) {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.stream()
                    .map(line -> {
                        String[] attributes = line.split(";");
                        return new MyObject(attributes[0], attributes[1], attributes[2]);
                    })
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void nioFileChunkedRead(String filePath) {
        int bufferSize = 4096; // 4KB buffer
        try (FileChannel readChannel = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
            int bytesRead;

            while ((bytesRead = readChannel.read(buffer)) > 0) {
                buffer.flip();
                byte[] data = new byte[bytesRead];
                buffer.get(data);
                System.out.print(new String(data, StandardCharsets.UTF_8));
                buffer.clear();
            }
            System.out.println("\nEnd of file reached");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioEntireFileRead(String filePath) {
        Path path = Paths.get(filePath);
        try (FileChannel channel = FileChannel.open(path)) {
            long bufferSize = Files.size(path);
            ByteBuffer buffer = ByteBuffer.allocate((int) bufferSize);
            channel.read(buffer);
            buffer.flip();
            System.out.println("Entire File Content:\n" + StandardCharsets.UTF_8.decode(buffer).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyObject {
        private final String attr1;
        private final String attr2;
        private final String attr3;

        public MyObject(String attr1, String attr2, String attr3) {
            this.attr1 = attr1;
            this.attr2 = attr2;
            this.attr3 = attr3;
        }

        @Override
        public String toString() {
            return "MyObject{" +
                    "attr1='" + attr1 + '\'' +
                    ", attr2='" + attr2 + '\'' +
                    ", attr3='" + attr3 + '\'' +
                    '}';
        }
    }
}