package com.xunlianying6.zuoye1;

// 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
// 思路：
// 这里要求的正方形，长宽一定要相等吗？
// 递归+DFS
// 动态规划+状态压缩
public class maximalSquare221 {

    /**
     * 递归+DFS
     * 时间复杂度:O() - 100%
     * 空间复杂度:O() - 22。43%
     * 优点:
     * 缺点:
     *
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix) {
        n = matrix.length;
        if (n == 0) return 0;
        m = matrix[0].length;
        if (m == 0) return 0;
        return dfs(1, matrix, 0);
    }

    int n, m;

    public int dfs(int maLen, char[][] matrix, int k) {
        for (int i = k; i <= n - maLen; i++) {
            for (int j = 0; j <= m - maLen; j++) {
                if (judge(maLen, matrix, i, j))
                    return Math.max(maLen * maLen, dfs(maLen + 1, matrix, i));
            }
        }
        return 0;
    }

    public boolean judge(int maLen, char[][] matrix, int i, int j) {
        if (maLen == 1 && matrix[i][j] == '1') return true;
        for (int k = i; k < i + maLen; k++) {
            for (int h = j; h < j + maLen; h++) {
                if (matrix[k][h] == '0')
                    return false;
            }
        }
        return true;
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
            int northwest = 0;
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

    /***
     * 二进制
     * 时间复杂度:O() - 100%
     * 空间复杂度:O() - 23.36%
     * 优点:
     * 缺点:
     * @param matrix
     * @return
     */
    public int maximalSquare1111(char[][] matrix) {
        for (int i = 0; i < matrix.length - res; ++i) {
            and_operate(i, matrix);
        }
        return res * res;
    }

    private int res = 0;

    private void and_operate(int row, char[][] matrix) {
        char[] next, base = matrix[row].clone();
        if (res < 1) calculate(base, 1);
        for (int i = row + 1; i < matrix.length; ++i) {
            next = matrix[i];
            for (int j = 0; j < base.length; ++j) {
                base[j] &= next[j];
            }
            if (!calculate(base, i - row + 1)) return;
        }
    }

    private boolean calculate(char[] array, int limit) {
        int count = 0;
        for (char ch : array) {
            if (ch == '0') {
                count = 0;
            } else if (++count == limit) {
                res = Math.max(res, limit);
                return true;
            }
        }
        return false;
    }
}
