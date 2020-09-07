package com.xunlianying3;

import java.util.Stack;

// 第一遍
// 根据一棵树的前序遍历与中序遍历构造二叉树。
// 注意:
// 你可以假设树中没有重复的元素。
// 已知一个二叉树的前序遍历和中序遍历的结果，求这个二叉树，那么这个二叉树如何表达呢？？？
public class BuildTree105 {
    /**
     * 递归
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(n) - 92.75%
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return dfs1(preorder, inorder, (long) Integer.MAX_VALUE + 1);
    }

    int pre_index = 0, in_index = 0;

    private TreeNode dfs1(int[] preorder, int[] inorder, long limit) {
        if (pre_index == preorder.length) return null;
        if (inorder[in_index] == limit) {
            in_index++;
            return null;
        }
        int val = preorder[pre_index++];
        TreeNode root = new TreeNode(val);
        root.left = dfs1(preorder, inorder, val); // 做节点有限制，要小于父节点的值
        root.right = dfs1(preorder, inorder, limit); // 右节点没有限制，所以取int的无限大加一。
        return root;
    }

    /**
     * 迭代 + 栈：类似于树的遍历
     * 时间复杂度：O(n) - 98.06%
     * 空间复杂度：O(n) - 99.71%
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
