package com.xunlianying8.zuoye1;

// 第一遍
// 给定一个整数，编写一个函数来判断它是否是2的幂次方。
// 思路：
// 2的幂次方，这个数的二进制只会有一个1。
public class isPowerOfTwo231 {
    /**
     * 时间复杂度:O(1) - 100% - 一次位运算的时间复杂度
     * 空间复杂度:O(1) - 94.68% - 只创建了一个常量x
     * 优点: 位运算简单易懂
     * 缺点:
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo1(int n) {
        if (n <= 0) return false; // 数学：2的幂次方始终大于零
        return (n & (n - 1)) == 0;
    }

    /**
     * 时间复杂度:O(1) - 100% - 一次位运算的时间复杂度
     * 空间复杂度:O(1) - 94.68% - 只创建了一个常量x
     * 优点: 位运算～与上面的方法类似，只是公式不同而已。
     * 缺点: 需要单独判断n==0的条件，目前没找到方法可以合并到公式里面。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false; // 0的情况始终需要单独判断
        return (n & (-n)) == n;
    }

    /**
     * 时间复杂度:O(1) - 100% - 运行时间与n中最后一个1的位置有关。在最坏情况下，n中只有第一位是1。对于32位整数，运行时间是O(1)的。
     * 因为int的位数是固定的，只要是可以计算的，就是O(1)
     * 空间复杂度:O(1) - 72.05% - 没有使用额外空间
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo3(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

}
