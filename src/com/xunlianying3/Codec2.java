package com.xunlianying3;

import java.util.LinkedList;
import java.util.Queue;

// 第一遍
// 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
// 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
// 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
// 解法二：层序遍历
// 时间复杂度： 48.74%
// 空间复杂度： 65.24%
public class Codec2 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String result = "";
        if (root == null) return result += "#!";
        result += (root.val + "!");
        result += serialize(root.left);
        result += serialize(root.right);
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split("!");
        if (s == null || s.length == 0 || s[0].equals("#")) return null;
        LinkedList<String> queue = new LinkedList<>();
        for (String temp : s) {
            queue.push(temp);
        }
        return reverse(queue);
    }

    private TreeNode reverse(LinkedList<String> queue) {
        String value = queue.pop();
        if (value.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = reverse(queue);
        root.right = reverse(queue);
        return root;
    }
}