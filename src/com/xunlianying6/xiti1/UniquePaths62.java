package com.xunlianying6.xiti1;

import java.util.Arrays;

// 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
// 问总共有多少条不同的路径？
// 思路：
// 递归（自顶向下）
// 动态规划（自底向上） + 状态转移方程 + 状态压缩
public class UniquePaths62 {

    /***
     * 递归(自顶向下) + 数组缓存
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(n^2) - 38.00%
     * 优点:
     * 缺点:
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        return uniquePaths_recur(m, n, new int[m + 1][n + 1]);
    }

    public int uniquePaths_recur(int m, int n, int[][] memo) {
        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;
        if (memo[m][n] == 0) {
            memo[m][n] = uniquePaths_recur(m - 1, n, memo) + uniquePaths_recur(m, n - 1, memo);
        }
        return memo[m][n];
    }

    /***
     * 动态规划(自底向上) + 二维状态数组
     * 时间复杂度:O(m*n) - 100.00%
     * 空间复杂度:O(m*n) - 49%
     * 优点:
     * 缺点:
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths11(int m, int n) {
        int[][] memo = new int[m][n];
        for (int i = 0; i < n; i++) memo[0][i] = 1;
        for (int i = 0; i < m; i++) memo[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }

    /***
     * 动态规划(自底向上) + 状态压缩(二维转一维)
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(2n) - 30%
     * 优点:
     * 缺点:
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths111(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + pre[j];
            }
            pre = cur.clone();
        }
        return pre[n - 1];
    }

    /***
     * 动态规划(自底向上) + 状态压缩(二维转一维)
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(n) - 49%
     * 优点: 这个一维数组代替二维数组的思维方式要好好学习一下
     * 缺点:
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1111(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 这个方法太完美了
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }
}
