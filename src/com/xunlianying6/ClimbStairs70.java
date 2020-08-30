package com.xunlianying6;

// 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
// 注意：给定 n 是一个正整数。
public class ClimbStairs70 {

    /***
     * 动态规划 + 三个变量
     * 时间复杂度:O(n) - 100%
     * 空间复杂度:O(1) - %
     * 优点:
     * 缺点:
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int i = 0, j = 0, k = 1;
        for (int temp = 0; temp < n; temp++) {
            i = j;
            j = k;
            k = i + j;
        }
        return k;
    }

    /***
     * 动态规划 + 两个变量（伪装成数组） - 这种有前后关系的，确实用数组更好。
     * 时间复杂度:O(n) - 100%
     * 空间复杂度:O(1) - %
     * 优点:
     * 缺点:
     * @param n
     * @return
     */
    public int climbStairs11(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
