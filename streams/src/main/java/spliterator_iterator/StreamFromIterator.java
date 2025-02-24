package spliterator_iterator;

import java.util.Arrays;
import java.util.Iterator;

public class StreamFromIterator {

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1,3,5,6,7};

        Iterator<Integer> iterator = Arrays.stream(integers).iterator();

        //var spliterator = Spliterators.spliteratorUnknownSize(iterator, )

        // StreamSupport.stream(spliterator)

    }
}
