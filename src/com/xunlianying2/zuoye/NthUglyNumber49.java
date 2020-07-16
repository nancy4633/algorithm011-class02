package com.xunlianying2.zuoye;

// 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
// 思路：
// 刚看到题目的时候，只想到了2、3、5分别求幂，存成数组，但是如何递增条件判断，没想明白，看了动态规划的题解，发现自己就差了一步，取min值就可以了，吸取经验。
// 动态规划
public class NthUglyNumber49 {

    /***
     * 动态规划
     *
     * 时间复杂度：O(N) ： 其中N=n ，动态规划需遍历计算dp列表。
     * 空间复杂度：O(N) ： 长度为N的dp 列表使用O(N)的额外空间。
     *
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

    }
}
