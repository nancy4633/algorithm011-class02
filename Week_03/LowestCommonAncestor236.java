package com.xunlianying3;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 第一遍 : 重点 只要记住解法一就可以了。
// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
// 最近公共祖先：对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
// 说明:
// 所有节点的值都是唯一的。p、q 为不同节点且均存在于给定的二叉树中。
public class LowestCommonAncestor236 {
    /**
     * 树的递归
     * 时间复杂度:O() - 99.86%
     * 空间复杂度:O() - 90.90%
     * 优点:
     * 缺点:
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        if (right == null) return left; // 说明pq都在左子树，返回q或q的值
        if (left == null) return right; // 说明pq都在右子树，返回q或q的值
        return root; // 说明pq分别在root的左右子树，所以返回root这个公共父节点。
    }

    /**
     * 树的遍历：单独写递归函数，更容易想到
     * 存储左右子树
     * 时间复杂度：O(n) - 99.86% - 其中n为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度：O(n) - 50.30% - 最差情况下，递归深度达到N ，系统使用O(n)大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs3(root, p, q);
        return this.ans;
    }

    private TreeNode ans;

    private boolean dfs3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs3(root.left, p, q);
        boolean rson = dfs3(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) ans = root;
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /**
     * 树的遍历：单独写递归函数，更容易想到
     * 存储父节点
     * 时间复杂度：O(n) - 7.62% - 其中n为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度：O(n) - 5.04% - 最差情况下，递归深度达到N ，系统使用O(n)大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        dfs4(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = parent.get(q.val);
        }
        return null;
    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs4(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs4(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs4(root.right);
        }
    }
}
