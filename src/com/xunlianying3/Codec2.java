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
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        StringBuffer res = new StringBuffer();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode != null) {
                res.append(curNode.val + ",");
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            } else {
                res.append("null,");
            }
        }
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] val = data.substring(0, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(val[0]));
        int cur = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (!"null".equals(val[cur])) {
                curNode.left = new TreeNode(Integer.valueOf(val[cur]));
                queue.offer(curNode.left);
            }
            cur++;
            if (!"null".equals(val[cur])) {
                curNode.right = new TreeNode(Integer.valueOf(val[cur]));
                queue.offer(curNode.right);
            }
            cur++;
        }
        return root;
    }
}