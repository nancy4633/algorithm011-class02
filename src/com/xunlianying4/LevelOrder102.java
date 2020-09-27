package com.xunlianying4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 第三遍 解法一
// 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
public class LevelOrder102 {
    /**
     * DFS + ArrayList
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(h) - 62.20% - h是树的高度
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root != null) dfs1(results, root, 0);
        return results;
    }

    private void dfs1(List<List<Integer>> results, TreeNode node, int level) {
        if (results.size() - 1 < level) results.add(new ArrayList<>());
        results.get(level).add(node.val);
        if (node.left != null) dfs1(results, node.left, level + 1);
        if (node.right != null) dfs1(results, node.right, level + 1);
    }

    /**
     * BFS + Queue(LinkedList)
     * 时间复杂度：O(n) - 92.97%
     * 空间复杂度：O(n) - 25.69%
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return results;
        queue.offer(root); // offer-addlast push-addfirst pop-removefirst
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> result = new LinkedList<>(); // 也可以用int index承接变量值。
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.peek();
                queue.poll();
                if (cur == null) continue;
                result.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            results.add(result);
        }
        return results;
    }
}