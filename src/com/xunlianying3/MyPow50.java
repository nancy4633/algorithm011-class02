package com.xunlianying3;

// 第三遍  解法一的注意事项要记清楚，遇到类似的问题也要学会分析。
// 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
public class MyPow50 {
    /**
     * 快速幂 + 原函数递归
     * 时间复杂度:O() - 95.81%
     * 空间复杂度:O() - 97.94%
     * 优点:
     * 缺点:
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {
        double s = n != 0 ? myPow1(x, n / 2) : 1;
        x = ((n & 1) == 0 ? 1 : x); //也可以写成 ： x = ((n % 2) == 0 ? 1 : x);
        return n > 0 ? x * s * s : 1 / x * s * s; // 这里的1只除以x，不包含s，因为s是函数再次执行的结果，最后会递归成1，而且也有关于1/x的转换操作，所以不需要再用1除了
    }

    /**
     * 快速幂 + 新建递归函数：
     * 快速幂算法的本质：分治算法
     * 时间复杂度：O(logn) - 95.81% - 递归的层数
     * 空间复杂度：O(logn) - 98.76% - 递归栈的使用的空间
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        if (n == 0) return 1.0;
        return (n >= 0) ? subPow2(x, n) : 1.0 / subPow2(x, -n);
    }

    public double subPow2(double x, long n) {
        if (n == 0) return 1.0;
        double temp = subPow2(x, n / 2);
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
    public double myPow3(double x, int n) {
        long N = n;
        return N >= 0 ? subPow3(x, N) : 1.0 / subPow3(x, -N);
    }

    double subPow3(double x, long N) {
        double ans = 1.0;
        double x_contribute = x;
        while (N > 0) {
            if (N % 2 == 1) ans *= x_contribute;
            x_contribute *= x_contribute;
            N /= 2;
        }
        return ans;
    }
}
