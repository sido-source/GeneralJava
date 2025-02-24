package bigCompanies.interview2;

import java.util.ArrayList;
import java.util.List;

public class BirdNest {
    public static List<Integer> solution(int[] forest, int bird) {
        List<Integer> foundSticks = new ArrayList<>();
        int totalLength = 0;
        int direction = 1; // Start by moving right (1 = right, -1 = left)

        while (totalLength < 100) {
            if (direction == 1) { // Moving to the right
                for (int i = bird + 1; i < forest.length; i++) {
                    if (forest[i] > 0) {
                        foundSticks.add(i);
                        totalLength += forest[i];
                        bird = i;
                        break;
                    }
                }
            } else { // Moving to the left
                for (int i = bird - 1; i >= 0; i--) {
                    if (forest[i] > 0) {
                        foundSticks.add(i);
                        totalLength += forest[i];
                        bird = i;
                        break;
                    }
                }
            }
            // Change direction
            direction *= -1;
        }

        return foundSticks;
    }

    public static void main(String[] args) {
        int[] forest = {25, 0, 50, 0, 0, 0, 0, 15, 0, 0, 45};
        int bird = 4;

        List<Integer> result = solution(forest, bird);
        System.out.println(result);
    }
}

