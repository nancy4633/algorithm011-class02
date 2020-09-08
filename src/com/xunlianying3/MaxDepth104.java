package com.xunlianying3;

import javafx.util.Pair;

import java.util.LinkedList;

// 第三遍
// 给定一个二叉树，找出其最大深度。
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
// 说明: 叶子节点是指没有子节点的节点。
public class MaxDepth104 {
    /**
     * 递归 + DFS(自身函数递归)
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(h) - h是树的最大深度 - 96.18%
     *
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }

    /**
     * 递归 + DFS(新建递归函数)
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 62.58%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        return dfs2(0, root);
    }

    public int dfs2(int result, TreeNode root) {
        if (root == null) return result;
        result++;
        return Math.max(dfs2(result, root.left), dfs2(result, root.right));
    }

    /**
     * 迭代 + BFS
     * 难度并不高，代码比较复杂，本期练的是递归，先就不写迭代的代码了
     * 时间复杂度：偏高 - 16.44%
     * 空间复杂度：O(1) - 89.27%
     *
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>(); // 这一类问题用queue最合适了，queue的实现累就是LinkedList，加油。
        queue.add(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            maxDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) queue.add(node.left); // 这里不能用push；
                if (node.right != null) queue.add(node.right);
            }
        }
        return maxDepth;
    }

    /**
     * 迭代 + DFS
     * 时间复杂度:O() - 5.98%
     * 空间复杂度:O() - 96.31%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public int maxDepth4(TreeNode root) {
        if (root == null) return 0;
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, 1));
        int maxDepth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            maxDepth = Math.max(maxDepth, pair.getValue());
            int curDepth = pair.getValue();
            if (node.right != null) stack.push(new Pair<>(node.right, curDepth + 1));
            if (node.left != null) stack.push(new Pair<>(node.left, curDepth + 1));
        }
        return maxDepth;
    }
}
