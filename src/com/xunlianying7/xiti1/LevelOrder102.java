package com.xunlianying7.xiti1;

import java.util.*;

// 第二遍
// 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
public class LevelOrder102 {
    /**
     * DFS + 递归
     * 时间复杂度:O() - 91.67%
     * 空间复杂度:O() - 5.08%
     * 优点: 递归代码简洁，而且时间比BFS和并查集好很多。
     * 缺点: 空间复杂度最低
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
     * 时间复杂度:O() - 91.67%
     * 空间复杂度:O() - 82.59%
     * 优点:
     * 缺点:空间复杂度最好
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder11(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> visited = new Stack<>();
        stack.push(root);
        TreeNode node;
        List<Integer> result;
        while (!stack.isEmpty()) {
            result = new ArrayList<>();
            while (!stack.isEmpty()) {
                node = stack.pop();
                result.add(node.val);
                if (node.left != null) visited.push(node.left);
                if (node.right != null) visited.push(node.right);
            }
            results.add(result);
            // 因为有了visited，就可以不判断stack的长度了
            while (!visited.isEmpty()) {
                stack.push(visited.pop());
            }
        }
        return results;
    }

    /**
     * BFS+遍历
     * 时间复杂度:O() - 91.67%
     * 空间复杂度:O() - 72.96%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder111(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        TreeNode node;
        while (!queue.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                result.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            results.add(result);
        }
        return results;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        //第一层
        treeNodes.add(root);
        while (treeNodes.size() != 0) {
            ArrayList<Integer> integers = new ArrayList<>();
            //一个临时list存放下一层node
            ArrayList<TreeNode> treeNodeTmp = new ArrayList<>();
            for (int i = 0; i < treeNodes.size(); i++) {
                TreeNode node = treeNodes.get(i);
                integers.add(node.val);
                if (node.left != null) treeNodeTmp.add(node.left);
                if (node.right != null) treeNodeTmp.add(node.right);
            }
            //替换临时list
            treeNodes = treeNodeTmp;
            result.add(integers);
        }
        return result;
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