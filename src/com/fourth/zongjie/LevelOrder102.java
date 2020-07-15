package com.fourth.zongjie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
// 思路：
// 广度优先搜索 - 递归 + 队列
// 深度优先搜索 - 递归 + 栈
// 这道题其实卡了三天，从周日开始，老师在视频讲了思路，不知道是这周的问题还是这题的问题，有思路，但是无从下手
// 纠结到周三，先把网上的题解贴过来了，主要是怕自己今天继续卡住，先下手把这个坑用别人的智慧填了，顾不上尊严了嘿嘿
public class LevelOrder102 {

    /***
     * 深度优先搜索 + ArrayList
     * 第二遍，dfs2里面的list的操作，一直没写对，对于这几个常用类的操作，需要背下来。还挺好用的。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs2(result, root, 0);
        return result;
    }

    private void dfs2(List<List<Integer>> result, TreeNode root, int level) {
        // result.add(new ArrayList<>()) 这个操作直接index从0开始递增，正好符合level的规律。直接从0递增
        if (level > result.size() - 1) result.add(new ArrayList<>());
        result.get(level).add(root.val);
        if (root.left != null) dfs2(result, root.left, level + 1);
        if (root.right != null) dfs2(result, root.right, level + 1);
    }

    /***
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder22(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 这里使用offer和add都可以。
        // queue没有push和offerfirst方法。所以之前自己想多了。
        queue.add(root);
        while (!queue.isEmpty()) {
            // 这里写错了，并没有把size保存在int变量中！！！切记，有些值是变的，一定要保存到临时变量
            // 由于queue的size一直是变的，如果不保存，for循环就不会停止了
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.peek();
                queue.poll();
                if (treeNode == null) continue;
                level.add(treeNode.val);
                queue.offer(treeNode.left);
                queue.offer(treeNode.right);
            }
            if (!queue.isEmpty()) result.add(level);
        }
        return result;
    }

    /***
     * 深度优先搜索 + ArrayList
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n^2)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) dfs1(res, root, 0);
        return res;
    }

    private void dfs1(List<List<Integer>> res, TreeNode node, int level) {
        if (res.size() - 1 < level) res.add(new ArrayList<Integer>());
        res.get(level).add(node.val);
        if (node.left != null) dfs1(res, node.left, level + 1);
        if (node.right != null) dfs1(res, node.right, level + 1);
    }

    /***
     * 广度优先搜索 + 队列
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder11(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 这部分不是很了解，为什么定义Queue的时候，为什么不用deque
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.peek();
                q.poll();
                if (cur == null) continue;
                level.add(cur.val);
                q.offer(cur.left);
                q.offer(cur.right);
            }
            if (!level.isEmpty()) res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        //
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        res.get(0).add(0);
        res.get(1).add(1);
        res.get(2).add(2);
    }
}