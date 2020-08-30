package com.xunlianying6;

import java.util.Arrays;

// 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
public class CoinChange322 {
    /**
     * DFS
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int ans = Integer.MAX_VALUE;

    public void dfs(int[] coins, int index, int amount, int cnt) {
        if (index < 0) {
            return;
        }
        for (int c = amount / coins[index]; c >= 0; c--) {
            int na = amount - c * coins[index];
            int ncnt = cnt + c;
            if (na == 0) {
                ans = Math.min(ans, ncnt);
                break;//剪枝1
            }
            if (ncnt + 1 >= ans) {
                break; //剪枝2
            }
            dfs(coins, index - 1, na, ncnt);
        }
    }

    /***
     * 递归（自顶向下）
     * 时间复杂度:O() - 11。50%
     * 空间复杂度:O() - 29.54%
     * 优点:
     * 缺点:
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange11(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange11(coins, amount, new int[amount]);
    }

    private int coinChange11(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange11(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     * 动态规划（自底向上）
     * 时间复杂度:O() - 67.95%
     * 空间复杂度:O() - 19.32%
     * 优点:
     * 缺点:
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange111(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
