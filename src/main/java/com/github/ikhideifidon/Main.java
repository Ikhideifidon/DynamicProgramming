package com.github.ikhideifidon;

import static com.github.ikhideifidon.DynamicProgramming.*;

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
        System.out.println(canSumMemoized(new int[] {2, 3, 5}, 8));
        System.out.println(howSum(new int[] {3, 5}, 8));
    }
}