package com.xunlianying2.zuoye;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 给定一个二叉树，返回它的 前序 遍历。
// 思路：
// 模仿中序遍历的写法，想到两种方式
// 递归
// 栈
public class PreorderTraversal144 {

    /****
     * 遍历
     *
     * 时间复杂度：O(n) 递归函数 T(n) = 2*T(n/2)+1
     * 空间复杂度：最坏情况下需要空间O(n)，平均情况为O(logn)。
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
        // 第一次写的时候忘记判断空值了！！！开始写的是 if (treeNode != null) 然后一个大括号，后面觉得判断空直接返回更简洁
        if (treeNode == null) return;
        result.add(treeNode.val);
        if (treeNode.left != null) {
            preorderHelper(treeNode.left, result);
        }
        if (treeNode.right != null) {
            preorderHelper(treeNode.right, result);
        }
    }


    /****
     * 栈
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal11(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                result.add(treeNode.val);
                // 第一次这个地方写错了，把左节点入栈了
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            // 第一次这个地方写错了，忘记赋值给treeNode了
            treeNode = stack.pop();
            treeNode = treeNode.right;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
