import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelVsSequence {

    public java.time.Duration main(int lengthOfStream, int parallelismLevel, boolean parallel) {

        // Set the ForkJoinPool parallelism level to  threads
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(parallelismLevel));

        Random random = new Random();

        Instant start = Instant.now();

        long count = 0;

        if (parallel) {
            count = Stream
                    .generate(() -> random.nextInt(1000))
                    .parallel()
                    .limit(lengthOfStream)
                    .count();
        } else {
            count = Stream
                    .generate(() -> random.nextInt(1000))
                    .limit(lengthOfStream)
                    .count();
        }
        Instant end = Instant.now();

        // Calculate the duration
        Duration duration = Duration.between(start, end);

        // Print the elapsed time in milliseconds
        System.out.println("Elapsed time for parallel: " + duration.toMillis() + " ms");

        return duration;
    }



    @Test
    void smallStreamSize() {
        Duration sequentialDuration = main(10, 1, false);
        Duration parallelDuration = main(10, 1, true);

        // Assert that sequential processing is faster for small datasets
        assertTrue(sequentialDuration.compareTo(parallelDuration) < 0,
                "Sequential processing should be faster for small datasets.");
    }

    @Test
    void largeStreamSize() {
        Duration sequentialDuration = main(1_000_000, 4, false);
        Duration parallelDuration = main(1_000_000, 4, true);

        // Assert that parallel processing is faster for large datasets
        assertTrue(parallelDuration.compareTo(sequentialDuration) < 0,
                "Parallel processing should be faster for large datasets.");
    }

    @Test
    void transitionPoint() {
        int[] sizes = {1_000, 10_000, 100_000, 1_000_000};
        for (int size : sizes) {
            Duration sequentialDuration = main(size, 4, false);
            Duration parallelDuration = main(size, 4, true);

            if (size < 100_000) {  // Adjust this threshold based on your observations
                assertTrue(sequentialDuration.compareTo(parallelDuration) < 0,
                        "Sequential processing should be faster for size: " + size);
            } else {
                assertTrue(parallelDuration.compareTo(sequentialDuration) < 0,
                        "Parallel processing should be faster for size: " + size);
            }
        }
    }


}
