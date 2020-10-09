package com.xunlianying2;

// 第四遍 重点 - 动态规划（自底向上）的超级典型例子
// 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
public class NthUglyNumber49 {

    /**
     * 动态规划 - 想法很妙
     * 时间复杂度：O(N) - 81.99% - 其中N=n ，动态规划需遍历计算dp列表。 - 99.30%
     * 空间复杂度：O(N) - 19.80% - 长度为N的dp 列表使用O(N)的额外空间。 - 93.74%
     *
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        if (n == 0) return n;
        int[] result = new int[n];
        result[0] = 1;
        int count2 = 0, count3 = 0, count5 = 0;
        for (int i = 1; i < n; i++) {
            int n2 = result[count2] * 2, n3 = result[count3] * 3, n5 = result[count5] * 5;
            result[i] = Math.min(Math.min(n2, n3), n5);
            if (result[i] == n2) count2++; // 这里的判断不可以使用if-else，因为两个判断语句都可能为真，而且都要执行。
            if (result[i] == n3) count3++; // 这里的判断不可以使用if-else，因为两个判断语句都可能为真，而且都要执行。
            if (result[i] == n5) count5++; // 这里的判断不可以使用if-else，因为两个判断语句都可能为真，而且都要执行。
        }
        return result[n - 1];
    }
}
