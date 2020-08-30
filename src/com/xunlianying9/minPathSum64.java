package com.xunlianying9;

// 第一遍
// 给定一个包含非负整数的m*n网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
public class minPathSum64 {

    /**
     * 动态规划（自底向上） + 原始数组
     * 时间复杂度:O() - 87.68%
     * 空间复杂度:O() - 69.45%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * 动态规划（自底向上） + 原始数组（第0行、第0列单独处理）
     * 时间复杂度:O() - 97.95%
     * 空间复杂度:O() - 5.82%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {


        return 0;
    }

    /**
     * 动态规划（自底向上） + 新建两个变量
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int minPathSum3(int[][] grid) {


        return 0;
    }
}
