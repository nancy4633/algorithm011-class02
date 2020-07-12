package com.first.zuoye;

// 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
// 注意：答案中不可以包含重复的三元组。
// 示例：
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
// 满足要求的三元组集合为：
// [
//  [-1, 0, 1],
//  [-1, -1, 2]
// ]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 思路：
// 暴力求解：迭代？先去重？后去重？先对原始数组排序+去重，接下来的结果就不会重复了，排序的时间复杂度是O(nlogn)，所以这额的时间复杂度也是O(nlogn)
// hash：递归？先去重？后去重？
// 双指针：
public class threeSum15 {
    /***
     * 迭代
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        // 先判断空值和大小小于三的数组
        int length = nums.length;
        if (nums == null || length < 3) {
            return result;
        }
        // 排序 - 可以降低时间复杂度，简化逻辑
        Arrays.sort(nums);
        // 遍历数组
        for (int i = 0; i < length - 2; i++) {
            // 因为已经按照由小到大进行排序，所以第一个数如果大于零，和就不可能等于零，直接停止遍历。
            if (nums[i] > 0) break;
            // 首位数字已经确定，接下来遍历后两个数字。
            for (int j = i + 1, k = length - 1; j < length - 1 && j < k; ) {
                if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List temp = Arrays.asList(nums[i], nums[j], nums[k]);
                    if (!result.contains(temp)) {
                        result.add(temp);
                    }
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    i++;
                    k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                }
            }
        }
        return result;
    }

    /***
     * 遍历
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：
     * @param num
     * @return
     */
    public List<List<Integer>> threeSum11(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
