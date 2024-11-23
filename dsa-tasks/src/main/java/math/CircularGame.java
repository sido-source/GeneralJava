package math;

public class CircularGame {
    public static int findTheWinner(int n, int k) {
        int winner = 0;  // Start with 0-indexed base case (1 person left)

        // Iteratively calculate the safe position for each group size
        for (int i = 2; i <= n; i++) {
            winner = (winner + k) % i;
        }

        // Convert to 1-indexed result
        return winner + 1;
    }


    public static void main(String[] args) {
        int n = 5;
        int k = 2;
        System.out.println("The winner is player: " + findTheWinner(n, k));  // Output: 3
    }
}
