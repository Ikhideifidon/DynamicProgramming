package com.github.ikhideifidon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.ikhideifidon.DynamicProgramming.*;
import static com.github.ikhideifidon.DynamicProgrammingTabulation.*;
import static com.github.ikhideifidon.Leetcode.*;
import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        //compute();
        tabulation();
        leetcode();
    }

    public static void compute() {
        System.out.println(climbingStaircase(15));
        System.out.println(fibonacci(40));
        System.out.println(fibonacciTopDown(40));
        System.out.println(fibonacciBottomUp(40));
        System.out.println(fibonacciIterative(40));
        System.out.println(gridTraveler(12, 13));
        System.out.println(gridTravelerMemoMap(12, 13));
        System.out.println(gridTravelerMemoArray(12, 13));
        System.out.println(gridTravelerBottomUp(12, 13));
        System.out.println(canSum(new int[]{7, 14, 3, 1, 2, 11, 2, 5, 4}, 30002));
        System.out.println(canSumMemoized(new int[]{2, 3, 5, 8}, 8));
        System.out.println(howSumMemoized(new int[]{7, 14}, 30002));
        System.out.println(howSumBruteForce(new int[]{7, 14}, 30));
        System.out.println(bestSumMemoized(new int[]{1, 2, 5, 25}, 1000));
        System.out.println(bestSumBruteForce(new int[]{1, 2, 5, 25}, 10));
        System.out.println(wordBreakBruteForce("abcdef", asList("ab", "abc", "cd", "def", "abcd")));
        System.out.println(countConstructBruteForce("abcdef", asList("ab", "abc", "cd", "abcdef", "def", "abcd")));
        System.out.println(countConstructMemoized("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new ArrayList<>(Arrays.asList(
                "e", "ee", "eee", "eeee", "eeeee")
        )));
        System.out.println(allConstructBruteForce("abcdef", asList("ab", "abc", "cd", "def", "abcd", "ef", "c")));
        System.out.println(allConstructMemoized("aaaaaaa", List.of("a", "aa", "aaa", "aaaa", "aaaaa")));
    }

    public static void tabulation() {
        // DynamicProgrammingTabulation
        System.out.println(fibonacciMemoized(50));
        System.out.println(gridTravelerMemoized(100, 100));
        System.out.println(canSumTabulation(7, new int[]{3, 5}));
        System.out.println(howSumTabulation(8, new int[]{2, 3, 5}));
        System.out.println(bestSumTabulation(100, new int[]{1, 2, 5, 25}));
        System.out.println(canConstructTabulation("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "abcdefg"}));
    }

    public static void leetcode() {
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChangeOptimal(new int[]{2}, 11));
        System.out.println(coinChanges(new int[]{3, 4}, 12));
        System.out.println(minimumCostTicket(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
        System.out.println(LISTopDown(new int[]{10, 9, 2, 7, 3, 5, 101, 18}));
        System.out.println(longestIncreasingSubsequenceBottomUp(new int[]{1, 2, 4, 3}));
        System.out.println(minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
        System.out.println(minimumTotalMemoized(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
        System.out.println(minPathSumTopDown(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(minimumPathSumBottomUp(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(calculateMinimumHPBruteForce(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -3}}));
        System.out.println(calculateMinimumHPMemoized(new int[][]{{-2,-3,3,-5,-10}}));
    }
}