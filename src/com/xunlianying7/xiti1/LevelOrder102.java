package com.xunlianying7.xiti1;

import java.util.ArrayList;
import java.util.List;

public class LevelOrder102 {
    /**
     * DFS + 递归
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        travel(root, results, 0);
        return results;
    }

    public void travel(TreeNode root, List<List<Integer>> results, int level) {
        if (root == null) return;
        // if (results.get(level) == null) results.add(level, new ArrayList<>());  这样写会indexoutofboundexception
        if (results.size() <= level) results.add(level, new ArrayList<>());
        results.get(level).add(root.val);
        if (root.left != null) travel(root.left, results, level + 1);
        if (root.right != null) travel(root.right, results, level + 1);
    }

    /**
     * DFS + stack
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder11(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();


        return results;
    }

    /**
     * BFS+遍历
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder111(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();


        return results;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}