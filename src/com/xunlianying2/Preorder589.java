package com.xunlianying2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 第一遍
// 给定一个 N 叉树，返回其节点值的前序遍历。
public class Preorder589 {
    /**
     * 递归：
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(n^2) - 95.43% - 这个不是很确定？？？
     *
     * @param root
     * @return
     */
    public List<Integer> preorder1(Node root) {
        inOrder(root);
        return result;
    }

    List<Integer> result = new ArrayList<Integer>();

    public void inOrder(Node root) {
        if (root == null) return;
        result.add(root.val);
        int s = root.children.size();
        for (int i = 0; i < s; i++) {
            inOrder(root.children.get(i));
        }
    }

    /**
     * 迭代
     * 时间复杂度：O(n) - 38.45% - 每个节点只会入栈和出栈各一次
     * 空间复杂度：O(n) - 52.03% - 最坏的情况下，这棵 N 叉树只有 2 层，所有第 2 层的节点都是根节点的孩子。将根节点推出栈后，需要将这些节点都放入栈，共有 M - 1M−1 个节点，因此栈的大小为 O(M)O(M)。
     *
     * @param root
     * @return
     */
    public List<Integer> preorder11(Node root) {
        LinkedList<Integer> results = new LinkedList<>();
        if (root == null) return results;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            root = queue.pollFirst();
            results.addLast(root.val);
            Collections.reverse(root.children);
            for (Node node : root.children) {
                queue.addFirst(node);
            }
        }
        return results;
    }
}
