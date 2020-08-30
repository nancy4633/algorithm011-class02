package com.xunlianying3;

// 递归步骤：
// 找 最近 重复子问题
// if else
// for while,recursion
// 1:1
// 2:2
// 3:f(1)+f(2), mutual exclusive, complete exhaustive
// 4:f(2)+f(3)
// ...
// n:f(n-1)+f(n-2)

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 经验！！！
// 对于递归的初始条件，不要仅仅想到第一步，还要想到第零步，因为所有递归都是从第零步开始，然后所有步骤都是从第零步递归出来的
// 所以真正重要的不是第一步而是第零步
// climbStairs33中的i、j、k，纠结了好久，怎么去摆放0、1、1，最后才发现，只要知道第一步从何而来就可以了，它是从0、0、1来的，所以初始值这个方法最简单了
public class climbStairs70 {
    /****
     * 通项公式
     *
     * 时间复杂度：n(logn)
     * 空间复杂度：n(1)
     *
     * @param n
     * @return
     */
    public static int climbStairs3(int n) {
        double sqrt5 = Math.sqrt(5);
        double result = Math.pow(((1 + sqrt5) / 2), n + 1) - Math.pow(((1 - sqrt5) / 2), n + 1);
        return (int) (result / sqrt5);
    }

    /****
     * 迭代
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     *
     *
     * @param n
     * @return
     */
    public static int climbStairs33(int n) {
        int i = 0, j = 0, k = 1;
        for (int temp = 0; temp < n; temp++) {
            i = j;
            j = k;
            k = i + j;
        }
        return k;
    }

    /***
     * 通项公式
     *
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        double sqrt5 = Math.sqrt(5);
        return (int) ((Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1)) / sqrt5);
    }

    /***
     * 迭代
     * 思想和尾递归类似，但是因为递归会消耗过多的递归栈，所有for循环更优，使用尾递归的时间复杂度是：O(2^n)
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public static int climbStairs22(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < result.length; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    /***
     * 矩阵
     * 暂时不想看，太复杂了，面试的时候用不到。
     *
     * @param n
     * @return
     */
    public static int climbStairs222(int n) {
        return n;
    }

    /***
     * 通项公式
     *
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibn / sqrt5);
    }

    /***
     * 动态规划
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int climbStairs11(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /***
     * 矩阵快速幂
     *
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int climbStairs111(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs33(3));
        int n = 8, m = 8;
        System.out.println("n >>= 1 是 " + (n >>= 1));
        System.out.println("n 是 " + n);
        System.out.println("m >> 1 是 " + (m >> 1));
        System.out.println("m 是 " + m);
    }

    // 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
    // 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
    // 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
    // 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
    // 思路：
    //
    public static class Codec297 {

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
}
