package com.xunlianying2;

import java.util.LinkedList;
import java.util.List;

public class postorderTraversal145 {
    /**
     * 递归（自身函数递归）
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 98.06%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        if (root == null) return result;
        postorderTraversal1(root.left);
        postorderTraversal1(root.right);
        result.add(root.val);
        return result;
    }

    LinkedList<Integer> result = new LinkedList<>();

    /**
     * 遍历 + LinkedList (比stack效率高很多)
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.74%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>(); // 重点
        if (root == null) return result; // 重点
        LinkedList<TreeNode> stack = new LinkedList<>(); // 这里也可以使用stack，但是stack效率比较低，时间：58.04%
        stack.addFirst(root);// addFirst和pollFirst addLast和pollFirst 都可以。后进先出就可以 。
        while (!stack.isEmpty()) {
            root = stack.pollFirst();
            result.addFirst(root.val);
            if (root.left != null) stack.addFirst(root.left); // 等同于 add(...)
            if (root.right != null) stack.addFirst(root.right); // 等同于 add(...)
        }
        return result;
    }
}
