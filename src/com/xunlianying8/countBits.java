package com.xunlianying8;

// 第四遍
// 给定一个非负整数num。对于0≤i≤num范围中的每个数字i ，计算其二进制数中的1的数目并将它们作为数组返回。
public class countBits {
    /**
     * @param num
     * @return
     */
    public int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        return result;
    }
}
