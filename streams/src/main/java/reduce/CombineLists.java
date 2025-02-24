package reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombineLists {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<Integer> list3 = Arrays.asList(5, 6);
        List<List<Integer>> lists = Arrays.asList(list1, list2, list3);

        List<Integer> combinedList = lists.stream().reduce(new ArrayList<>(), (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
        System.out.println(combinedList);  // Output: [1, 2, 3, 4, 5, 6]

//        List<Integer> ovenNumbers = lists
//                .stream()
//                .flatMap(index -> index.stream())
//                .reduce(new ArrayList<>(), (l1, l2) -> {
//                    if (l2%2==0) l1.add(l2)
//                    return l1;
//                });

        System.out.println(combinedList);  // Output: [1, 2, 3, 4, 5, 6]

    }
}
