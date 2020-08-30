package com.xunlianying6;

// 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
// 网格中的障碍物和空位置分别用 1 和 0 来表示。
// 说明：m 和 n 的值均不超过 100。
// 思路：
public class UniquePathsWithObstacles63 {

    /***
     * 递归（自顶向下） + 二维数组
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        return (int) helper1(obstacleGrid, 0, 0);
    }

    public static long helper1(int[][] obstacleGrid, int down, int right) {
        if (obstacleGrid[down][right] == 1)
            return 0;
        if (right == obstacleGrid[0].length - 1 && down == obstacleGrid.length - 1) {
            if (obstacleGrid[down][right] == 1)
                return 0;
            else
                return 1;
        }
        if (right == obstacleGrid[0].length - 1 || down == obstacleGrid.length - 1) {
            if (right == obstacleGrid[0].length - 1)
                return helper1(obstacleGrid, down + 1, right);
            return helper1(obstacleGrid, down, right + 1);
        }
        return helper1(obstacleGrid, down, right + 1) + helper1(obstacleGrid, down + 1, right);
    }

    /***
     * 动态规划（自底向上） + 二维数组
     * 时间复杂度:O(m*n) - 100%
     * 空间复杂度:O(m*n) - 55%
     * 优点:
     * 缺点:
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles11(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 动态规划（自底向上） + 一维数组
     * 时间复杂度:O(m*n) - 100%
     * 空间复杂度:O(m) - 68%
     * 优点:
     * 缺点:
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles111(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[m - 1];
    }
}
