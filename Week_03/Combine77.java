package com.xunlianying3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 第一遍 今天要把苦难的事情做完，明天才会更轻松，要先做困难的，加油。
// 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
// 没有重复的集合！！！
public class Combine77 {
    /**
     * 回溯 + 剪枝 + ArrayList
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 97.15%
     * 优点:
     * 缺点:
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        getResult1(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void getResult1(int start, int n, int k, ArrayList<Integer> temp, List<List<Integer>> ans) {
        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < n - (k - temp.size()) + 1; i++) { // 1<=i<=n i不是index，是具体的值
            temp.add(i);
            getResult1(i + 1, n, k, temp, ans);
            temp.remove(temp.size() - 1); // reverse state 移除list中最后一个数字
        }
    }

    /**
     * 回溯 + 剪枝 + Stack
     * 时间复杂度：O() - 97.61%
     * 空间复杂度：O() - 44.38%
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) return result;
        getResult2(n, k, 1, new Stack<>(), result);
        return result;
    }

    private void getResult2(int n, int k, int begin, Stack<Integer> p, List<List<Integer>> result) {
        if (p.size() == k) {
            result.add(new ArrayList<>(p));
            return;
        }
        for (int i = begin; i <= n - (k - p.size()) + 1; i++) { // 这里就是剪枝
            p.push(i);
            getResult2(n, k, i + 1, p, result);
            p.pop();
        }
    }

    /**
     * 回溯
     * 时间复杂度：O() - 21.39%
     * 空间复杂度：O() - 90.75%
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine3(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) return result;
        getResult3(n, k, 1, new Stack<>(), result);
        return result;
    }

    private void getResult3(int n, int k, int begin, Stack<Integer> pre, List<List<Integer>> result) {
        if (pre.size() == k) {
            result.add(new ArrayList<>(pre));
            return;
        }
        for (int i = begin; i <= n; i++) {
            pre.add(i);
            getResult3(n, k, i + 1, pre, result);
            pre.pop();
        }
    }
}
