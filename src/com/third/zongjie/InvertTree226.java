package com.third.zongjie;

import java.util.LinkedList;

// 翻转一棵二叉树。怎么翻转？左右调换吗？
// 思路：
// 数组下标直接移动 - 这个应该是最简单的了 ，这个比较难实现，因为还要遍历一整遍的数组，还不如直接就操作简单
// 这里也不涉及树的DFS和BFS
// 递归
// 迭代
public class InvertTree226 {

    /***
     * 递归：深度优先遍历
     * 第一次独立写对了一段递归代码，看来练了两天还是有成果的，虽然还是觉得浆糊一团
     *
     * 时间复杂度：O(n) - 每个元素都必须访问一次
     * 空间复杂度：O(h) - 最坏的情况下，需要存放O(h)个函数调用(h是树的高度)
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        // terminator
        if (root == null) return null;
        // process
        // drill down
        TreeNode temp = invertTree1(root.right);
        root.right = invertTree1(root.left);
        root.left = temp;
        // reverse state
        return root;
    }

    /***
     * 迭代：广度优先遍历
     *
     * 时间复杂度：O(n) - 同样每个节点都需要入队列/出队列一次
     * 空间复杂度：0(n) - 最坏的情况下会包含所有的叶子节点，完全二叉树叶子节点是n/2个
     *
     * @param root
     * @return
     */
    public TreeNode invertTree11(TreeNode root) {
        if (root == null) return null;
        //将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode tmp = queue.poll();
            TreeNode left = tmp.left;
            tmp.left = tmp.right;
            tmp.right = left;
            //如果当前节点的左子树不为空，则放入队列等待后续处理
            if (tmp.left != null) queue.add(tmp.left);
            //如果当前节点的右子树不为空，则放入队列等待后续处理
            if (tmp.right != null) queue.add(tmp.right);
        }
        //返回处理完的根节点
        return root;
    }
}
