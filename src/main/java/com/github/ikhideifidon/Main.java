package com.github.ikhideifidon;

import java.util.*;

import static com.github.ikhideifidon.BottomUp.*;
import static com.github.ikhideifidon.BottomUp.maximalSquareTabulation;
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
        System.out.println(calculateMinimumHPMemoized(new int[][]{{-2, -3, 3, -5, -10}}));
        char[][] square = new char[][]{
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        int[][] square2 = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}};
        System.out.println(maximalSquare(square));
        System.out.println(maximalSquareMemoized(square));


        // BottomUp
        System.out.println(maximalSquareTabulation(square));
        System.out.println(numSquare(50));
        System.out.println(numSquareMemoized(66));
        System.out.println(numSquareTabulation(30976 + 66));
        System.out.println(minCoinChange(new int[]{2}, 11));
        System.out.println(minCoinChangeMemoized(new int[]{1, 2, 5}, 11));
        System.out.println(findMaxFormBruteForce(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxFormMemoized(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxFormTabulation(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(minSteps(1210));
        System.out.println(minStepsMemoized(1210));
        System.out.println(minCostClimbingStairsBruteForce(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairsTabulation(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairsMemoized(new int[]{10, 5, 2, 8}));
        System.out.println(minCostClimbingStairsConstantSpace(new int[]{10, 5, 2, 8}));
        System.out.println(minCostClimbingStairsMemoized2(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(Integer.compare(5, 2));
        System.out.println(minRefuelStopsGreedyApproach(120, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        System.out.println("BruteForce");
        System.out.println(minRefuelStopsBruteForce(120, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        System.out.println(minRefuelStopsBruteForce(100, 35, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        System.out.println(minRefuelStopsBruteForce(100, 11, new int[][]{{10, 80}}));

        System.out.println("Greedy");
        System.out.println(minRefuelStopsGreedyApproach(120, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        System.out.println(minRefuelStopsGreedyApproach(100, 35, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        System.out.println(minRefuelStopsGreedyApproach(100, 11, new int[][]{{10, 80}}));
////        System.out.println(minRefuelStopsGreedyApproach(1000000000, 96590732, new int[][]{{3463138, 171226670},{
////                42514169, 871404},{45979689, 126681049},{80194728, 82824251},{113079470, 134585347},{186818655, 32057533},{
////                246663592, 142661201},{251592812, 144809488},{268781525, 23309186},{381405819, 155048427},{391116368, 164796170},{
////                433014492, 149057678},{433512702, 14527877},{446985018, 48126745},{476848695, 57439473},{525312691, 160081803},{
////                526247424, 169647083},{537136657, 5212455},{564887288, 110158354},{603529372, 196239200},{667093430, 82142416},{
////                682009873, 152653709},{819959710, 180003657},{877282592, 146414758},{926463574, 146211509}}));
////
        System.out.println(minRefuelStopsBruteForce(1000000000, 96590732, new int[][]{{3463138, 171226670},{
                42514169, 871404},{45979689, 126681049},{80194728, 82824251},{113079470, 134585347},{186818655, 32057533},{
                246663592, 142661201},{251592812, 144809488}}));

        System.out.println(Integer.MIN_VALUE);
    }
}