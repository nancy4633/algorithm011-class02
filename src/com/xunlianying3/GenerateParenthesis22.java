package com.xunlianying3;

import java.util.ArrayList;
import java.util.List;

// 第二遍 - 重点：char[] 代替String，这个想法太牛了～虽然也说不上是牛，但是习惯性的并不会想到用char[]
// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
public class GenerateParenthesis22 {
    /**
     * 递归（char[2*n]） + DFS
     * 就是把解法2的String换成2n长度的char数组，因为字符串的任何修改都意味着重新一个申请并创建对象，耗时耗空间。思路要集中在最耗这两点的问题上。
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.80%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        char[] dict = new char[2 * n];
        dfs1(n, 0, 0, result, dict);
        return result;
    }

    public void dfs1(int n, int left, int right, List<String> result, char[] dict) {
        if (right == n) { // 这里不用判断left了，判断最小的就可以了。
            result.add(new String(dict)); // 不清楚为什么比 String.valueOf(dict)快一丢丢～
            return; // 终止条件一定要记得加return;
        }
        if (left < n) {
            dict[left + right] = '('; // 都是给dict[left + right]赋值，所以不需要清除此处的赋值
            dfs1(n, left + 1, right, result, dict); //
        }
        if (right < left) {
            dict[left + right] = ')'; // 都是给dict[left + right]赋值，所以不需要清除此处的赋值
            dfs1(n, left, right + 1, result, dict);
        }
    }

    /**
     * 递归（String） + DFS
     * 时间复杂度：O(n) - 96.90%
     * 空间复杂度：O(1) - 89.17%
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        dfs2("", n, n, result);
        return result;
    }

    public void dfs2(String s, int left, int right, List<String> result) {
        if (left == 0 & right == 0) {
            result.add(s);
            return;
        }
        if (left > right) return;
        if (left > 0) dfs2(s + "(", left - 1, right, result);
        if (right > 0) dfs2(s + ")", left, right - 1, result);
    }

    /**
     * 递归 + DFS
     * 时间复杂度:O() - 96.90%
     * 空间复杂度:O() - 96.41%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        List<String> result = new ArrayList<>();
        dfs3(0, 0, n, "", result);
        return result;
    }

    public void dfs3(int left, int right, int n, String s, List<String> result) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        if (left < n) dfs3(left + 1, right, n, s + "(", result);
        if (right < left) dfs3(left, right + 1, n, s + ")", result);
    }

    /**
     * 动态规划
     * 时间复杂度:O() - 11.57%
     * 空间复杂度:O() - 16.34%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis4(int n) {
        List<List<String>> dp = new ArrayList<>(n);
        List<String> temp = new ArrayList<>(), str1, str2;
        temp.add("");
        dp.add(temp);
        for (int i = 1; i <= n; i++) {
            temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                str1 = dp.get(j);
                str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        temp.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(temp);
        }
        return dp.get(n);
    }
}
