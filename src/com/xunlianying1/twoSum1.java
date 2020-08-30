package com.xunlianying1;

import java.util.Arrays;
import java.util.HashMap;

// 第二遍 - 重点
// 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
// 给定 nums = [2, 7, 11, 15], target = 9
// 因为 nums[0] + nums[1] = 2 + 7 = 9
// 所以返回 [0, 1]
public class twoSum1 {
    /**
     * 时间复杂度:O(n) - 99.60%
     * 空间复杂度:O(n) - 91.80%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int length = nums.length, index; // 居然把index写在外面更节省空间。
        for (int i = 0; i < length; i++) {
            if (hashMap.containsKey(nums[i])) {
                index = hashMap.get(nums[i]);
                return new int[]{index, i}; // 记住数组的定义方式
            } else {
                hashMap.put(target - nums[i], i);
            }
        }
        return new int[2]; // 记住数组的定义方式
    }

    /**
     * 时间复杂度:O(n) - 99.60%
     * 空间复杂度:O(n) - 96.48%
     * 优点: 对复制数组进行排序，在排序好的数组种查找目标值，最后在原始数组确定目标值的index
     * 缺点:
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int length = nums.length;
        int[] nums2 = Arrays.copyOf(nums, length);
        Arrays.sort(nums2); // 对复制的数组进行排序
        int left = 0, right = 0, start = 0, end = length - 1;
        while (start < end) {
            int sum = nums2[start] + nums2[end];
            if (sum < target) {
                start++;
            } else if (sum > target)
                end--;
            else {
                left = nums2[start];
                right = nums2[end];
                break;
            }
        }
        int[] result = new int[2];
        for (int i = 0; i < length; i++) {
            if (nums[i] == left) {
                result[0] = i;
                break;
            }
        }
        if (left != right) {
            for (int i = 0; i < length; i++) {
                if (nums[i] == right) {
                    result[1] = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (nums[i] == right && i != result[0]) {
                    result[1] = i;
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 暴力求解
     * 时间复杂度:O(n^2) - 23.53%
     * 空间复杂度:O(1) - 41.78%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
