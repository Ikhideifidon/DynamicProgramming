package com.github.ikhideifidon;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Bottom-Up Approach
 */
public class DynamicProgrammingTabulation {

    private static long fibonacci(int n, long[] memo) {
        if (memo[n] != 0)
            return memo[n];

        if (n <= 1)
            return n;

        long[] fibonacciTable = new long[n + 1];
        fibonacciTable[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacciTable[i] = fibonacciTable[i - 1] + fibonacciTable[i - 2];
            memo[i] = fibonacciTable[i];
        }
        return memo[n];
    }

    public static long fibonacciMemoized(int n) {
        return fibonacci(n, new long[n + 1]);
    }

    private static int gridTraveler(int m, int n, int[][] memo) {
        if (m == 0 || n == 0)
            return 0;

        if (memo[m - 1][n - 1] != 0)
            return memo[m - 1][n - 1];

        int[][] gridTable = new int[m][n];

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    gridTable[i][j] = 1;
                else
                    gridTable[i][j] = gridTable[i - 1][j] + gridTable[i][j - 1];
                memo[i][j] = gridTable[i][j];
            }
        }
        return memo[m - 1][n - 1];
    }

    public static int gridTravelerMemoized(int m, int n) {
        return gridTraveler(m, n, new int[m][n]);
    }

    // canSum
    public static boolean canSumTabulation(int target, int[] nums) {
        boolean[] canSumTable = new boolean[target + 1];
        canSumTable[0] = true;

        for (int i = 0; i <= target; i++) {
            if (canSumTable[i]) {
                for (int num : nums) {
                    if (i + num <= target) {
                        canSumTable[i + num] = true;
                        if (num == target || canSumTable[target])           // Short-circuiting
                            return true;
                    }
                }
            }
        }
        return canSumTable[target];
    }

    public static List<Integer> howSumTabulation(int target, int[] nums) {
        List<List<Integer>> howSumTable = new ArrayList<>(Collections.nCopies(target + 1, null));
        howSumTable.set(0, new ArrayList<>());

        for (int i = 0; i <= target; i++) {
            if (howSumTable.get(i) != null) {
                for (int num : nums) {
                    if (i + num <= target) {
                        howSumTable.set(i + num, new ArrayList<>(howSumTable.get(i)));
                        howSumTable.get(i + num).add(num);
                    }
                }
            }
        }
        System.out.println(howSumTable);
        return howSumTable.get(target);
    }

    public static List<Integer> bestSumTabulation(int target, int[] nums) {
        List<List<Integer>> bestSumTable = new ArrayList<>(Collections.nCopies(target + 1, null));
        bestSumTable.set(0, new ArrayList<>());

        for (int i = 0; i <= target; i++) {
            if (bestSumTable.get(i) != null) {
                for (int num : nums) {
                    if (i + num <= target) {

                        List<Integer> temp = new ArrayList<>(bestSumTable.get(i));
                        temp.add(num);

                        if (bestSumTable.get(i + num) == null || bestSumTable.get(i + num).size() > temp.size())
                            bestSumTable.set(i + num, temp);
                    }
                }
            }
        }
        return bestSumTable.get(target);
    }
}
