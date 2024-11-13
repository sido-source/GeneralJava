package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
The Josephus problem is a classic problem that can be solved efficiently with recursion or iteration. I'll walk you through the problem step-by-step, explaining each approach and decision in detail.

Problem Analysis
Input:

N: Total number of people in the circle.
k: Indicates that every kth person is eliminated.
Goal:

Find the position of the person who will survive after all others are eliminated.
Key Observations
The problem involves repeatedly removing every kth person in a circular manner until only one person remains.
For smaller values of N, the position of the survivor can be computed recursively, using insights from the smaller subproblems.
Recursive Approach to Solve Josephus Problem
The Josephus problem has a known recursive formula:

Base Case: If there is only one person, that person survives, so Josephus(1, k) = 0


 https://www.geeksforgeeks.org/josephus-problem/
 */
public class JosephusProblem {

    public static void main(String[] args) {
        int N = 8, k = 3;
        List<Integer> person = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        josephus(person, k, N);
    }


    static void josephus(List<Integer> person, int start, int k) {
        if ( person.size() == 1) {
            System.out.println("Survivor: " + person.get(0));
            return;
        }

        start = (start + k - 1) % person.size();
        person.remove(start);

        josephus(person, start, k);
    }


}
