package com.xunlianying1;

// 删除排序数组中的重复项
// 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

// 思路：
// 暴力求解
// 因为时间复杂度要求是O(1)，所以个人觉得不需要考虑递归。
public class RemoveDuplicates26 {

    /****
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
        int i = 0, j = i + 1;
        while (j < len) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }

    public int removeDuplicates3(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                if (++i == j) continue;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
