package com.xunlianying9;

// 第一遍
// 给定一个数组，它的第i个元素是一支给定股票第i天的价格。
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
// 注意：你不能在买入股票前卖出股票。
public class maxProfit121 {

    /**
     * 动态规划（自底向上） + 新建一维数组
     * 时间复杂度:O() - 61.92%
     * 空间复杂度:O() - 81.46%
     * 优点:
     * 缺点:
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }

    /**
     * 动态规划（自底向上）+ 两个变量
     * 时间复杂度:O(n) - 98.97%
     * 空间复杂度:O(1) - 94.99%
     * 优点:
     * 缺点:
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
