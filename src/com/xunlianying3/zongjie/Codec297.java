package com.xunlianying3.zongjie;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
// 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
// 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
// 思路：
//
public class Codec297 {

    /**
     * 递归：
     * 序列化
     * 非空 ="节点值！"
     * 空值 = "#！"
     *
     * @param root
     * @return
     */
    public String serialize1(TreeNode root) {
        if (root == null) return "#!";
        String result = root.val + "!";
        result += serialize1(root.left);
        result += serialize1(root.right);
        return result;
    }

    /***
     * 递归
     * 将存储好的String类型数据去掉分割符号
     * 将此时数据二叉树的先序遍历结果依次压入队列
     *
     * @param data
     * @return
     */
    public TreeNode deserialize1(String data) {
        String[] arr = data.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
        }
        //调用先序遍历递归方法（改动后）依次重建二叉树
        return deserialize1(queue);
    }

    //传入队列
    public TreeNode deserialize1(Queue<String> queue) {
        //依次弹出队列中的节点值
        String str = queue.poll();
        //如果是"#",代表节点值为空,返回null
        if (str.equals("#")) {
            return null;
        }
        //若节点值不为空，将其由String转换回int
        //将其作为当前节点值新建当前节点
        TreeNode root = new TreeNode(Integer.parseInt(str));
        //递归调用连接当前节点的左右节点
        root.left = deserialize1(queue);
        root.right = deserialize1(queue);
        //递归完成后返回当前节点
        return root;
    }

    /**
     * 迭代
     * 因为最近主要是练习递归，所以并没有看迭代的实现！！！
     *
     * @param data
     * @return
     */
    public TreeNode deserialize11(String data) {
        if (data.length() < 3) return null;
        String[] convertedData = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.valueOf(convertedData[0]));
        // 这里的queue赋值，一直没弄明白，不过方法倒是记住了！！！
        // 这种方法的确是最简单的
        Deque<TreeNode> quque = new LinkedList<>();
        quque.add(root);
        TreeNode cur;
        int idx = 0;
        while (true) {
            cur = quque.remove();
            if (++idx >= convertedData.length) break;
            if (convertedData[idx].compareTo("null") != 0) {
                cur.left = new TreeNode(Integer.valueOf(convertedData[idx]));
                quque.add(cur.left);
            }
            if (++idx >= convertedData.length) break;
            if (convertedData[idx].compareTo("null") != 0) {
                cur.right = new TreeNode(Integer.valueOf(convertedData[idx]));
                quque.add(cur.right);
            }
        }
        return root;
    }


}
