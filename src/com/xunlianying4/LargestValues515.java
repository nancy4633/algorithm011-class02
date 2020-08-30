package com.xunlianying4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 您需要在二叉树的每一行中找到最大的值。
// 思路：
// BFS + 最优解
public class LargestValues515 {


    /**
     * DFS
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues2(TreeNode root) {
        // 这一部分很好些，关键是dfs的实现部分，不过最难写的还是bfs的循环～，以前觉得递归老难了，现在觉得简直是神仙姐姐，又美又厉害！！！
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        dfs2(result, root, 1);
        return result;
    }

    public void dfs2(List<Integer> result, TreeNode treeNode, int level) {
        // terminator
        if (treeNode == null) return;
        // process
        if (result.size() + 1 == level) {
            result.add(treeNode.val);
        } else {
            // 忘记减一了
            result.set(level - 1, Math.max(result.get(level - 1), treeNode.val));
        }
        // drill down
        dfs2(result, treeNode.left, level + 1);
        dfs2(result, treeNode.right, level + 1);
    }

    /***
     * BFS
     *
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues22(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        // while (!queue.isEmpty()) 这个语句还是不顺手，居然这么简单的语句都不顺手，也很神奇，加油！！！
        while (!queue.isEmpty()) {
            // 因为树的值可能是负整型，所以一定要用int的最小值，也就是Integer.MIN_VALUE。
            int max = Integer.MIN_VALUE;
            int level = queue.size();
            // i从几开始，经常搞混，一定要多留意，多总结经验。
            for (int i = 0; i < level; i++) {
                TreeNode treeNode = queue.poll();
                max = Math.max(max, treeNode.val);
                // 这个地方总是忘记怎么写，总想写递归
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            // 忘加了，一定要多加注意。
            result.add(max);
        }
        return result;
    }

    /***
     * DFS + 递归
     * 第一快
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs1(root, res, 1);
        return res;
    }

    // level表示的是第几层，集合res中的第一个数据表示的是
    // 第一层的最大值，第二个数据表示的是第二层的最大值……
    private void dfs1(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        if (level == res.size() + 1) {
            res.add(root.val);
        } else {
            res.set(level - 1, Math.max(res.get(level - 1), root.val));
        }
        dfs1(root.left, res, level + 1);
        dfs1(root.right, res, level + 1);
    }

    /***
     * BFS + 队列（LinkedList）
     * 第二快
     * 自己写BFS有点儿紧张，结果没写出来，让我再认命一天吧，老天再给我一天的机会
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues11(TreeNode root) {
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


}
