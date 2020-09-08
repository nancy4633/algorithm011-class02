package com.xunlianying3;

// 第二遍 - 重点，解法一的三元运算很巧妙，代码简洁，出神入化～
// 给定一个二叉树，找出其最小深度。
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
// 说明: 叶子节点是指没有子节点的节点。
// 特殊情况：
// [1,2] 应该返回的是2，root的叶子节点是2， 所以2才是这棵树唯一的叶子节点，取的是叶子节点的最小深度。
public class MinDepth111 {
    /**
     * 递归：
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(logn) - 97.93%
     *
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        return root.left == null || root.right == null ?
                minDepth1(root.left) + minDepth1(root.right) + 1 :
                Math.min(minDepth1(root.left), minDepth1(root.right)) + 1;
    }

    /**
     * 递归： 解法一的三元运算拆分成逻辑
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 54.36%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left != null && root.right != null) return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
        return root.left == null ? (minDepth2(root.right) + 1) : (minDepth2(root.left) + 1);
    }
}
