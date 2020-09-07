package com.xunlianying3;

// 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
// 思路：
// 递归 - 树的深度优先遍历
// 迭代 - 动态规划、树的广度优先遍历
//
public class MyPow50 {
    /**
     * 快速幂 + 递归：
     * 快速幂算法的本质：分治算法
     * 时间复杂度：O(logn) - 95.81% - 递归的层数
     * 空间复杂度：O(logn) - 98.76% - 递归栈的使用的空间
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {
        if (n == 0) return 1.0;
        return (n >= 0) ? subPow1(x, n) : 1.0 / subPow1(x, -n);
    }

    public double subPow1(double x, long n) {
        if (n == 0) return 1.0;
        double temp = subPow1(x, n / 2);
        return (n % 2 == 0) ? (temp * temp) : (temp * temp * x);
    }

    /**
     * 快速幂 + 迭代
     * 时间复杂度：O(logn) - 95.81% - 对n进行二进制拆分的时间复杂度
     * 空间复杂度：O(1) - 73.12%
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow11(double x, int n) {
        long N = n;
        return N >= 0 ? subPow11(x, N) : 1.0 / subPow11(x, -N);
    }

    double subPow11(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
            if (N % 2 == 1) ans *= x_contribute;
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可。这个符号倒是可以学一学，挺巧妙的！！！
            N /= 2;
        }
        return ans;
    }

    /**
     * 公式
     * 时间复杂度:O() - 95.81%
     * 空间复杂度:O() - 97.94%
     * 优点:
     * 缺点:
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow0(double x, int n) {
        double s = n != 0 ? myPow0(x, n / 2) : 1;
        x = ((n % 2) == 0 ? 1 : x);
        return n > 0 ? x * s * s : 1 / x * s * s;
    }
}
