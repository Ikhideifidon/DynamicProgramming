package com.github.ikhideifidon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgramming {

    // Climbing Staircase (Bottom-Up Approach)
    public static int climbingStaircase(int  n) {
        int[] result = new int[n + 1];
        if (n <= 2)
            return n;
        result[1] = 1;
        result[2] = 2;
        for (int i = 3; i <= n; i ++) {
            result[i] = result[i - 1] + result[i - 1];
        }
        return result[n];
    }

    // Fibonacci Series: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ... , f(n - 1) + f(n - 2)
    // Fibonacci (Top-Down) Without Memoization
    // Time: O(2^n)
    // Space: O(n)
    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Fibonacci (Top-Down) With Memoization
    // Time: O(n)
    // Space: O(n)
    private static int fibTopDown(int n, int[] memo) {
        if (n <= 1)
            return n;

        else if (memo[n] != 0)
            return memo[n];

        else
            return memo[n] = fibTopDown(n - 1, memo) + fibTopDown(n - 2, memo);
    }

    public static int fibonacciTopDown(int n) {
        return fibTopDown(n, new int[n + 1]);
    }

    // Fibonacci (Bottom-Up)----Tabulation
    // Time: O(n)
    // Space: O(n)
    public static int fibonacciBottomUp(int n) {
        if (n <= 1)
            return n;

        int[] memo = new int[n + 1];
        memo[1] = 1;

        if (memo[n] != 0)
            return memo[n];

        for (int i = 2; i <= n; i++)
            memo[i] = memo[i - 1] + memo[i - 2];
        return memo[n];
    }

    // Fibonacci (Iterative)
    // Time: O(n)
    // Space: O(1)
    public static int fibonacciIterative(int n) {
        if (n <= 1)
            return n;
        int first = 0;
        int second = 1;

        while (n-- > 1) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    // GridTraveler
    // Recursion without Memoization
    public static int gridTraveler(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        else if (m == 1 || n == 1)
            return 1;
        else if (m == 2 || n == 2)
            return Math.max(m, n);

        return gridTraveler(m - 1, n) + gridTraveler(m, n - 1);
    }

    // GridTraveler
    // Recursion with Memoization (HashMap) Top-Down
    private static int gridTravelerMemoizationMap(int m, int n, Map<String, Integer> memo) {
        if (m == 0 || n == 0)
            return 0;
        else if (m == 1 || n == 1)
            return 1;

        else if (m == 2 || n == 2)
            return Math.max(m, n);

        String key = m + ", " + n;
        String key2 = n + ", " + m;
        if (memo.containsKey(key) || memo.containsKey(key2))
            return memo.containsKey(key) ? memo.get(key) : memo.get(key2);

        memo.put(key, gridTravelerMemoizationMap(m - 1, n, memo) + gridTravelerMemoizationMap(m, n - 1, memo));
//        System.out.println(memo.entrySet());
        return memo.get(key);
    }

    public static int gridTravelerMemoMap(int m, int n) {
        return gridTravelerMemoizationMap(m, n, new HashMap<>());
    }


    // GridTraveler
    // Recursion with Memoization (2D-Array) Top-Down
    private static int gridTravelerMemoizationArray(int m, int n, int[][] memo) {
        if (m == 0 || n == 0)
            return 0;
        else if (m == 1 || n == 1)
            return 1;

        else if (m == 2 || n == 2)
            return Math.max(m, n);

       else if (memo[m][n] != 0)
           return memo[m][n];

       return memo[m][n] = gridTravelerMemoizationArray(m, n - 1, memo) + gridTravelerMemoizationArray(m - 1, n, memo);
    }

    public static int gridTravelerMemoArray(int m, int n) {
        return gridTravelerMemoizationArray(m, n, new int[m + 1][n + 1]);
    }

    // GridTraveler
    // Bottom-Up
    public static int gridTravelerBottomUp(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        else if (m == 1 || n == 1)
            return 1;

        else if (m == 2 || n == 2)
            return Math.max(m, n);

        int[][] memo = new int[m][n];
        for(int i = 0; i < m; i++)
            memo[i][0] = 1;

        for(int j = 0; j < n; j++)
            memo[0][j] = 1;

        for(int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
        }
        return memo[m - 1][n - 1];
    }

    // canSum
    // Write a function "canSum(nums, targetSum)" that takes in a targetSum sum and an array of
    // integers as arguments. The function should return a boolean indicating whether
    // it is possible to generate the targetSum using the num in nums.

    // You may use an element of the array as many times as needed.
                                                                               
    // You may assume that all input numbers are nonnegative.

    // Top-Down Recursion without Memoization
    public static boolean canSum(int[] nums, int targetSum) {
        if (targetSum < 0)
            return false;
        if (targetSum == 0)
            return true;

        for (int num : nums) {
            int remainder = targetSum - num;
            return canSum(nums, remainder);
        }
        return false;
    }

    // Top-Down Recursion with Memoization
    // Using Map. Note: Array can be used.
    private static boolean canSumMemo(int[] nums, int targetSum, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum))
            return memo.get(targetSum);

        if (targetSum < 0)
            return false;

        if (targetSum == 0)
            return true;

        for (int num : nums) {
            if (canSumMemo(nums, targetSum - num, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }

    public static boolean canSumMemoized(int[] nums, int targetSum) {
        return canSumMemo(nums, targetSum, new HashMap<>());
    }


    // How Sum Problem
    public static List<Integer> howSum(int[] nums, int targetSum) {
        // if (targetSum < minimum in nums)
        //    return null;
        if (targetSum < 0)
            return null;
        if (targetSum == 0)
            return new ArrayList<>();

        for (int num : nums) {
            int remainder = targetSum - num;
            List<Integer> result;
            result = howSum(nums, remainder);
            if (result != null)
                result.add(num);
            return result;


        }
        return null;
    }



}
