package com.xunlianying3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 第一遍
// 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
// 众数（Mode）是指在统计分布上具有明显集中趋势点的数值，代表数据的一般水平。 也是一组数据中出现次数最多的数值，有时众数在一组数中有好几个。用M表示。
public class MajorityElement169 {
    /**
     * 投票优化
     * 时间复杂度:O() - 99.81%
     * 空间复杂度:O() - 98.55%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int majorityElement0(int[] nums) {
        int majority = nums[0], cnt = 0, quorum = nums.length >> 1;
        for (int i = 0; i < nums.length; i++) {
            if (majority == nums[i]) {
                if (++cnt > quorum) return majority;
            } else {
                if (--cnt == 0) majority = nums[i + 1];
            }
        }
        return majority;
    }

    /**
     * 投票
     * 时间复杂度:O() - 99.81%
     * 空间复杂度:O() - 75.06%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        int last = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == last) count++;
            else count--;
            if (count == 0) {
                i++;
                last = nums[i];
                count = 1;
            }
        }
        return last;
    }

    /**
     * 排序
     * 时间复杂度:O() - 77.43%
     * 空间复杂度:O() - 99.27%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 分治
     * 时间复杂度:O() - 77.43%
     * 空间复杂度:O() - 95.01%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        return majorityElementRec3(nums, 0, nums.length - 1);
    }

    private int countInRange3(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) count++;
        }
        return count;
    }

    private int majorityElementRec3(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec3(nums, lo, mid);
        int right = majorityElementRec3(nums, mid + 1, hi);
        if (left == right) return left;
        int leftCount = countInRange3(nums, left, lo, hi);
        int rightCount = countInRange3(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    /**
     * 随机化
     * 时间复杂度:O() - 77.43%
     * 空间复杂度:O() - 95.28%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int majorityElement4(int[] nums) {
        Random rand = new Random();
        int majorityCount = nums.length / 2;
        while (true) {
            int candidate = nums[randRange4(rand, 0, nums.length)];
            if (countOccurences4(nums, candidate) > majorityCount) return candidate;
        }
    }

    private int randRange4(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurences4(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) count++;
        }
        return count;
    }

    /**
     * Boyer-Moore 投票算法
     * 时间复杂度:O() -
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int majorityElement5(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 迭代 - hash
     * 时间复杂度：O(n) - 31.48%
     * 空间复杂度：O(n) - 29.14%
     *
     * @param nums
     * @return
     */
    public int majorityElement6(int[] nums) {
        Map<Integer, Integer> result = new HashMap();
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i < nums.length; i++) {
            result.put(nums[i], result.getOrDefault(nums[i], 0) + 1);
        }
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue()) maxEntry = entry;
        }
        return maxEntry.getKey();
    }

}
