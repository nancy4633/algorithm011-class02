package com.xunlianying4;

import java.util.ArrayList;
import java.util.List;

// 第三遍
// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
public class GenerateParenthesis22 {
    /**
     * DFS + char数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 97.41%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> results = new ArrayList<>();
        if (n < 1) return results;
        dfs(n, 0, 0, new char[2 * n], results);
        return results;
    }

    private void dfs(int n, int left, int right, char[] result, List<String> results) {
        if (right == n) {
            results.add(new String(result));
            return;
        }
        if (left < n) {
            result[left + right] = '(';
            dfs(n, left + 1, right, result, results);
        }
        if (right < left) {
            result[left + right] = ')';
            dfs(n, left, right + 1, result, results);
        }
    }

    /**
     * 回溯 + String + DFS + 做加法
     * 时间复杂度:O() - 96.71%
     * 空间复杂度:O() - 94.43%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs11("", 0, 0, n, res);
        return res;
    }

    private void dfs11(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }
        if (left < right) return;
        if (left < n) dfs11(curStr + "(", left + 1, right, n, res);
        if (right < n) dfs11(curStr + ")", left, right + 1, n, res);
    }

    /**
     * 回溯 + String + DFS + 做减法
     * 时间复杂度:O() - 96.71%
     * 空间复杂度:O() - 98.73%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs1("", n, n, res);
        return res;
    }

    private void dfs1(String curStr, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        if (left > right) return;
        if (left > 0) dfs1(curStr + "(", left - 1, right, res);
        if (right > 0) dfs1(curStr + ")", left, right - 1, res);
    }

    /**
     * 动态规划
     * 时间复杂度:O() - 11.36%
     * 空间复杂度:O() - 35.20%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis5(int n) {
        if (n == 0) return new ArrayList<>();
        List<List<String>> dp = new ArrayList<>(n);
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);
        for (int i = 1; i <= n; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }
}
