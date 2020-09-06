package com.xunlianying3;

// 第一遍
// 对于递归的初始条件，不要仅仅想到第一步，还要想到第零步，因为所有递归都是从第零步开始，然后所有步骤都是从第零步递归出来的
// 所以真正重要的不是第一步而是第零步
// climbStairs33中的i、j、k，纠结了好久，怎么去摆放0、1、1，最后才发现，只要知道第一步从何而来就可以了，它是从0、0、1来的，所以初始值这个方法最简单了
public class climbStairs70 {
    /**
     * 动态规划 - 两个变量
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(1) - 98.46%
     *
     * @param n
     * @return
     */
    public int climbStairs33(int n) {
        int p = 0, q = 1;
        for (int i = 0; i < n; i++) {
            q += p;
            p = q - p;
        }
        return q;
    }

    /**
     * 动态规划
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(1) - 96.18%
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
     * 动态规划 - 思想和尾递归类似，但是因为递归会消耗过多的递归栈，所有for循环更优，使用尾递归的时间复杂度是：O(2^n)！！！
     * 这里提到的尾递归，是指未进行优化的递归，是二叉树的形式进行递归，很多分支重复计算，所以时间复杂度O(2^n)。
     * 时间复杂度：O(n) - 100.00%
     * 空间复杂度：O(n) - 86.00%
     *
     * @param n
     * @return
     */
    public int climbStairs22(int n) {
        int[] result = new int[n + 1];
        result[0] = 1; // 尤其注意，这里不是0。对于边界值，一定要多留意。
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    /**
     * 通项公式 - 出乎意料，通项公式居然不是最节省空间的～ 时间上这几个都很不错。
     * 时间复杂度：O(logn) - 100.00%
     * 空间复杂度：O(1) - 30.02%
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        double sqrt5 = Math.sqrt(5);
        return (int) ((Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1)) / sqrt5);
    }

    /**
     * 矩阵快速幂 - 暂时不记了，之后的动态规划自然会要理解～
     * 时间复杂度：O(logn) - 100.00%
     * 空间复杂度：O(1) - 48.26%
     *
     * @param n
     * @return
     */
    public int climbStairs111(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] result = pow(q, n);
        return result[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] result = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) result = multiply(result, a);
            n >>= 1;
            a = multiply(a, a);
        }
        return result;
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
}
