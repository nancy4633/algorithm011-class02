package com.xunlianying4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 第四遍 解法一
// 您需要在二叉树的每一行中找到最大的值。
public class LargestValues515 {
    /**
     * DFS + 递归
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 83.48%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs1(root, result, 0); // 同： dfs1(root, result, 1);之前答案是这样写的，好像也是边界问题，现在不知道问什么不校验了，难道是jdk版本差异？
        return result;
    }

    private void dfs1(TreeNode root, List<Integer> result, int level) {
        if (root == null) return;
        if (level == result.size()) { // 同上
            result.add(root.val);
        } else {
            result.set(level, Math.max(result.get(level), root.val)); // 同上
        }
        dfs1(root.left, result, level + 1);
        dfs1(root.right, result, level + 1);
    }

    /**
     * BFS + 队列（LinkedList）
     * 时间复杂度:O() - 43.03%
     * 空间复杂度:O() - 86.94%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> values = new ArrayList<>();
        if (root != null) queue.addLast(root);//入队
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE, levelSize = queue.size();//每一层的数量
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.pollFirst();//出队
                max = Math.max(max, node.val);//记录每层的最大值
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            values.add(max);
        }
        return values;
    }
}
