package com.xunlianying2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 第二遍 解法1 解法2都需要熟练
// 给定一个 N 叉树，返回其节点值的后序遍历。
// 说明: 递归法很简单，你可以使用迭代法完成此题吗?
public class postorder590 {
    /**
     * 递归
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 79.77%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorder1(Node root) {
        if (root == null) return result1;
        int size = root.children.size();
        for (int i = 0; i < size; i++) { // 对于个数不大的用for+i比较快，对数据量比较大的，for+对象比较快？猜测！！！
            postorder1(root.children.get(i));
        }
        result1.add(root.val);
        return result1;
    }

    List<Integer> result1 = new ArrayList<>();

    /**
     * 遍历 - 一个stack
     * 时间复杂度:O() - 40.73%
     * 空间复杂度:O() - 23.68%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorder2(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Node> stack = new LinkedList<>();
        if (root == null) return result;
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            result.addFirst(node.val); // 如果使用ArrayList，可以使用result.add(0, node.val);
            for (Node item : node.children) {
                if (item != null) stack.addFirst(item);
            }
        }
        return result;
    }

    /**
     * 遍历 - 两个stack
     * 时间复杂度:O() - 11.05%
     * 空间复杂度:O() - 99.79%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public List<Integer> postorder3(Node root) {
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
