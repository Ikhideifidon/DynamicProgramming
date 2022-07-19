package com.github.ikhideifidon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.ikhideifidon.DynamicProgramming.*;
import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        System.out.println(climbingStaircase(15));
        System.out.println(fibonacci(40));
        System.out.println(fibonacciTopDown(40));
        System.out.println(fibonacciBottomUp(40));
        System.out.println(fibonacciIterative(40));
        System.out.println(gridTraveler(12, 13));
        System.out.println(gridTravelerMemoMap(12, 13));
        System.out.println(gridTravelerMemoArray(12, 13));
        System.out.println(gridTravelerBottomUp(12, 13));
        System.out.println(canSum(new int[] {7, 14, 3, 1, 2, 11, 2, 5, 4}, 30002));
        System.out.println(canSumMemoized(new int[] {2, 3, 5, 8}, 8));
        System.out.println(howSumMemoized(new int[]{7, 14}, 30002));
        System.out.println(howSumBruteForce(new int[]{7, 14}, 30));
        System.out.println(bestSumMemoized(new int[] {1, 2, 5, 25}, 1000));
        System.out.println(bestSumBruteForce(new int[] {1, 2, 5, 25}, 10));
        System.out.println(wordBreakBruteForce("abcdef", asList("ab", "abc", "cd", "def", "abcd")));
        System.out.println(countConstructBruteForce("abcdef", asList("ab", "abc", "cd", "abcdef", "def", "abcd")));
        System.out.println(countConstructMemoized("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new ArrayList<>(Arrays.asList(
                "e",  "ee", "eee", "eeee",  "eeeee")
        )));
        System.out.println(allConstruct("abcdef", asList("ab", "abc", "cd", "def", "abcd", "ef", "c")));




    }
}