package com.xunlianying6.xiti1;

// 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
// 思路：
// 动态规划 递归 分治 没有本质的区别，所以题解，就是递归、分治、动态规划。
// 递归（自顶向下）
// 动态规划（自底向上） - 因为没有求最值，斐波那契数列不属于动态规划（没有涉及最优子结构）
public class Fib509 {
    /***
     * 递归（自顶向下） + 数组缓存中间结果（也可以使用hashmap）
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O() - 8.33%
     * 优点:
     * 缺点:
     * @param N
     * @return
     */
    public int fib1(int N) {
        // 0和1的情况进行合并。
        if (N <= 1) return N;
        // 数组越界，忘记加一了，因为算上了fib0，所以比实际长度加一。
        return fib(N, new int[N + 1]);
    }

    public int fib(int N, int[] memo) {
        if (N <= 1) return N;
        // 先在结果数组判断是否已经缓存，减少时间复杂度。
        // 备忘录==剪枝
        if (memo[N] == 0) memo[N] = fib(N - 1, memo) + fib(N - 2, memo);
        return memo[N];
    }

    /***
     * 动态规划（自底向上） + 一维数组
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(1) - 79.76% - 不明白为什么一维数组比两个变量更节省空间。
     * 优点:
     * 缺点:
     * @param n
     * @return
     */
    public int fib11(int n) {
        if (n <= 1) return n;
        // 需要在 0、1 判断之后创建状态数组，否则会数组越界。
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;
        // 自底向上for循环遍历 - 这是动态规划的关键。
        // 状态转移方程：f(n) 想做一个状态 n，这个状态 n 是由状态 n - 1 和状态 n - 2 相加转移而来，这就叫状态转移
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    /***
     * 动态规划（自底向上）+ 状态压缩（两个中间变量）
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(1) - 38.09%
     * 优点:《状态压缩》 如果我们发现每次状态转移只需要 DP table 中的一部分，那么可以尝试用状态压缩来缩小 DP table 的大小，只记录必要的数据，上述例子就相当于把DP table 的大小从 n 缩小到 2。后续的动态规划章节中我们还会看到这样的例子，一般来说是把一个二维的 DP table 压缩成一维，即把空间复杂度从 O(n^2) 压缩到 O(n)。
     * 缺点:
     * @param n
     * @return
     */
    public int fib111(int n) {
        // terminator
        // 这里的 0、1、2 都需要提前判断
        if (n == 0) return 0;
        if (n == 2 || n == 1) return 1;
        // 状态压缩：只使用两个变量存储中间状态
        int prev = 1, curr = 1;
        int sum;
        // 自底向上for循环遍历 - 这是动态规划的关键。
        // 状态转移方程！！！
        for (int i = 3; i <= n; i++) {
            sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}
