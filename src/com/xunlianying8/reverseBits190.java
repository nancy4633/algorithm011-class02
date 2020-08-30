package com.xunlianying8;

// 第三遍
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
        int result = 0, pos = 0;
        // 这里一定要记得32这个数字哦，因为int数据类型，就是32位，如果按照n来计算的话，就是错误的了！！！！
        while (pos < 32) {
            result += (((n >> pos) & 1) << (31 - pos)); //得到的最低位加过来
            pos++;
        }
        return result;
    }

    /**
     * bit位的交换法，挺巧妙。。。但是太难想到了
     * 时间复杂度:O(1) - 100.00%
     * 空间复杂度:O(1) - 88.54%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public int reverseBits2(int n) {
        n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);  // 这里必须使用>>>无符号右移，左移没有无符号，所以一定是<<
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2); // 16进制的计算必须熟练，每一位16进制对应4位二进制
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

}
