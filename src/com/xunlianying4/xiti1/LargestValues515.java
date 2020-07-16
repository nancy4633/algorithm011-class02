package com.xunlianying4.xiti1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 您需要在二叉树的每一行中找到最大的值。
// 思路：
// BFS + 最优解
public class LargestValues515 {

    /***
     * BFS + 队列（LinkedList）
     * 第二快
     * 自己写BFS有点儿紧张，结果没写出来，让我再认命一天吧，老天再给我一天的机会
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> values = new ArrayList<>();
        if (root != null) queue.add(root);//入队
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE, levelSize = queue.size();//每一层的数量
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();//出队
                max = Math.max(max, node.val);//记录每层的最大值
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            values.add(max);
        }
        return values;
    }

    /***
     * DFS + 递归
     * 最快
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues11(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs11(root, res, 1);
        return res;
    }

    // level表示的是第几层，集合res中的第一个数据表示的是
    // 第一层的最大值，第二个数据表示的是第二层的最大值……
    private void dfs11(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        //如果走到下一层了直接加入到集合中
        if (level == res.size() + 1) {
            res.add(root.val);
        } else {
            //注意：我们的level是从1开始的，也就是说root
            // 是第一层，而集合list的下标是从0开始的，
            // 所以这里level要减1。
            // Math.max(res.get(level - 1), root.val)表示的
            // 是遍历到的第level层的root.val值和集合中的第level
            // 个元素的值哪个大，就要哪个。
            res.set(level - 1, Math.max(res.get(level - 1), root.val));
        }
        //下面两行是DFS的核心代码
        dfs11(root.left, res, level + 1);
        dfs11(root.right, res, level + 1);
    }
}
