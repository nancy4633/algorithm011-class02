package com.xunlianying1;

import java.util.Arrays;

// 第二遍
// 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
// 说明：
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
public class merge88 {
    /**
     * 双指针 / 从前往后
     * 时间复杂度: O(n + m)O(n+m) - 100.00%
     * 空间复杂度: O(m)O(m) - 91.90%
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3 = new int[m];
        System.arraycopy(nums1, 0, nums3, 0, m); // 同：int[] nums3 = Arrays.copyOf(nums1, m); 代码简洁但是底层还是调用System.arraycopy
        int p1 = 0, p3 = 0, p2 = 0;
        while ((p3 < m) && (p2 < n)) nums1[p1++] = (nums3[p3] < nums2[p2]) ? nums3[p3++] : nums2[p2++];
        if (p3 < m) System.arraycopy(nums3, p3, nums1, p1, m - p3); // 同：while (p2 < n) nums1[p1++] = nums2[p2++];
        if (p2 < n) System.arraycopy(nums2, p2, nums1, p1, n - p2); // 同：while (p3 < m) nums1[p1++] = nums3[p3++];
    }

    /**
     * 双指针 / 从后往前
     * 时间复杂度: O(n + m)O(n+m) - 100.00%
     * 空间复杂度: O(1)O(1) - 24.09%
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * 合并后排序
     * 时间复杂度: O((n + m)\log(n + m))O((n+m)log(n+m)) - 23.70%
     * 空间复杂度: O(1)O(1) - 49.92%
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
