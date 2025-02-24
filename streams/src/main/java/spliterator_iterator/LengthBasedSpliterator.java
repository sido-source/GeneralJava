package spliterator_iterator;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.List;
import java.util.stream.StreamSupport;

public class LengthBasedSpliterator implements Spliterator<String> {
    private final List<String> list;
    private int current = 0;
    private final int lengthThreshold;

    public LengthBasedSpliterator(List<String> list, int lengthThreshold) {
        this.list = list;
        this.lengthThreshold = lengthThreshold;
    }

    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
        if (current < list.size()) {
            String element = list.get(current++);
            if (element.length() <= lengthThreshold) {
                action.accept(element);
                return true;
            }
        }
        return false;
    }

    @Override
    public Spliterator<String> trySplit() {
        int currentSize = list.size() - current;
        if (currentSize < 2) {
            return null;
        }
        int splitPos = current + currentSize / 2;
        List<String> newList = list.subList(current, splitPos);
        current = splitPos;
        return new LengthBasedSpliterator(newList, lengthThreshold);
    }

    @Override
    public long estimateSize() {
        return list.size() - current;
    }

    @Override
    public int characteristics() {
        return SIZED | SUBSIZED | ORDERED;
    }



    public static void main(String[] args) {
        List<String> words = Arrays.asList("cat", "elephant", "tiger", "mouse", "hippopotamus");

        // Create a custom Spliterator
        LengthBasedSpliterator spliterator = new LengthBasedSpliterator(words, 5);

        // Create a stream using StreamSupport.stream
        StreamSupport.stream(spliterator, false).forEach(System.out::println);
    }
}

