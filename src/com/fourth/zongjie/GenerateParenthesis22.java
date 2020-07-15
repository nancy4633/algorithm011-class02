package com.fourth.zongjie;

import java.util.ArrayList;
import java.util.List;

// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 思路：
// DFS+回溯
public class GenerateParenthesis22 {

    /***
     * DFS + 回溯
     *
     *
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        dfs2("", n, n, result);
        return result;
    }

    public void dfs2(String current, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current);
            return;
        }
        if (left > right) return;
        if (left <= right && left > 0) dfs2(current + "(", left - 1, right, result);
        if (left < right && right > 0) dfs2(current + ")", left, right - 1, result);
    }
}
