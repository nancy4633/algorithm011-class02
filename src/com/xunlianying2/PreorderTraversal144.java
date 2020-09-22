package com.xunlianying2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 第三遍 - 遍历（LinkedList尤其要记住）
// 给定一个二叉树，返回它的 前序 遍历。
public class PreorderTraversal144 {




    /**
     * 递归（自身递归）
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 97.49%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) return results;
        results.add(root.val);
        preorderTraversal1(root.left);
        preorderTraversal1(root.right);
        return results;
    }

    List<Integer> results = new ArrayList<>();

    /**
     * 遍历 + LinkedList (比stack效率高很多)
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(n) - 96.73%
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.val); // 前序
                stack.addFirst(root); // addFirst和pollFirst addLast和pollLast
                root = root.left;
            }
            root = stack.pollFirst();
            root = root.right;
        }
        return result;
    }
}
