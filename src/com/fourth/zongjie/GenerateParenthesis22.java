package com.fourth.zongjie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 思路：
// DFS+回溯
public class GenerateParenthesis22 {

    /***
     * 回溯（深度优先遍历 + 做减法）
     * 第二快
     *
     * @param n
     * @return
     */

    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) return res;
        // 执行深度优先遍历，搜索可能的结果
        dfs1("", n, n, res);
        return res;
    }

    private void dfs1(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) return;
        if (left > 0) dfs1(curStr + "(", left - 1, right, res);
        if (right > 0) dfs1(curStr + ")", left, right - 1, res);
    }

    /***
     * 回溯（ 深度优先遍历 + 做加法 ）
     * 第一快
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis11(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) return res;
        dfs11("", 0, 0, n, res);
        return res;
    }

    private void dfs11(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }
        // 剪枝
        if (left < right) return;
        if (left < n) dfs11(curStr + "(", left + 1, right, n, res);
        if (right < n) dfs11(curStr + ")", left, right + 1, n, res);
    }

    /***
     * 广度优先搜索
     * 第三快
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis111(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) res.add(curNode.res);
            if (curNode.left > 0) queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            if (curNode.right > 0 && curNode.left < curNode.right)
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
        }
        return res;
    }

    /***
     * 动态规划
     * 第四快
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis1111(int n) {
        if (n == 0) return new ArrayList<>();
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
