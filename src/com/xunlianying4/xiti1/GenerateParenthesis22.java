package com.xunlianying4.xiti1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 思路：
// DFS+回溯
public class GenerateParenthesis22 {

    /***
     * 回溯 + 深度优先遍历 （做加法）
     * 第二刷，印象中挺难的，没想到就过了，一定是我大显神通了哈哈哈
     * 第一快（因为后来加上剪枝了）
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        dfs2(result, n, 0, 0, "");
        return result;
    }

    public void dfs2(List<String> result, int n, int left, int right, String res) {
        // terminator
        if (left == n && right == n) {
            result.add(res);
            return;
        }
        // 一开始没加剪枝，加上之后确实快了很多，晋升为第一快
        if (left < right) return;
        // process
        if (left < n && left >= right) dfs2(result, n, left + 1, right, res + "(");
        if (right < n && left > right) dfs2(result, n, left, right + 1, res + ")");
        // drill down
        // reverse state
    }

    /***
     * 广度优先搜索 + queue(linkedlist)
     * bfs一直不太熟。关键还是stack或者queue的参数、大小变化，经常绕晕。需要多练习，今天又在纸上默写了一边模版，坎坷，希望这周可以把这部分弄明白。
     * 第二快
     *
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis22(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == 0 && node.right == 0) result.add(node.res);
            if (node.left > 0) queue.add(new Node(node.res + "(", node.left - 1, node.right));
            if (node.right > 0 && node.left < node.right)
                queue.add(new Node(node.res + ")", node.left, node.right - 1));
        }
        return result;
    }

    /***
     * 动态规划
     * 第二刷，之前理解动态规划就是回溯+剪枝，貌似还有些区别，看到的时候不会第一时间想出解法和代码
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis222(int n) {
        if (n == 0) return new ArrayList<>();
        List<List<String>> results = new ArrayList<>();
        List<String> result0 = new ArrayList<>();
        // 这一步很重要，否则之后的每一步都会是空
        results.add(result0);
        for (int i = 1; i <= n; i++) {
            List<String> curresult = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> temp1 = results.get(j);
                List<String> temp2 = results.get(i - 1 - j);
                for (String s1 : temp1) {
                    for (String s2 : temp2) {
                        // 枚举右括号的位置
                        curresult.add("(" + s1 + ")" + s2);
                    }
                }

            }
            results.add(curresult);
        }
        return results.get(n);
    }


    /***
     * 回溯 + 深度优先遍历 (做减法)
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
        // 之前没自己定义过树，这类的定义需要学习
        Queue<Node> queue = new LinkedList<>();
        // offer是在前面添加。
        queue.offer(new Node("", n, n));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            // terminator
            if (curNode.left == 0 && curNode.right == 0) res.add(curNode.res);
            // process & drill down
            // 这里的字符串是curNode.res + "("一定要紧急
            if (curNode.left > 0) queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            // process & drill down
            // 这里的判断条件一开始写错了
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
        // 这一步很重要，否则之后的每一层的结果都是空
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

    public static void main(String[] args) {
        System.out.println(generateParenthesis22(3));
    }
}
