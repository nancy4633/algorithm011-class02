package com.xunlianying3.zuoye;

// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
// 最近公共祖先：对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
// 说明:
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。
// 这题看着迷糊？待刷第三遍

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 思路：
// 树的递归：需要再练练树的递归！！！
// 分治
public class LowestCommonAncestor236 {

    /***
     * 树的递归
     * 第四遍！终于理解含义了，以后类似的题就知道怎么用最简单的递归树来写了，看来前几天一直刷题是有用处了，从简到难，理解起来就容易多了
     * 思路，分别在左右子树寻找pq，如果遍历到叶子节点依旧没有，会返回null值，如果子树是null值，就舍弃，直接寻找另一侧的子树
     * 向下递归遍历每一个子树，知道返回p、q、或者null值，最终都是遍历到叶子节点或者遍历到p、q才真正返回。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor4(root.right, p, q);
        TreeNode right = lowestCommonAncestor4(root.left, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /***
     * 树的递归
     * 第三遍，也只是能默写代码，并不是很理解
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // terminator
        if (root == null || root == p || root == q) return root;
        // drill down
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        // current logic
        if (left == null) return right;
        if (right == null) return left;
        // reverse state
        return root;
    }

    private static TreeNode treeNode;

    public static TreeNode lowestCommonAncestor33(TreeNode root, TreeNode p, TreeNode q) {
        dfs3(root, p, q);
        return treeNode;
    }

    public static void dfs3(TreeNode root, TreeNode p, TreeNode q) {

    }

    /***
     * 树的递归：
     * 深度遍历的一种：后序遍历
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /***
     * 第一种解法的拆解，更容易理解
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor22(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor22(root.left, p, q);
        TreeNode right = lowestCommonAncestor22(root.right, p, q);
        // 当left和right同时为空 ：说明root的左/右子树中都不包含p,q，返回null
        if (left == null && right == null) return null;
        // 当left为空 ，right不为空：p,q都不在root的左子树中，直接返回right。具体可分为两种情况：
        //      1. p,q其中一个在root的右子树中，此时right指向p（假设为p）
        //      2. p,q两节点都在root的右子树中，此时的right指向最近公共祖先节点
        if (left == null) return right;
        // 当left不为空，right为空：与情况3.同理
        if (right == null) return left;
        // 当left和right同时不为空：说明p,q分列在root的异侧（分别在左/右子树），因此root为最近公共祖先，返回root
        return root; // 2. if(left != null and right != null)
    }

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

    public static void main(String[] args) {
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode1;
        treeNode5.left = treeNode6;
        treeNode6.right = treeNode2;
        treeNode2.left = treeNode7;
        treeNode2.right = treeNode4;
        treeNode1.left = treeNode0;
        treeNode1.right = treeNode8;
        lowestCommonAncestor2(treeNode3, treeNode6, treeNode2);
    }
}
