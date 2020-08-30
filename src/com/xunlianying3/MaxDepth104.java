package com.xunlianying3;

// 给定一个二叉树，找出其最大深度。
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
// 说明: 叶子节点是指没有子节点的节点。
// 思路：
// 递归 - 深度
// 迭代 - 广度
public class MaxDepth104 {
    /**
     * 递归 - 在原始函数内部写递归调用，四行搞定。
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) - h是树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }

    /***
     * 迭代：
     * 难度并不高，代码比较复杂，本期练的是递归，先就不写迭代的代码了
     *
     * 时间复杂度：偏高
     * 空间复杂度：O(1)
     *
     * @param root
     * @return
     */
    public int maxDepth11(TreeNode root) {
        return 0;
    }

}
