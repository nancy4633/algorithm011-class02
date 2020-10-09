package com.xunlianying1;

// 第一遍
// 删除排序数组中的重复项
// 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
public class RemoveDuplicates26 {

    /**
     * 暴力求解：相同-》第一个指针不动，第二个指针后移；不相同-》第一、二指针都后移一位。
     * 时间复杂度:O(n) - 98.49%
     * 空间复杂度:O(1) - 95.32%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int removeDuplicates1(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len;
        int m = 0, n = m + 1;
        while (n < len) {
            if (nums[m] != nums[n]) {
                nums[m + 1] = nums[n];
                m++;
            }
            n++;
        }
        return m + 1;
    }

    /**
     * 时间复杂度:O() - 98.23%
     * 空间复杂度:O() - 57.33%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if (len < 1) return 0;
        int p = 0, q = 1;
        while (q < len) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 时间复杂度:O() - 98.23%
     * 空间复杂度:O() - 7.63%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int removeDuplicates3(int[] nums) {
        int m = 0;
        for (int n = 1; n < nums.length; n++) {
            if (nums[m] != nums[n]) {
                if (++m == n) continue;
                nums[m] = nums[n];
            }
        }
        return m + 1;
    }
}
