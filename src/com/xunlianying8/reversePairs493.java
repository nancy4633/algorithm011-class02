package com.xunlianying8;

// 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
// 你需要返回给定数组中的重要翻转对的数量。
// 注意:
// 给定数组的长度不会超过50000。
// 输入数组中的所有数字都在32位整数的表示范围内。
public class reversePairs493 {
    /**
     * 归并排序
     * 时间复杂度:O() - 92.88%
     * 空间复杂度:O() - 32.63%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int reversePairs1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int left, int right) {
        if (right <= left) return 0;
        int mid = (left + right) >> 1;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int[] cache = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, tmp = left;
        while (j <= right) {
            while (tmp <= mid && nums[tmp] <= 2 * (long) nums[j]) tmp++;
            while (i <= mid && nums[i] < nums[j]) cache[k++] = nums[i++];
            cache[k++] = nums[j++];
            count += mid - tmp + 1;
        }
        while (i <= mid) cache[k++] = nums[i++];
        System.arraycopy(cache, 0, nums, left, right - left + 1);
        return count;
    }
}
