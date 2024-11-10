package slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class TotalFruit {

    // 904. Fruit Into Baskets
        public int totalFruit(int[] fruits) {
            int maxFruits = 0;  // Maximum number of fruits we can collect
            int start = 0;  // Left end of the window
            Map<Integer, Integer> map = new HashMap<>();  // Map to store fruit types and their counts

            // Iterate over the fruits array
            for (int end = 0; end < fruits.length; end++) {
                // Add the current fruit to the map and increment its count
                map.put(fruits[end], map.getOrDefault(fruits[end], 0) + 1);

                // Shrink the window until we have only 2 types of fruits
                while (map.size() > 2) {
                    map.put(fruits[start], map.get(fruits[start]) - 1);
                    // Remove the fruit from the map if its count becomes zero
                    if (map.get(fruits[start]) == 0) {
                        map.remove(fruits[start]);
                    }
                    start++;  // Move the left end of the window to the right
                }

                // Update the maximum number of fruits collected
                maxFruits = Math.max(maxFruits, end - start + 1);
            }

            return maxFruits;
        }


        public static void main(String[] args) {
            TotalFruit fruit = new TotalFruit();
            int[] fruits = {0, 0, 1, 2, 2, 3, 3, 3, 4, 5, 5};
            System.out.println(fruit.totalFruit(fruits));  // Output: 8
        }
}
