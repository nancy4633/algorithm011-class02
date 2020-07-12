package com.second.zuoye;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 给定一个 N 叉树，返回其节点值的前序遍历。
// 思路：遇到类似问题，不知道该怎么定义变量，用哪些类，虽然知道要递归，但是有点儿晕
// 递归
//
public class Preorder589 {
    List<Integer> res = new ArrayList<Integer>();

    /**
     * 递归：
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n^2) - 这个不是很确定？？？
     *
     * @param root
     * @return
     */
    public List<Integer> preorder1(Node root) {
        inOrder(root);
        return res;
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        int s = root.children.size();
        for (int i = 0; i < s; i++) {
            inOrder(root.children.get(i));
        }
    }

    /**
     * 迭代
     * <p>
     * 时间复杂度：O(n) - 每个节点只会入栈和出栈各一次
     * 空间复杂度：O(n) - 最坏的情况下，这棵 N 叉树只有 2 层，所有第 2 层的节点都是根节点的孩子。将根节点推出栈后，需要将这些节点都放入栈，共有 M - 1M−1 个节点，因此栈的大小为 O(M)O(M)。
     *
     * @param root
     * @return
     */
    public List<Integer> preorder11(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }

    public static void main(String[] args) {

    }
}
