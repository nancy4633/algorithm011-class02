package com.third.zuoye;

// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
// 最近公共祖先：对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
// 说明:
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 思路：
// 树的递归：需要再练练树的递归！！！
// 分治
public class LowestCommonAncestor236 {

    /***
     * 树的递归：
     * 在本函数实现递归，代码简洁，但是不容易想到，需要多加练习！！！
     * 后序遍历 - 由于最近公共祖先的目标是公共的root，所以更适合二叉树的后序遍历
     * 因为treenode类本身就相当于是链表结构，不需要我手动写栈，直接利用treenode的左右节点的关系，就可以利用递归实现遍历已经存储当前状态
     *
     * 第一遍刷题，胸有成竹的谢了2小时，最后都是超出时间限制。。。费解的很
     * 树的遍历还要每天练习！！！
     *
     * 时间复杂度：O(n) - 其中n为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度：O(n) - 最差情况下，递归深度达到N ，系统使用O(n)大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /***
     * 树的遍历：单独写递归函数，更容易想到
     * 存储左右子树
     *
     * 时间复杂度：O(n) - 其中n为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度：O(n) - 最差情况下，递归深度达到N ，系统使用O(n)大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor11(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /***
     * 树的遍历：单独写递归函数，更容易想到
     * 存储父节点
     *
     * 时间复杂度：O(n) - 其中n为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度：O(n) - 最差情况下，递归深度达到N ，系统使用O(n)大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor111(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
