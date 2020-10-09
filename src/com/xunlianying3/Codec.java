package com.xunlianying3;

import java.util.LinkedList;
import java.util.Queue;

// 第三遍
// 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
// 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
// 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
// 解法一：前序遍历 - 层序遍历
// 时间复杂度： 75.52%
// 空间复杂度： 74.29%
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#!";
        String str = root.val + "!"; // 切记要加上这一句，整个逻辑最重要的呀，加加加！！！
        str += serialize(root.left); // 不可以判断root.left != null，会影响生成的字符串，字符串中空节点要用nulll占位
        str += serialize(root.right); // 不可以判断root.left != null，会影响生成的字符串，字符串中空节点要用nulll占位
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split("!"); // !是分隔符，#代表空节点，所以放到queue里直接pop就ok了
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
        }
        return bfs(queue);
    }

    public TreeNode bfs(Queue<String> queue) {
        if (queue.isEmpty()) return null;
        String str = queue.poll();
        if (str.equals("#")) return null; // 不可以用==， String是对象，不是基础数据类型
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = bfs(queue);
        root.right = bfs(queue);
        return root;
    }
}