package com.xunlianying3;

public class MinDepth111 {

    /***
     * 递归：
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(logn)
     *
     * 自己写的递归，有点儿冗余，不过还好
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        return root.left == null || root.right == null ?
                minDepth1(root.left) + minDepth1(root.right) + 1 :
                Math.min(minDepth1(root.left), minDepth1(root.right)) + 1;
    }

}
