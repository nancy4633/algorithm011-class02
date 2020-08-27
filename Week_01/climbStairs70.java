package com.xunlianying1;

// 第三遍
// 遇到类似问题：
// 第一个思路： 暴力求解法？基本情况？
// 之后再找最优解
// 递归：判断是否可以使用递归？最近 重复子问题？为什么要找最近重复子问题？因为这样才可以串联起来啊，然后才可以一层层递归到终点。
public class climbStairs70 {
    /**
     * 通项公式（斐波那契数列）
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 79.67%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs5(int n) {
        double sqrt5 = Math.sqrt(5);
        return (int) ((Math.pow(((1 + sqrt5) / 2), n + 1) - Math.pow(((1 - sqrt5) / 2), n + 1)) / sqrt5); // 尤其记住n+1
    }

    /**
     * 动态规划（自底向上）+ 一维数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 78.86%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int[] result = new int[n + 1];
        result[0] = 1; // 自定义数组的 0 、 1 ，一定要用真正的参数验证一下
        result[1] = 1; // 自定义数组的 0 、 1 ，一定要用真正的参数验证一下
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    /***
     * 动态规划 + 两个参数
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 90.59%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int temp = 0, result = 1;
        for (int i = 1; i <= n; ++i) {
            result += temp;
            temp = result - temp;
        }
        return result;
    }

    /***
     * 动态规划 + 三个参数
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 65.76%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        int prev = 0, curr = 0, result = 1;
        for (int i = 1; i <= n; ++i) {
            prev = curr;
            curr = result;
            result = prev + curr;
        }
        return result;
    }

    /***
     * 递归（自顶向下） + memo
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 15.50%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        int memo[] = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        return traversal(memo, n);
    }

    private int traversal(int memo[], int n) {
        if (memo[n] == 0) {
            memo[n] = traversal(memo, n - 1) + traversal(memo, n - 2);
        }
        return memo[n];
    }
}
