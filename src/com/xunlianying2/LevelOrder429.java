package com.xunlianying2;

import java.util.*;

// 第一遍 解法1-递归 解法3-遍历
// 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
// 思路：相当于图的广度遍历
// 递归
// 广度优先遍历
public class LevelOrder429 {
    /**
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 47.32%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(Node root) {
        dfs1(root, 0);
        return results;
    }

    List<List<Integer>> results = new ArrayList<>();

    private void dfs1(Node root, int level) {
        if (root == null) return;
        if (results.size() == level) {
            List<Integer> result = new ArrayList<>();
            result.add(root.val);
            results.add(result);
        } else {
            results.get(level).add(root.val);
        }
        for (Node node : root.children) {
            dfs1(node, level + 1);
        }
    }

    /**
     * 递归 - 这个自己想出来的还是有很多冗余，待优化！！！
     * 时间复杂度：O(n) - 83.44%
     * 空间复杂度：- 37.33% - 正常情况O(logn)，最坏情况O(n)。运行时在堆栈上的空间。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(Node root) {
        List<Node> cur = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        if (root != null) {
            cur.add(root);
            bfs2(cur, results);
        }
        return results;
    }

    public void bfs2(List<Node> cur, List<List<Integer>> results) {
        if (cur == null || cur.size() == 0) return;
        List<Node> next = new ArrayList<>();
        if (cur == null) return;
        List<Integer> result = new ArrayList<>();
        for (Node node : cur) {
            result.add(node.val);
            next.addAll(node.children);
        }
        results.add(result);
        bfs2(next, results);
    }

    /**
     * 广度优先搜索
     * 时间复杂度：O(n) - 80.69% - n指的是节点的数量
     * 空间复杂度：O(n) - 58.55% - 我们的列表包含所有节点
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(Node root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        List<Integer> result;
        List<Node> prev = Arrays.asList(root), cur;
        while (!prev.isEmpty()) {
            cur = new ArrayList<>();
            result = new ArrayList<>();
            for (Node node : prev) {
                result.add(node.val);
                cur.addAll(node.children);
            }
            results.add(result);
            prev = cur;
        }
        return results;
    }

    /**
     * 用队列实现广度优先搜索遍历
     * 时间复杂度：O(n) - 80.69% - n 指的是节点的数量
     * 空间复杂度：O(n) - 90.71%
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder4(Node root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                result.add(node.val);
                queue.addAll(node.children);
            }
            results.add(result);
        }
        return results;
    }
}
