package com.third.zongjie;

// 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
// 假设一个二叉搜索树具有如下特征：
// 节点的左子树只包含小于当前节点的数。
// 节点的右子树只包含大于当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
// 思路：
// 递归 - 深度优先搜索
// 迭代 - 广度优先搜索
// 经验：
// 正如老师所说的，这里如果只判断节点和左右节点的关系，就错了！！！
// 一开始我以为问题出在null值上，还用Integer去判断int的null值，丢了西瓜捡芝麻！！！
public class IsValidBST98 {

    /**
     * 递归
     * 没看懂！！！！
     *
     * <p>
     * 时间复杂度：O(n) - 每个元素都必须访问一次
     * 空间复杂度：O(h) - 最坏的情况下，需要存放O(h)个函数调用(h是树的高度)
     *
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST1(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST1(root.right);
    }

    long pre = Long.MIN_VALUE;

    /**
     * 迭代 - 广度优先搜索
     * 先不看了，已经没有耐心了。
     *
     * 时间复杂度：O(n) - 同样每个节点都需要入队列/出队列一次
     * 空间复杂度：0(n) - 最坏的情况下会包含所有的叶子节点，完全二叉树叶子节点是n/2个
     *
     * @param root
     * @return
     */
    public boolean isValidBST11(TreeNode root) {

        return false;
    }
}
