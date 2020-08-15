package com.xunlianying8.zuoye1;

// 颠倒给定的 32 位无符号整数的二进制位。
// 思路：
// 转换成字符串，再颠倒，再转换成int，是最耗时的，完全不考虑
// 最好利用位运算。
public class reverseBits190 {

    /**
     * 逐位颠倒
     * 时间复杂度:O(1) - 100.00%
     * 空间复杂度:O(1) - 95.31%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int reverseBits1(int n) {
        int res = 0, count = 0;
        while (count < 32) {
            res <<= 1;  //res左移一位空出位置
            res |= (n & 1); //得到的最低位加过来
            n >>= 1;//原数字右移一位去掉已经处理过的最低位
            count++;
        }
        return res;
    }

    /**
     * 时间复杂度:O(1) - 100.00%
     * 空间复杂度:O(1) - 88.54%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int reverseBits2(int n) {
        n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    /**
     * 时间复杂度:O(1) - 100.00% -
     * 空间复杂度:O(1) - 42.32% - 常量空间
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int reverseBits3(int n) {
        int ans = 0;
        for (int bitsSize = 31; n != 0; n = n >>> 1, bitsSize--) {
            ans += (n & 1) << bitsSize;
        }
        return ans;
    }
}
