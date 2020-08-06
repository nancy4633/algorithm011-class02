package com.xunlianying4.zuoye2;

// 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
public class MaxProfit122 {

    /***
     * 暴力求解 - 超时
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        this.res = 0;
        dfs(prices, 0, len, 0, res);
        return this.res;
    }

    private int res;

    /**
     * @param prices 股价数组
     * @param index  当前是第几天，从 0 开始
     * @param status 0 表示不持有股票，1表示持有股票，
     * @param profit 当前收益
     */
    private void dfs(int[] prices, int index, int len, int status, int profit) {
        if (index == len) {
            this.res = Math.max(this.res, profit);
            return;
        }
        dfs(prices, index + 1, len, status, profit);
        if (status == 0) {
            dfs(prices, index + 1, len, 1, profit - prices[index]); // 可以尝试转向 1
        } else {
            dfs(prices, index + 1, len, 0, profit + prices[index]); // 此时 status == 1，可以尝试转向 0
        }
    }

    /**
     * 贪心算法 - ifelse
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit11(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) res += diff;
        }
        return res;
    }

    /***
     * 贪心算法 - 三元运算符
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfit111(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++) {
            res += Math.max(prices[i + 1] - prices[i], 0);
        }
        return res;
    }

    /***
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        // cash：持有现金
        // hold：持有股票
        // 状态数组
        // 状态转移：cash → hold → cash → hold → cash → hold → cash
        int[] cash = new int[len];
        int[] hold = new int[len];

        cash[0] = 0;
        hold[0] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
        }
        return cash[len - 1];
    }

    /***
     * 动态规划 - 状态压缩
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfit11111(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // cash：持有现金
        // hold：持有股票
        // 状态转移：cash → hold → cash → hold → cash → hold → cash
        int cash = 0;
        int hold = -prices[0];
        int preCash = cash;
        int preHold = hold;
        for (int i = 1; i < len; i++) {
            cash = Math.max(preCash, preHold + prices[i]);
            hold = Math.max(preHold, preCash - prices[i]);
            preCash = cash;
            preHold = hold;
        }
        return cash;
    }
}
