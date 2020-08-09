package com.xunlianying7.zuoye1;

// 第二遍
// 思路：
// 递归
// 剪枝
// 双向BFS
// 启发式算法
public class ClimbStairs70 {

    /***
     * 迭代
     * 时间复杂度:O(n) - 100%
     * 空间复杂度:O(n) - 91.59%
     * 优点:
     * 缺点:
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int[] sum = new int[n + 1];
        sum[0] = 1;
        sum[1] = 1;
        for (int i = 2; i <= n; i++) {
            sum[n] = sum[n - 1] + sum[n - 2];
        }
        return sum[n];
    }

    /**
     * 递归（自顶向下） + memo
     * 时间复杂度:O(n) - 100%
     * 空间复杂度:O(n) - 93%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int[] sum = new int[n + 1];
        sum[0] = 1;
        sum[1] = 1;
        travel(n, sum);
        return sum[n];
    }

    public int travel(int n, int[] sum) {
        if (sum[n] == 0) sum[n] = travel(n - 1, sum) + travel(n - 2, sum);
        return sum[n];
    }


    /**
     * 通项公式
     * 时间复杂度:O(logn) - 100%
     * 空间复杂度:O(1) - 99%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        double fivePow = Math.pow(5.0, 0.5);
        double result = Math.pow(1.0 + fivePow, n) - Math.pow(1.0 - fivePow, n);
        return (int) (result / fivePow);

    }

    /**
     * 动态规划（自底向上） - 因为题目过于简单，所以迭代和动态规划实在太像了
     * 时间复杂度:O(n) - 100%
     * 空间复杂度:O(1) - 99%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

}
