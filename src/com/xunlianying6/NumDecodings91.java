package com.xunlianying6;

import java.util.HashMap;

// 一条包含字母 A-Z 的消息通过以下方式进行了编码：
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。
// 思路：
// 递归+BFS
// 动态规划（自底向上） + 状态压缩
public class NumDecodings91 {

    /***
     * 递归 - 跟爬楼梯的思路类似，我居然还打算按照1，2，非1或2的情况分别计算，有点儿过了。
     * 时间复杂度:O(2^n) - 5%
     * 空间复杂度:O(1) - 12.4%
     * 优点:
     * 缺点:
     * @param s
     * @return
     */
    public int numDecodings1(String s) {
        // return recur1(s, 0);
        return recur2(s, 0, new HashMap<>());
    }

    public int recur1(String s, int start) {
        if (start == s.length()) return 1;
        if (s.charAt(start) == '0') return 0;
        int ans1 = recur1(s, start + 1);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) ans2 = recur1(s, start + 2);
        }
        return ans1 + ans2;
    }

    /***
     * 递归+memo
     * 时间复杂度:O() - 42.84%
     * 空间复杂度:O() - 10.85%
     * 优点:
     * 缺点:
     * @param s
     * @param start
     * @param memoization
     * @return
     */
    public int recur2(String s, int start, HashMap<Integer, Integer> memoization) {
        if (start == s.length()) return 1;
        if (s.charAt(start) == '0') return 0;
        int m = memoization.getOrDefault(start, -1);
        if (m != -1) return m;
        int ans1 = recur2(s, start + 1, memoization);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) ans2 = recur2(s, start + 2, memoization);
        }
        memoization.put(start, ans1 + ans2);
        return ans1 + ans2;
    }

    /***
     * 动态规划
     * 时间复杂度:O(n) - 99.98%
     * 空间复杂度:O(1) - 86.82%
     * 优点:
     * 缺点:
     * @param s
     * @return
     */
    public int numDecodings11(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        if (s.charAt(len - 1) != '0') dp[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            int ans1 = dp[i + 1];
            int ans2 = 0;
            int ten = (s.charAt(i) - '0') * 10;
            int one = s.charAt(i + 1) - '0';
            if (ten + one <= 26) ans2 = dp[i + 2];
            dp[i] = ans1 + ans2;
        }
        return dp[0];
    }

    /***
     * 动态规划+状态压缩（两个变量）
     * 时间复杂度:O(n) - 99.98%
     * 空间复杂度:O(1) - 79.84%
     * 优点:
     * 缺点:
     * @param s
     * @return
     */
    public int numDecodings111(String s) {
        int len = s.length();
        int end = 1;
        int cur = 0;
        if (s.charAt(len - 1) != '0') cur = 1;
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                end = cur; // end 前移
                cur = 0;
                continue;
            }
            int ans1 = cur;
            int ans2 = 0;
            int ten = (s.charAt(i) - '0') * 10;
            int one = s.charAt(i + 1) - '0';
            if (ten + one <= 26) ans2 = end;
            end = cur; // end 前移
            cur = ans1 + ans2;

        }
        return cur;
    }

    public static void main(String[] args) {
        String s = "6";
        System.out.println((int) s.charAt(0));
    }
}
