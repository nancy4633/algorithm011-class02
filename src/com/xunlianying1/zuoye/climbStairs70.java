package com.xunlianying1.zuoye;

// 遇到类似问题：
// 第一个思路： 暴力求解法？基本情况？
// 之后再找最优解
// 递归：判断是否可以使用递归？最近 重复子问题？为什么要找最近重复子问题？因为这样才可以串联起来啊，然后才可以一层层递归到终点。
public class climbStairs70 {

    /***
     * 暴力求解法 - 最简单的递归
     * 数字比较小的时候还可以，超过100就时间很久了，leetcode直接显示超过时间限制。
     *
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        // 0层
        // 1层
        // 2层
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    /***
     * 递归
     *
     * @param n
     * @return
     */
    public static int climbStairs11(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib_n = Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2, n + 1);
        return (int) (fib_n / sqrt_5);
    }

    /***
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int climbStairs111(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs111(100));
    }
}
