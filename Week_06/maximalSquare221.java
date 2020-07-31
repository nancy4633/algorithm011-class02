package com.xunlianying6.zuoye1;

// 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
// 思路：
// 这里要求的正方形，长宽一定要相等吗？
// 递归+DFS
// 动态规划+状态压缩
public class maximalSquare221 {

    /**
     * 递归+DFS
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix) {
        return 0;
    }

    /**
     * 动态规划 + 二维数组
     * 时间复杂度:O(m*n) - 82.66%
     * 空间复杂度:O(m*n) - 78.50%
     * 优点:
     * 缺点:
     *
     * @param matrix
     * @return
     */
    public int maximalSquare11(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;
        int[][] dp = new int[height + 1][width + 1];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 动态规划 + 状态压缩
     * 时间复杂度:O(m*n) - 82.66%
     * 空间复杂度:O(m) - 12.15%
     * 优点:
     * 缺点:
     *
     * @param matrix
     * @return
     */
    public int maximalSquare111(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;
        int[] dp = new int[width + 1];
        for (char[] chars : matrix) {
            int northwest = 0; // 个人建议放在这里声明，而非循环体外
            for (int col = 0; col < width; col++) {
                int nextNorthwest = dp[col + 1];
                if (chars[col] == '1') {
                    dp[col + 1] = Math.min(Math.min(dp[col], dp[col + 1]), northwest) + 1;
                    maxSide = Math.max(maxSide, dp[col + 1]);
                } else {
                    dp[col + 1] = 0;
                }
                northwest = nextNorthwest;
            }
        }
        return maxSide * maxSide;
    }
}
