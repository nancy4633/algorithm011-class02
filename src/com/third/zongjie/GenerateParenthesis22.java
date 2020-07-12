package com.third.zongjie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 思路：
// 暴力求解 - 其实就是下面说的动态规划，太傻了，先不看了。
// 递归 - 深度优先遍历、广度优先遍历、动态规划
public class GenerateParenthesis22 {
    /***
     * 递归
     * 深度优先遍历
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        dfs2("", n, n, result);
        return result;
    }

    public static void dfs2(String current, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current);
            // 此处的return语句容易漏掉，切记！！！
            return;
        }
        if (left > right) return;
        // 这里的-1，不可以写成--，原因是--会在drill down的时候影响当前层的参数值。切记！！！
        if (left <= right && left > 0) dfs2(current + "(", left - 1, right, result);
        if (left < right && right > 0) dfs2(current + ")", left, right - 1, result);
    }

    /***
     * 动态规划
     * 广度优先遍历
     *
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis22(int n) {
        List<String> result = new ArrayList<>();

        return result;
    }

    /***
     * 深度优先遍历
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        dfs("", n, n, result);
        return result;
    }

    public static void dfs(String currentString, int left, int right, List<String> result) {
        if (left == 0 & right == 0) {
            result.add(currentString);
            return;
        }
        if (left > right) return;
        if (left > 0) dfs(currentString + "(", left - 1, right, result);
        if (right > 0) dfs(currentString + ")", left, right - 1, result);
    }

    /***
     * 广度优先遍历
     *
     *
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis11(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis111(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // 这里 dp 数组我们把它变成列表的样子，方便调用而已
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
                        // 枚举右括号的位置
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }
}
