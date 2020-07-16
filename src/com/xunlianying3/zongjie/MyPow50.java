package com.xunlianying3.zongjie;

// 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
// 思路：
// 递归 - 树的深度优先遍历
// 迭代 - 动态规划、树的广度优先遍历
//
public class MyPow50 {

    /***
     * 快速幂 + 递归：
     * 快速幂算法的本质：分治算法
     *
     * 时间复杂度：O(logn) - 递归的层数
     * 空间复杂度：O(logn) - 递归栈的使用的空间
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

    /***
     * 快速幂 + 迭代
     * 逻辑看的有点儿晕，下次再看，说实在的，迭代的代码冗余又难懂。递归练完后要好好练练迭代
     *
     * 时间复杂度：O(logn) - 对n进行二进制拆分的时间复杂度
     * 空间复杂度：O(1)
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


}
