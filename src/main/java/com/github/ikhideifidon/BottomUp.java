package com.github.ikhideifidon;

import java.util.Arrays;

public class BottomUp {
    // Just some LeetCode Questions
    // Always remember the rule of Tabulation; Draw some good examples before coding.

    // Maximal Square
    // Tme Complexity: O(mn)
    // Space Complexity: O(mn)
    // m = length of 2d array and n = array[0].length. The 2d array is not a jagged array.
    public static int maximalSquareTabulation(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // [0][0] ---> [m][n]
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    maxLength = Math.max(dp[i][j], maxLength);
                }
            }
        }
        return maxLength * maxLength;
    }

    // Perfect Squares Tabulation
    public static int numSquareTabulation(int n) {
        int[] table = new int[n + 1];
        Arrays.fill(table, 4);             // Lagrange's four-square theorem. maximum output <= 4
        table[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int square = j * j;
                if (square <= n) {
                    if ((i - square) >= 0)
                        table[i] = Math.min(table[i], (1 + table[i - square]));
                } else break;
            }
        }
        return table[n];
    }

    // Coin Change
    public static int coinChangeTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);                // Maximum amount in dp <= amount
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if ((i - coin) >= 0)
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    // Zeros and Ones
    public static int findMaxFormTabulation(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int nZeros = 0;
            int nOnes = 0;
            for (int k = 0; k < str.length(); k++) {
                if (str.charAt(k) == '1')
                    nOnes++;
                else nZeros++;
            }

            for (int i = m; i >= nZeros; i--) {
                for (int j = n; j >= nOnes; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - nZeros][j - nOnes] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // 2 Keys Keyboard
    // Math
    // Time Complexity: O(N^1/2)
    // Space Complexity: O(1)
    public static int minStepsMaths(int n) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                result += i;
                n /= i;
            }
        }
        return result;
    }

    // Minimum Cost Climbing Stairs
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int minCostClimbingStairsTabulation(int[] cost) {
        int n = cost.length;
        if (n <= 1)
            return n == 0 ? 0 : cost[0];

        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int oneStep = dp[i - 1] + cost[i - 1];
            int twoSteps = dp[i - 2] + cost[i -2];
            dp[i] = Math.min(oneStep, twoSteps);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    // Minimum Cost Climbing Stairs
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int minCostClimbingStairsConstantSpace(int[] cost) {
        int n = cost.length;
        if (n <= 1)
            return n == 0 ? 0 : cost[0];

        int first = cost[0];
        int second = cost[1];

        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(first, second);
            first = second;
            second = current;
        }
        return Math.min(first, second);
    }


}
