package com.xunlianying1;

// 第二遍
// 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
public class rotate189 {
    /**
     * 反转数组
     * 根据反转的数组大小，达到移动的目的
     * 时间复杂度：O(n) - 100.00% - 执行n次交换操作
     * 空间复杂度：O(1) - 75.29% - 只有一个临时变量
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 环状替换 - 按照模值相等，来决定移动的目的地址，遍历整个数组。
     * 时间复杂度：O(n) - 100.00% - 一层for循环
     * 空间复杂度：O(1) - 86.19%
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int len = nums.length, count = 0, pos1, pos2, cur, next;
        k %= len;
        for (int i = 0; count < len; i++) {
            pos1 = i;
            cur = nums[i];
            do {
                pos2 = (pos1 + k) % len;
                next = nums[pos2];
                nums[pos2] = cur;
                cur = next;
                pos1 = pos2;
                count++;
            } while (i != pos1);
        }
    }

    /**
     * 临时数组 - copy原来的数组，相当于移动的时候保留原始值
     * 时间复杂度：O(n) - 100.00% - 一层for循环
     * 空间复杂度：O(n) - 33.71% - 新建数组
     *
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1 || k <= 0) return;
        int[] newnums = nums.clone();
        for (int i = 0; i < len; i++) {
            nums[(i + k) % len] = newnums[i];
        }
    }
}
