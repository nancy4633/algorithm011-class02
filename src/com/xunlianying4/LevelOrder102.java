package com.xunlianying4;

import javafx.util.Pair;

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
// 第四刷的时候貌似还有些觉得简单。完全可以自己写出来了
public class LevelOrder102 {

    /**
     * DFS - 递归
     * 时间复杂度：O(n) - 100%
     * 空间复杂度：O(h) - h是树的高度 - 5.71%
     * 优点：
     * 缺点：
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        dfs4(root, results, 0);
        return results;
    }

    private void dfs4(TreeNode root, List<List<Integer>> results, int level) {
        // terminator
        if (root == null) return;
        // process
        if (results.size() < (level + 1)) results.add(new ArrayList<>());
        results.get(level).add(root.val);
        // drill down
        dfs4(root.left, results, level + 1);
        dfs4(root.right, results, level + 1);
        // reverse state
    }

    /***
     * BFS -
     * 时间复杂度：O(n) - 91.32%
     * 空间复杂度：O(n) - 5.71%
     * 优点：
     * 缺点：
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder44(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); // 第一层循环 level =1， 第二层循环
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                result.add(treeNode.val);
                if (treeNode.left != null) queue.offer(treeNode.left);
                if (treeNode.right != null) queue.offer(treeNode.right);
            }
            results.add(result);
        }
        return results;
    }

    /***
     * DFS
     * 第三刷，依然存在些问题，思路更加清晰一点点，
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        dfs3(result, root, 0);
        return result;
    }

    private void dfs3(List<List<Integer>> result, TreeNode root, int level) {
        // 这里的new ArrayList的条件！！！终于看明白了！！！前两遍都是死记硬背的～其实很简单，怎么前两遍都没理解呢，还好悔悟及时！！！
        // terminator 这里没有终止条件，当没有节点的时候停止。
        // process
        if (result.size() <= level) result.add(level, new ArrayList<>());
        result.get(level).add(root.val);
        // drill down
        if (root.left != null) dfs3(result, root.left, level + 1);
        if (root.right != null) dfs3(result, root.right, level + 1);
        // reverse state
    }

    /***
     * BFS + 队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder33(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        if (root == null) return result;
        queue.add(new Pair<Integer, TreeNode>(0, root));
        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> currentPair = queue.poll();
            int level = currentPair.getKey();
            TreeNode treeNode = currentPair.getValue();
            result.get(level).add(treeNode.val);
            if (root.left != null) queue.add(new Pair(level + 1, root.left));
            if (root.right != null) queue.add(new Pair(level + 1, root.left));
        }
        return result;
    }

    /***
     * DFS + ArrayList
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
     * BFS
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
     * DFS + ArrayList
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) - h是树的高度
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
     * BFS + 队列
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