package com.github.ikhideifidon;

import java.util.*;

public class Leetcode {

    private static List<Integer> coinCombination(int[] coins, int amount, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(amount))
            return memo.get(amount);

        if (amount < 0 || coins == null)
            return null;

        if (amount == 0)
            return new ArrayList<>();

        List<Integer> shortestCoinsCombination = null;
        for (int coin : coins) {
            int remainderCoin = amount - coin;
            List<Integer> result = coinCombination(coins, remainderCoin, memo);
            if (result != null) {
                List<Integer> combination = new ArrayList<>(result);
                combination.add(coin);
                if (shortestCoinsCombination == null || combination.size() < shortestCoinsCombination.size())
                    shortestCoinsCombination = combination;
            }
        }
        memo.put(amount, shortestCoinsCombination);
        return shortestCoinsCombination;
    }

    public static int coinChange(int[] coins, int amount) {
        List<Integer> answer = coinCombination(coins, amount, new HashMap<>());
        return answer != null ? answer.size() : -1;
    }

    // Bottom-Up Approach
    public static int coinChangeOptimal(int[] coins, int amount) {
        int[] table = new int[amount + 1];
        Arrays.fill(table, amount + 1);             // Since output <= amount
        table[0] = 0;

        // Arrays.sort(coins)               // This will ensure early short-circuiting
        for (int i = 1; i <= amount; i++) {
            // [1, 2, 5]
            for (int coin : coins) {
                if ((i - coin) >= 0)
                    table[i] = Math.min(table[i], (1 + table[i - coin]));
                // else break;              // This will ensure early short-circuiting
            }
        }
        return table[amount] != amount + 1 ? table[amount] : -1;
    }

    public static int coinChanges(int[] coins, int amount) {
        return help(coins, amount, new HashMap<>());
    }

    // Top-Down With Memoization
    private static int help(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        if (memo.get(amount) != null) return memo.get(amount);

        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            int result = help(coins, amount - coin, memo);
            if (result >= 0 && result < min) {
                min = 1 + result;
            }
        }
        min = (min == Integer.MAX_VALUE) ? -1 : min;
        memo.put(amount, min);
        return min;
    }

    public static int minimumCostTicket(int[] days, int[] costs) {
        // days = [1,4,6,7,8,20], costs = [2,7,15]
        int n = days[days.length - 1];
        int[] table = new int[n + 1];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[0] = 0;
        int count = 1;
        int previousForDay = 0;
        int previousForWeek = 0;
        for (int day : days) {
            if (count <= 3) {
                table[day] = Math.min(table[day], costs[0] + previousForDay);
                previousForDay = table[day];
            } else if (count < 8) {
                table[day] = Math.min(table[day], costs[1] + previousForWeek);
                previousForWeek = (count - 3) * costs[0];
            } else {
                table[day] = Math.min(table[day], costs[2] + previousForWeek);
                int factor = Math.max((count - 8), 0);
                previousForWeek = factor * costs[0];
            }
            count++;
        }
        return table[n];
    }

    // Top-Down
    private static int longestIncreasingSubsequenceTopDown(int[] nums, int current, int previous, Map<Integer, Integer> memo) {
        if (memo.containsKey(current))
            return memo.get(current);

        int n = nums.length;
        if (current >=  n)
            return 0;
        int take = 0, notTake = longestIncreasingSubsequenceTopDown(nums, current + 1, previous, memo);
        if (nums[current] > previous)
            take = 1 + longestIncreasingSubsequenceTopDown(nums, current + 1, nums[current], memo);
        memo.put(current, Math.max(take, notTake));
        return memo.get(current);
    }

    public static int LISTopDown(int[] nums) {
        return longestIncreasingSubsequenceTopDown(nums, 0, 0, new HashMap<>());
    }

    // Bottom-Up
    public static int longestIncreasingSubsequenceBottomUp(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return n;
        int[] dp = new int[n];
        dp[n-1] = 1;
        int max = 1;
        // [1, 2, 4, 3]
        //           i
        //           j
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    if (dp[i] > max)
                        max = dp[i];
                }
            }
        }
        return max;
    }

    // Triangle
    // Bottom-Up
    public static int minimumTotal(List<List<Integer>> triangle) {
        // triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        /*          2                   i = 0
                 3     4                i = 1
              6     5     7             i = 2
           4     1     8     3          i = 3

        */
        int n = triangle.size();
        int[] dp = new int[n + 1];          // the plus one is from j+1
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {   // len of the subsequent arrays in triangle
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    // Triangle
    // Top-Down
    // Note: 2d array can be used in the place of Map
    private static int minimumTotalTopDown(List<List<Integer>> triangle, int row, int column, Map<String, Integer> memo) {
        if (row >= triangle.size())
            return 0;

        int result;
        String key = row + ", " + column;
        if (!memo.containsKey(key)) {
            result = Math.min(minimumTotalTopDown(triangle, row + 1, column, memo), minimumTotalTopDown(triangle, row + 1, column + 1, memo)) + triangle.get(row).get(column);
            memo.put(key, result);
        }
        return memo.get(key);
    }

    public static int minimumTotalMemoized(List<List<Integer>> triangle) {
        return minimumTotalTopDown(triangle, 0, 0, new HashMap<>());
    }

    // Minimum Path Sum
    // Top-Down
    private static int minimumPathSum(int[][] grid, int row, int column, int[][]memo) {
        int m = grid.length;
        int n = grid[0].length;
        if (row >= m && column >= n)
            return 0;                       // Since our given values are non-negative.

        else if (row == m - 1 && column == n - 1) {
            memo[row][column] = grid[row][column];
            return grid[row][column];
        }

        else if (memo[row][column] != 0)
            return memo[row][column];

        else if (row >= m - 1) {                // No more downward movement
            memo[row][column] = minimumPathSum(grid, row, column + 1, memo) + grid[row][column];
            return memo[row][column];
        }

        else if (column >= n - 1) {            // No more rightward movement
            memo[row][column] = minimumPathSum(grid, row + 1, column, memo) + grid[row][column];
            return memo[row][column];
        }
       else
            return memo[row][column] = Math.min(minimumPathSum(grid, row + 1, column, memo), minimumPathSum(grid, row, column + 1, memo)) + grid[row][column];
    }

    public static int minPathSumTopDown(int[][] grid) {
        return minimumPathSum(grid, 0, 0, new int[grid.length][grid[0].length]);
    }


    // Bottom-Up
    // Constant Extra Space.
    public static int minimumPathSumBottomUp(int[][] grid) {
        int m = grid.length;                // rows
        int n = grid[0].length;             // columns

        for (int i = 1; i < m; i++)
            grid[i][0] += grid[i - 1][0];

        for(int j = 1; j < n; j++)
            grid[0][j] += grid[0][j - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    // Dungeon Game
    // Recursion without Memoization (Top-Down).
    private static int calculateMinimumHP(int[][] dungeon, int row, int col) {
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;

        if (row > m || col > n) {                 // Out of bounds
            return Integer.MAX_VALUE;
        }

        if (row == m && col == n)                 // We have reached our destination.
            return dungeon[row][col] <= 0 ? -dungeon[row][col] + 1 : 1;

        // Try all possible paths to reach destination.
        int rightward = calculateMinimumHP(dungeon, row, col + 1);
        int downward = calculateMinimumHP(dungeon, row + 1, col);

        // Minimum of either values and the cost of the current cell
        int minimumHealthRequired = Math.min(rightward, downward) - dungeon[row][col];
        return minimumHealthRequired <= 0 ? 1 : minimumHealthRequired;
    }

    public static int calculateMinimumHPBruteForce(int[][] dungeon) {
        int row = 0;
        int col = 0;
        return calculateMinimumHP(dungeon, row, col);
    }


    // Dungeon Game
    // Recursion with Memoization (Top-Down).
    public static int calculateMinimumHPMemoized(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] memo = new int[m][n];

        for (int[] array : memo)
            Arrays.fill(array, Integer.MAX_VALUE);

        return helper(dungeon, 0, 0, memo);
    }

    private static int helper(int[][] dungeon, int row, int col, int[][] memo) {
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;

        if (row > m || col > n) {                 // Out of bounds
            return Integer.MAX_VALUE;
        }

        if (row == m && col == n)                 // We have reached our destination.
            return memo[row][col] = dungeon[row][col] <= 0 ? -dungeon[row][col] + 1 : 1;

        if (memo[row][col] != Integer.MAX_VALUE)
            return memo[row][col];

        // Try all possible paths to reach destination.
        int rightward = helper(dungeon, row, col + 1, memo);
        int downward = helper(dungeon, row + 1, col, memo);

        // Minimum of either values and the cost of the current cell
        int minimumHealthRequired = Math.min(rightward, downward) - dungeon[row][col];
        return memo[row][col] = minimumHealthRequired <= 0 ? 1 : minimumHealthRequired;
    }

    // Dungeon Game
    // Tabulation (Bottom-Up).



}
