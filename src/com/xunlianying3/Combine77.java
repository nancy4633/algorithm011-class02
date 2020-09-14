package com.xunlianying3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 第六遍 今天要把苦难的事情做完，明天才会更轻松，要先做困难的，加油。
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
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        getResult(1, n, k, new ArrayList<>(), results); // 此处n从1开始，不是0！！！//又一次发生，一定要仔细审题
        return results;
    }

    private void getResult(int start, int n, int k, ArrayList<Integer> result, List<List<Integer>> results) {
        if (result.size() == k) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = start; i <= n - (k - result.size()) + 1; i++) { // 此处的等号要记清楚；此处的计算要清楚，本办法也ok～
            result.add(i);
            // 此处传递了result，所有用i，如果不传递，像permute就需要用index！！！
            getResult(i + 1, n, k, result, results); // 这里是i+1，index只是边界值，因为不允许有重复元素，所以每一次递归，都是当前的i值加一，而不是边界值index+1
            result.remove(result.size() - 1); // reverse state 移除list中最后一个数字 // remove, 思维惯性要避免
        }
    }

    /**
     * 回溯 + 剪枝 + Stack，跟解法一的ArrayList的原理是一样的。
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
}
