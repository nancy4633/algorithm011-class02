package com.xunlianying9.zuoye1;

// 第一遍
// 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
// 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
// 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
public class minCostClimbingStairs746 {

    /**
     * 动态规划（自底向上） + 一维数组
     * 时间复杂度:O() - 99.94%
     * 空间复杂度:O() - 38.72%
     * 优点:
     * 缺点:
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] = Math.min(cost[i - 2], cost[i - 1]) + cost[i];
        }
        return Math.min(cost[cost.length - 2], cost[cost.length - 1]);
    }
}
