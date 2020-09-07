package com.xunlianying2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 第一遍 - 栈 + 遍历 ， 尤其要记住
// 给定一个二叉树，返回它的 前序 遍历。
public class PreorderTraversal144 {
    /**
     * 遍历
     * 时间复杂度：O(n) 递归函数 T(n) = 2*T(n/2)+1 - 100.00%
     * 空间复杂度：最坏情况下需要空间O(n)，平均情况为O(logn)。 - 45.94%
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    public void preorderHelper(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) return;
        result.add(treeNode.val);
        if (treeNode.left != null) preorderHelper(treeNode.left, result);
        if (treeNode.right != null) preorderHelper(treeNode.right, result);
    }

    /**
     * 栈 + 遍历
     * 时间复杂度：O(n) - 46.66%
     * 空间复杂度：O(n) - 89.21%
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.println(root.val + ",");
                result.add(root.val);
                stack.push(root); // stack.add(root)
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return result;
    }
}
