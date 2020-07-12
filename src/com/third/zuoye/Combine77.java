package com.third.zuoye;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
// 思路：
// 回溯
// 回溯 + 剪枝，优化后的时间减少
// 递归和回溯要不要返回状态值，一直是我最大的疑惑！！！
public class Combine77 {
    /***
     * 回溯+剪枝
     * 第二遍，刷了一周的回溯的题，现在感觉好很多，之前代码都看不懂！！！
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine22(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) return result2;
        findCombinations22(n, k, 1, new Stack<>());
        return result2;
    }

    List<List<Integer>> result2 = new ArrayList<>();

    public void findCombinations22(int n, int k, int index, Stack<Integer> p) {
        if (p.size() == k) {
            result2.add(new ArrayList<>(p));
            return;
        }
        for (int i = index; i <= n - (k - p.size()) + 1; i++) {
            p.push(i);
            findCombinations22(n, k, i + 1, p);
            p.pop();
        }
    }

    /***
     * 回溯
     * 第一遍，知道肯定是回溯，折腾了一个小时，也没想好回溯怎么写。
     * 想用递归的思路，但是分支该怎么走，没想明白
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine1(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) return result1;
        findCombinations1(n, k, 1, new Stack<>());
        return result1;
    }

    private List<List<Integer>> result1 = new ArrayList<>();

    private void findCombinations1(int n, int k, int begin, Stack<Integer> pre) {
        if (pre.size() == k) {
            result1.add(new ArrayList<>(pre));
            return;
        }
        for (int i = begin; i <= n; i++) {
            pre.add(i);
            findCombinations1(n, k, i + 1, pre);
            pre.pop();
        }
    }

    /***
     * 回溯 + 剪枝
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine11(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) return result11;
        findCombinations11(n, k, 1, new Stack<>());
        return result11;
    }

    private List<List<Integer>> result11 = new ArrayList<>();

    /**
     * p 可以声明成一个栈，    i 的极限值满足： n - i + 1 = (k - pre.size())。
     * 【关键】n - i + 1 是闭区间 [i,n] 的长度。  k - pre.size() 是剩下还要寻找的数的个数。
     *
     * @param n
     * @param k
     * @param index
     * @param p
     */
    private void findCombinations11(int n, int k, int index, Stack<Integer> p) {
        if (p.size() == k) {
            result11.add(new ArrayList<>(p));
            return;
        }
        for (int i = index; i <= n - (k - p.size()) + 1; i++) {
            p.push(i);
            findCombinations11(n, k, i + 1, p);
            p.pop();
        }
    }
}
