package com.xunlianying6.zuoye1;

import java.util.Arrays;

// 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
// 思路：
// 遍历暴力求解
//
public class CountSubstrings647 {
    /***
     * 从中心向两侧延伸 - 对于本题，这种方法比动态规划更快。
     * 时间复杂度:O(n^2) - 82.84%
     * 空间复杂度:O(1) - 60.71s%
     * 优点: 最容易想到
     * 缺点:
     * @param s
     * @return
     */
    public int countSubstrings1(String s) {
        int N = s.length(), ans = 0;
        for (int center = 0; center <= 2 * N - 1; ++center) { // 遍历每一个节点，作为中心点，向两侧不断延伸判断
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    /***
     * 动态规划 + 二维数组
     * 时间复杂度:O() - 31.50%
     * 空间复杂度:O() - 33.03%
     * 优点:
     * 缺点:
     * @param s
     * @return
     */
    public int countSubstrings11(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = s.length();
        for (int i = 0; i < n; i++) dp[i][i] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    /***
     * 动态规划 + 二维数组
     * 时间复杂度:O() - 45.81%
     * 空间复杂度:O() - 17.85%
     * 优点:
     * 缺点:
     * @param s
     * @return
     */
    public int countSubstrings111(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

}
