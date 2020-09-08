package com.xunlianying3;

import java.util.LinkedList;

// 第二遍 - 重点： 迭代写得不是很顺
// 翻转一棵二叉树。怎么翻转？左右调换吗？
public class InvertTree226 {
    /**
     * 递归 + DFS
     * 时间复杂度：O(n) - 每个元素都必须访问一次 - 100.00%
     * 空间复杂度：O(h) - 最坏的情况下，需要存放O(h)个函数调用(h是树的高度) - 99.2%
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = invertTree1(root.right);
        root.right = invertTree1(root.left);
        root.left = temp;
        return root;
    }

    /**
     * 迭代 + BFS
     * 时间复杂度：O(n) - 同样每个节点都需要入队列/出队列一次 - 100.00%
     * 空间复杂度：0(n) - 最坏的情况下会包含所有的叶子节点，完全二叉树叶子节点是n/2个 - 95.10%
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;
        LinkedList<TreeNode> queue = new LinkedList<>(); // Queue<TreeNode> queue = new LinkedList<>(); 但是LinkedList更快
        queue.add(root);
        TreeNode cur, temp;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return root;
    }
}
