package com.xunlianying6;

// 给定一个包含非负整数的m*n网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 思路：
// 递归+DFS
// 动态规划 + 状态压缩
// 动态规划的三步：状态初始化、状态方程、状态递归
public class MinPathSum64 {

    /***
     * 递归
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {

        return 0;
    }

    /***
     * 动态规划（自底向上）+ 直接修改原矩阵
     * 时间复杂度:O(n^2) - 87。87%
     * 空间复杂度:O(1) - 71。65%
     * 优点:
     * 缺点:
     * @param grid
     * @return
     */
    public int minPathSum11(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = grid[i][j];
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }

            }
        }
        return grid[m - 1][n - 1];
    }

    /***
     * 动态规划（自底向上）+ 直接修改原矩阵
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点: 相比于上一个方法，把第0行和第0列从两层for循环提取出来，for循环中减少了每次的ifelse判断。
     * 缺点:
     * @param grid
     * @return
     */
    public int minPathSum111(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }

    /***
     * 动态规划（自底向上）+ 二维数组
     * 时间复杂度:O(n*m) - 87.87%
     * 空间复杂度:O(n*m) - 50.47%
     * 优点:
     * 缺点:
     * @param grid
     * @return
     */
    public int minPathSum1111(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }
}
