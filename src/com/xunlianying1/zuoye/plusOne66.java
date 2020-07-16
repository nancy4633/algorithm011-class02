package com.xunlianying1.zuoye;

// 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
// 你可以假设除了整数 0 之外，这个整数不会以零开头。
// 思路：
// 暴力求解：判断特殊情况9，以及连续的9。直到非9就+1
public class plusOne66 {
    /***
     * 暴力求解
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param digits
     * @return
     */
    public static int[] plusOne11(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

}
