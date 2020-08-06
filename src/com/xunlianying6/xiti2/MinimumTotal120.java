package com.xunlianying6.xiti2;

import java.util.List;

// 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
// 思路：
// 递归
// 动态规划
public class MinimumTotal120 {
    /***
     * 递归(DFS) - 先不练了，之前练了几个递归的写法，还比较顺手，先练一下动态规划
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     * @param triangle
     * @return
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }

    Integer[][] memo;

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    /***
     * 动态规划(自底向上) + 二维数组
     * 时间复杂度:O(n^2) - 13.53%
     * 空间复杂度:O(1) - 76.85%
     * 优点:
     * 缺点:
     * @param triangle
     * @return
     */
    public int minimumTotal11(List<List<Integer>> triangle) {
        // 直接使用triangle数组
        if (triangle == null || triangle.size() == 0) return 0;
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                triangle.get(i).set(j, Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j));
            }
        }
        return triangle.get(0).get(0);
    }

    /***
     * 动态规划 + 一维数组
     * 时间复杂度:O(n^2) - 73.46%
     * 空间复杂度:O(n^2) - 28.70%
     * 优点:
     * 缺点:
     * @param triangle
     * @return
     */
    public int minimumTotal111(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /***
     * 动态规划(自底向上)+状态压缩（一维）
     * 时间复杂度:O() - 95。68%
     * 空间复杂度:O(n) - 75。93%
     * 优点:
     * 缺点:
     * @param triangle
     * @return
     */
    public int minimumTotal1111(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
