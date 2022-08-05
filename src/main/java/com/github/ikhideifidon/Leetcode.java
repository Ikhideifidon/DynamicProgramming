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


    // Maximal Square
    // Top-Down Without Memoization
    private static int helper(char[][] matrix, int row, int col) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;

        // [0][0] ----> [m][n]
        if (row > m || col > n)
            return 0;

        int rightward = helper(matrix, row, col + 1);
        int downward = helper(matrix, row + 1, col);
        int diagonally = helper(matrix, row + 1, col + 1);

        if (matrix[row][col] == '1')
            return Math.min(rightward, Math.min(downward, diagonally)) + 1;
        return 0;
    }

    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int size;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    size = helper(matrix, i, j);
                    maxArea = Math.max(maxArea, size);
                }
            }
        }
        return maxArea * maxArea;
    }

    // Maximal Square
    // Top-Down With Memoization
    private static int helper(char[][] matrix, int row, int col, int[][] memo) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;

        // [0][0] ----> [m][n]
        if (row > m || col > n)
            return 0;

        if (memo[row][col] != 0)
            return memo[row][col];

        int rightward = helper(matrix, row, col + 1, memo);
        int downward = helper(matrix, row + 1, col, memo);
        int diagonally = helper(matrix, row + 1, col + 1, memo);

        if (matrix[row][col] == '1')
            memo[row][col] = Math.min(rightward, Math.min(downward, diagonally)) + 1;
        return memo[row][col];
    }

    public static int maximalSquareMemoized(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];

        int size;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    size = helper(matrix, i, j, memo);
                    maxArea = Math.max(maxArea, size);
                }
            }
        }
        return maxArea * maxArea;
    }

    // Perfect Square
    // Without Memoization
    public static int numSquare(int n) {
        if (n == 0) return 0;
        if (n < 0) return -1;

        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (i * i <= n) {
                int result = numSquare(n - (i * i));
                if (result >= 0 && result < minLength)
                    minLength = 1 + result;
            }
        }
        return minLength;
    }

    // Perfect Square
    // With Memoization
    private static int helper(int n, int[] memo) {
        if (n == 0) return 0;
        if (n < 0) return -1;

        if (memo[n] != 0)
            return memo[n];

        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (i * i <= n) {
                int result = helper(n - (i * i), memo);
                if (result >= 0 && result < minLength)
                    minLength = 1 + result;
            }
        }
        return memo[n] = minLength;
    }

    public static int numSquareMemoized(int n) {
        return helper(n, new int[n + 1]);
    }
    // Coin Change
    // Without Memoization
    public static int minCoinChange(int[] coins, int amount) {

        // Exit conditions
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int minLength = Integer.MAX_VALUE;
        for (int coin : coins) {
            int remainder = amount - coin;
            int result = minCoinChange(coins, remainder);
            if (result >= 0 && result < minLength)
                minLength = 1 + result;
        }
        return minLength != Integer.MAX_VALUE ? minLength : -1;
    }

    // Coin Change
    // With Memoization
    private static int helper(int[] coins, int amount, int[] memo) {

        // Exit conditions
        if (amount == 0) return memo[amount] = 0;
        if (amount < 0) return -1;

        if (memo[amount] != 0)
            return memo[amount];

        int minLength = Integer.MAX_VALUE;
        for (int coin : coins) {
            int remainder = amount - coin;
            int result = helper(coins, remainder, memo);
            if (result >= 0 && result < minLength)
                minLength = 1 + result;
        }
        memo[amount] = minLength;
        return minLength != Integer.MAX_VALUE ? minLength : -1;
    }

    public static int minCoinChangeMemoized(int[] coins, int amount) {
        return helper(coins, amount, new int[amount + 1]);
    }

    // Ones and Zeroes
    // Top-Down Without Memoization
    private static int helper(String[] strs, int m, int n, int index ) {
        int l = strs.length;

        // Base conditions
        if (m < 0 || n < 0) return -1;              // this means such a string is not a candidate
        if (index == l) return 0;                   // Such a string is a candidate

        int nZeros = 0;
        int nOnes = 0;
        for (int i = 0; i < strs[index].length(); i++) {
            if (strs[index].charAt(i) == '1')
                nOnes++;
            else nZeros++;
        }

        int include = 1 + helper(strs, m - nZeros, n - nOnes, index + 1);
        int exclude = helper(strs, m, n, index + 1);
        return Math.max(include, exclude);
    }

    public static int findMaxFormBruteForce(String[] strs, int m, int n) {
        int index = 0;
        return helper(strs, m, n, index);
    }

    // Ones and Zeroes
    // Top-Down With Memoization
    private static int helper(String[] strs, int m, int n, int index, int[][][] memo) {
        int l = strs.length;

        // Base conditions
        if (m < 0 || n < 0) return -1;              // this means such a string is not a candidate
        if (index == l) return 0;                   // Such a string is a candidate

        if (memo[m][n][index] != 0)                 // Memoized
            return memo[m][n][index];

        int nZeros = 0;
        int nOnes = 0;
        for (int i = 0; i < strs[index].length(); i++) {
            if (strs[index].charAt(i) == '1')
                nOnes++;
            else nZeros++;
        }

        int include = 1 + helper(strs, m - nZeros, n - nOnes, index + 1, memo);
        int exclude = helper(strs, m, n, index + 1, memo);
        return memo[m][n][index] = Math.max(include, exclude);
    }

    public static int findMaxFormMemoized(String[] strs, int m, int n) {
        int index = 0;
        int[][][] memo = new int[m + 1][n + 1][strs.length + 1];
        return helper(strs, m, n, index, memo);
    }

    // 2 Keys Keyboard
    // Recursion Without Memoization
    private static int helper(int n, int current, int afterLastCopy) {
        if (current > n)
            return 1_000_000;                    // Since n cannot exceed 1000

        if (current == n)
            return 0;

        int copy = 2 + helper(n, current + current, current);
        int paste = 1 + helper(n, current + afterLastCopy, afterLastCopy);

        return Math.min(copy, paste);
    }

    public static int minSteps(int n) {
        return n > 1 ? helper(n, 1, 1) + 1 : 0;
    }

    // 2 Keys Keyboard
    // Recursion With Memoization
    private static int helper(int n, int current, int afterLastCopy, int[][] memo) {
        if (current > n)
            return 1_000_000;                    // Since n cannot exceed 1000

        if (current == n)
            return 0;

        if (memo[current][afterLastCopy] != 0)
            return memo[current][afterLastCopy];

        int copy = 2 + helper(n, current + current, current, memo);
        int paste = 1 + helper(n, current + afterLastCopy, afterLastCopy, memo);

        return memo[current][afterLastCopy] = Math.min(copy, paste);
    }

    public static int minStepsMemoized(int n) {
        return n > 1 ? helper(n, 1, 1, new int[n + 1][n + 1]) + 1 : 0;
    }

    // Minimum Cost of Climbing Stairs
    // Recursion Without Memoization
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    private static int helper(int[] cost, int index) {
        int n = cost.length;
        if (n <= 1)
            return n == 0 ? 0 : cost[0];

        if (index < 0)
            return 0;

        if (index == 0)
            return cost[index];

        int oneStep = helper(cost, index - 1);
        int twoSteps = helper(cost, index - 2);
        return Math.min(oneStep, twoSteps) + cost[index];
    }

    public static int minCostClimbingStairsBruteForce(int[] cost) {
        int n = cost.length;
        return Math.min(helper(cost, n - 1), helper(cost, n - 2));
    }

    // Minimum Cost of Climbing Stairs
    // Recursion With Memoization
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private static int helper1(int[] cost, int index, int[] memo) {
        int n = cost.length;
        if (n <= 1)
            return n == 0 ? 0 : cost[0];

        if (index < 0)
            return 0;

        if (index == 0)
            return cost[index];

        if (memo[index] != 0)
            return memo[index];

        int oneStep = helper1(cost, index - 1, memo);
        int twoSteps = helper1(cost, index - 2, memo);
        return memo[index] = Math.min(oneStep, twoSteps) + cost[index];
    }

    public static int minCostClimbingStairsMemoized(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        return Math.min(helper1(cost, n - 1, memo), helper1(cost, n - 2, memo));
    }

    private static int helper2(int[] cost, int index, int[] memo) {
        int n = cost.length;
        if (n <= 1)
            return n == 0 ? 0 : cost[0];

        if (index > n - 1)
            return 0;

        if (index == n - 1)
            return cost[index];

        if (memo[index] != 0)
            return memo[index];

        int oneStep = helper2(cost, index + 1, memo);
        int twoSteps = helper2(cost, index + 2, memo);
        return memo[index] = Math.min(oneStep, twoSteps) + cost[index];
    }

    public static int minCostClimbingStairsMemoized2(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        return Math.min(helper2(cost, 0, memo), helper2(cost, 1, memo));
    }

}
