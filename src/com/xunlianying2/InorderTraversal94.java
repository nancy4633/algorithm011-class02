package com.xunlianying2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 第四遍 - 对于遍历的理解更好一些了，每次复习默写都会理解的更深一层，看来机械的复习还是有用的。
// 所以二叉树遍历写法的关键还是stack的实现，要么简单明了的用LinkedList，直接选择用addFirst和pollFirst，或者，addLast和pollLast
// 只有次序是对的就没问题。还有一点，就是都检查一下root是否为空，这个最稳妥了。
// 给定一个二叉树，返回它的中序 遍历。
public class InorderTraversal94 {
    /**
     * 递归（自身递归）
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 64.49%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) return results;
        inorderTraversal1(root.left);
        results.add(root.val);
        inorderTraversal1(root.right);
        return results;
    }

    List<Integer> results = new ArrayList<>();

    /**
     * 遍历 + LinkedList (比stack效率高很多)
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(n) - 87.51%
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);// addFirst和pollFirst   addLast和pollLast
                root = root.left;
            }
            root = stack.pollFirst();
            result.add(root.val); // 中序
            root = root.right;
        }
        return result;
    }

    /**
     * 莫里斯遍历:
     * 线索二叉树，这个方法没有看！！！刷第二遍的时候再看吧，忙着交作业！！！
     * 时间复杂度：O(n) 想要证明时间复杂度是O(n)，最大的问题是找到每个节点的前驱节点的时间复杂度。乍一想，找到每个节点的前驱节点的时间复杂度应该是 O(n\log n)，因为找到一个节点的前驱节点和树的高度有关。
     * 但事实上，找到所有节点的前驱节点只需要O(n) 时间。一棵 nn 个节点的二叉树只有 n-1n−1 条边，每条边只可能使用2次，一次是定位节点，一次是找前驱节点。
     * 故复杂度为 O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
