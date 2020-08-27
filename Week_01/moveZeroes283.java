package com.xunlianying1;

// 第二遍
// 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
// 说明:
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。

// 思路：
// 1.暴力求解 - 遍历数组，遇到零就后移，时间复杂度-O(n^2)，空间复杂度O(1)
// 2.置位删除，先遍历并标记所有零，最后移动零，时间复杂度-O(n^2)，空间复杂度O(1)
// 3.置位删除，先遍历并标记所有零，最后移动所有非零到前面，后面补零，时间复杂度-O(n^2)，空间复杂度O(1)
// 4.冒泡的思想，新建一个大小相等的数组，遍历所有非零放到新数组，，后面补零，时间复杂度-O(n)，空间复杂度O(n)

// 注意：
// 初始判断要加
public class moveZeroes283 {
    /**
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 96.56%
     * 优点:
     * 缺点:
     *
     * @param nums
     */
    public void moveZeroes11(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) nums[insertPos++] = num;
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    /**
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(1) - 5.37%
     * 优点:
     * 缺点:
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num = 0; num < nums.length; num++) {
            if (nums[num] != 0 && num != insertPos) {
                nums[insertPos] = nums[num];
                nums[num] = 0;
                insertPos++;
            } else if (nums[num] != 0) {
                insertPos++;
            }
        }
    }
}
