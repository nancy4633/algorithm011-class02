package com.xunlianying2;

import java.util.*;

// 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
// 思路：相当于图的广度遍历
// 递归
// 广度优先遍历
public class LevelOrder429 {

    /***
     * 递归
     * 这个自己想出来的还是有很多冗余，待优化！！！
     *
     * 时间复杂度：O(n)
     * 空间复杂度：正常情况O(logn)，最坏情况O(n)。运行时在堆栈上的空间。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(Node root) {
        List<Node> level = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            level.add(root);
            // 递归函数
            levelHelper(level, result);
        }
        return result;
    }

    public void levelHelper(List<Node> level, List<List<Integer>> result) {
        if (level == null || level.size() == 0) return;
        List<Node> newlevel = new ArrayList<>();
        if (level == null) return;
        List<Integer> res = new ArrayList<>();
        for (Node node : level) {
            res.add(node.val);
            newlevel.addAll(node.children);
        }
        result.add(res);
        levelHelper(newlevel, result);
    }

    /***
     * 广度优先搜索
     *
     * 时间复杂度：O(n) n指的是节点的数量
     * 空间复杂度：O(n) 我们的列表包含所有节点
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder11(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Node> previousLayer = Arrays.asList(root);

        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }

        return result;
    }

    /***
     * 用队列实现广度优先搜索遍历
     *
     * 时间复杂度：O(n) n 指的是节点的数量
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder111(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
