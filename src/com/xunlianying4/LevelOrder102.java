package com.xunlianying4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 第四遍 解法一 题目答案变了，之前是可以有[]的，但是现在不允许了，所以这种边界情况都要和面试官讨论清楚
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
            for (int i = size - 1; i >= 0; i--) {
                TreeNode cur = queue.peek(); // peek是first，p打头的都是first，其它全是last。当然明确标明first/last不算
                queue.poll();
                if (cur == null) continue;
                result.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if (result.size() != 0) results.add(result); // 题目答案变了，之前是可以有[]的，但是现在不允许了，所以这种边界情况都要和面试官讨论清楚
        }
        return results;
    }

    /**
     * 解法2的另一种实现，所有都使用LinkedList，虽然安全性欠妥，但是代码更清晰。学习的时候用这种解法会比较好。
     * 时间复杂度：O(n) - 92.95%
     * 空间复杂度：O(n) - 25.69%
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        LinkedList<List<Integer>> results = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) return results;
        queue.addLast(root); // offer-addlast push-addfirst pop-removefirst
        while (!queue.isEmpty()) {
            int size = queue.size(); // 这个临时变量一定要加，因为后面的queue size会一直变，需要用到初始值，这种变化的问题也要多考虑
            LinkedList<Integer> result = new LinkedList<>(); // 也可以用int index承接变量值。
            for (int i = 0; i < size; ++i) {
                root = queue.peekFirst(); // peek是first，p打头的都是first，其它全是last。当然明确标明first/last不算
                queue.pollFirst();
                if (root == null) continue;
                result.addLast(root.val);
                queue.addLast(root.left);
                queue.addLast(root.right);
            }
            if (result.size() != 0) results.add(result);
        }
        return results;
    }
}