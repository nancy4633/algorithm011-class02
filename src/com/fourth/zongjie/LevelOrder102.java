package com.fourth.zongjie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
// 思路：
// 广度优先搜索 - 递归+队列
public class LevelOrder102 {

    /***
     * 广度优先搜索 + 队列
     *
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Set<TreeNode> visited = new HashSet();
        BFS(root, visited);
        return result;
    }

    public void BFS(TreeNode treeNode, Set<TreeNode> visited) {
        // terminator

        // process

        // drill down

        // reverse state

    }
}
