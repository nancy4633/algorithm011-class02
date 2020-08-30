package com.xunlianying1;

import java.util.Arrays;

// 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
// 说明：
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
// 思路：
// 十五分钟什么思路都没有，一直拘泥于怎么挪动nums1的数据
// 合并后排序～代码简洁
// 双指针 / 从前往后 - 其实就是借助临时数组存储nums1
// 双指针 / 从后往前 - 就是先把后面的填上，这种解法也是因为这种情况才想出来的，挺妙的。有点儿像人脑的思维模式。
public class merge88 {

    /***
     * 合并后排序
     *
     * 时间复杂度: O((n + m)\log(n + m))O((n+m)log(n+m))
     * 空间复杂度: O(1)O(1)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /***
     * 双指针 / 从前往后
     *
     * 时间复杂度: O(n + m)O(n+m)
     * 空间复杂度: O(m)O(m)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge11(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    /***
     * 双指针 / 从后往前
     *
     * 时间复杂度: O(n + m)O(n+m)
     * 空间复杂度: O(1)O(1)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge111(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {

    }
}
