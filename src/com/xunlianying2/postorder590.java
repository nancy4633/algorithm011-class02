package com.xunlianying2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 第一遍
// 给定一个 N 叉树，返回其节点值的后序遍历。
// 说明: 递归法很简单，你可以使用迭代法完成此题吗?
public class postorder590 {
    /**
     * 遍历
     * 时间复杂度:O() - 40.14%
     * 空间复杂度:O() - 63.88%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorder1(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        if (root == null) return result;
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.addFirst(node.val); // 如果使用ArrayList，可以使用result.add(0, node.val);
            for (Node item : node.children) {
                if (item != null) stack.add(item);
            }
        }
        return result;
    }

    /**
     * 递归
     * 时间复杂度:O() - 96.34%
     * 空间复杂度:O() - 79.77%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorder3(Node root) {
        if (root == null) return result3;
        for (Node temp : root.children) {
            postorder3(temp);
        }
        result3.add(root.val);
        return result3;
    }

    List<Integer> result3 = new ArrayList<>();

    /**
     * 遍历
     * 时间复杂度:O() - 11.05%
     * 空间复杂度:O() - 99.79%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorder2(Node root) {
        List<Integer> list = new ArrayList<>();//建立一个整数列表，一个节点列表，两个栈
        List<Node> curr = new ArrayList();
        Stack<Node> stack1 = new Stack();
        Stack<Node> stack2 = new Stack();
        if (root != null) {  //根不空，进栈1
            stack1.push(root);
            while (!stack1.isEmpty()) {//栈1不空出栈1，然后进栈2
                Node temp = stack1.pop();
                stack2.push(temp);
                curr = temp.children;
                for (int i = 0; i < curr.size(); i++)//检查出栈节点的孩子依次入栈1
                {
                    stack1.add(curr.get(i));
                }
            }
        }
        while (!stack2.isEmpty()) {//栈2不空依次出栈，值加入列表
            Node ss = stack2.pop();
            list.add(ss.val);
        }
        return list;    //返回列表
    }
}
