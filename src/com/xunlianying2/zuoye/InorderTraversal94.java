package com.xunlianying2.zuoye;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 给定一个二叉树，返回它的中序 遍历。
// 思路：
// 递归
// 栈
// 莫里斯遍历
public class InorderTraversal94 {

    /****
     * 递归
     *
     * 时间复杂度：O(n) 递归函数 T(n) = 2*T(n/2)+1
     * 空间复杂度：最坏情况下需要空间O(n)，平均情况为O(logn)。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        inorderhelper(root, result);
        return result;
    }

    public void inorderhelper(TreeNode treeNode, List<Integer> result) {
        if (treeNode != null) {
            if (treeNode.left != null) {
                inorderhelper(treeNode.left, result);
            }
            // 这里面体现的就是中序遍历的逻辑！！！
            result.add(treeNode.val);
            if (treeNode.right != null) {
                inorderhelper(treeNode.right, result);
            }
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
    public List<Integer> inorderTraversal11(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack();
        TreeNode temp = root;
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            temp = stack.pop();
            result.add(temp.val);
            // 这个地方的逻辑总是写错，需要多练习！！！
            temp = temp.right;
        }
        return result;
    }

    /****
     * 莫里斯遍历:
     * 线索二叉树，这个方法没有看！！！刷第二遍的时候再看吧，忙着交作业！！！
     *
     * 时间复杂度：O(n) 想要证明时间复杂度是O(n)，最大的问题是找到每个节点的前驱节点的时间复杂度。乍一想，找到每个节点的前驱节点的时间复杂度应该是 O(n\log n)，因为找到一个节点的前驱节点和树的高度有关。
     * 但事实上，找到所有节点的前驱节点只需要O(n) 时间。一棵 nn 个节点的二叉树只有 n-1n−1 条边，每条边只可能使用2次，一次是定位节点，一次是找前驱节点。
     * 故复杂度为 O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal111(TreeNode root) {
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

    public static void main(String[] args) {

    }
}
