package com.xunlianying9.zuoye1;

// 第一遍
// 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
// 注意：给定 n 是一个正整数。
public class climbStairs70 {

    /**
     * 动态规划（自下而上） + 一维数组
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= n; i++) { // 循环的首位一定要验证一下！！！这里i从2开始，不是1。
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    /**
     * 动态规划（自下而上） + 三个变量
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 55.79%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int result = 0, temp1 = 0, temp2 = 1;
        for (int i = 2; i <= n; i++) { // 循环的首位一定要验证一下！！！这里i从2开始，不是1。
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        return result;
    }

    /**
     * 动态规划（自下而上） + 两个变量
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        int temp = 1, result = 1;
        for (int i = 2; i <= n; i++) {
            result = temp + result;
            temp = result - temp;
        }
        return result;
    }

    /**
     * 通项公式：容易记错，复习一下。
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        double fivep = Math.pow(5, 0.5);
        return (int) ((Math.pow(((1 + fivep) / 2), n + 1) - Math.pow(((1 - fivep) / 2), n + 1)) / fivep); // 这里n+1，千万记住，这次又写成n了。
    }

}
