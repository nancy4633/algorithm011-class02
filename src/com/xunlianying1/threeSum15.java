package com.xunlianying1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 第三遍 - 重点
// 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
// 注意：
// !!!答案中不可以包含重复的三元组。
// 示例：
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
// 满足要求的三元组集合为： [  [-1, 0, 1], [-1, -1, 2] ]
public class threeSum15 {
    /**
     * 时间复杂度:O() - 99.51%
     * 空间复杂度:O() - 52.30%
     * 优点: 第一位的-target已经提前算出来，这是比第三种方法快的原因，不需要每次都和后面两位加在一起，再判断要快很多。
     * 优点：相比于第二种解法，这个解法，把各个条件都分开判断，这样更节省时间和空间。
     * 缺点: 代码理解起来真的挺头疼，虽然判断条件很容易理解，但是合并起来非常晦涩。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 3) return result;
        int first = 0, max = nums.length - 2;
        Arrays.sort(nums);
        while (first < max) {
            if (nums[first] > 0) break;
            int target = -nums[first], second = first + 1, third = nums.length - 1; // 因为忘记重新初始化high = length-1，排查了好久，昨天就终止了，类似的问题一定要记下来，可以下次复习的时候再看。
            while (second < third) {
                int sum = nums[second] + nums[third];
                if (sum > target) {
                    third--;
                } else if (sum < target) {
                    second++;
                } else {
                    result.add(Arrays.asList(nums[first], nums[second], nums[third]));
                    do {
                        second++;
                    } while (second < third && nums[second] == nums[second - 1]); // 去重
                    do {
                        third--;
                    } while (second < third && nums[third] == nums[third + 1]); // 去重
                }
            }
            while (first < max && -target == nums[first]) { // 去重
                ++first;
            }
        }
        return result;
    }

    /**
     * 把不相等的if判断条件先判断，这样提高这道题的速度，因为这个题目，多数的情况是不等，所以先判断速度可以更快
     * 时间复杂度:O() - 99.09%
     * 空间复杂度:O() - 75.95%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 3) return result; // 这条语句居然也影响了速度
        Arrays.sort(nums);
        for (int m = 0; m < nums.length - 2; m++) {
            if (m == 0 || (m > 0 && nums[m] != nums[m - 1])) {
                int low = m + 1, high = nums.length - 1, target = -nums[m];
                while (low < high) {
                    if (nums[low] + nums[high] < target) {
                        low++;
                    } else if (nums[low] + nums[high] > target) {
                        high--;
                    } else {
                        result.add(Arrays.asList(nums[m], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 解法有点儿复杂，考虑要不要记住
     * 时间复杂度:O() - 99.83%
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0, posSize = 0, zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) minValue = v;
            if (v > maxValue) maxValue = v;
            if (v > 0) {
                posSize++;
            } else if (v < 0) {
                negSize++;
            } else {
                zeroSize++;
            }
        }
        if (zeroSize >= 3) result.add(Arrays.asList(0, 0, 0));
        if (negSize == 0 || posSize == 0) return result;
        if ((minValue << 1) + maxValue > 0) { // [-3, -2, 0, 2, 5, 6, 7, 8, 9]
            maxValue = -minValue << 1;
        } else if ((maxValue << 1) + minValue < 0) { // [-9, -8, -7, -6, -5, -2, 0, 2, 3]
            minValue = -maxValue << 1;
        }
        int[] sizes = new int[maxValue - minValue + 1];
        int[] poses = new int[posSize]; // 存放所有的正数（已去重）
        posSize = 0;
        int[] negs = new int[negSize]; // 存放所有的负数（已去重）
        negSize = 0;
        for (int v : nums) {
            if (v < minValue || v > maxValue) continue;
            if (sizes[v - minValue]++ != 0) continue;
            if (v > 0) {
                poses[posSize++] = v;
            } else if (v < 0) {
                negs[negSize++] = v;
            }
        }
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int posBegin = 0;
        int nv;
        for (int ni = negSize - 1; ni >= 0; ni--) {
            nv = negs[ni];
            int minPv = -nv >> 1;
            while (posBegin < posSize && poses[posBegin] < minPv) {
                posBegin++;
            }
            for (int pi = posBegin; pi < posSize; pi++) {
                int pv = poses[pi];
                int remain = -nv - pv; // 0 - nv - pv
                if (remain == nv) { // 还差一个nv，就凑够0
                    if (sizes[nv - minValue] > 1) result.add(Arrays.asList(nv, nv, pv));
                } else if (remain == pv) { // 还差一个pv，就凑够0
                    if (sizes[pv - minValue] > 1) result.add(Arrays.asList(nv, pv, pv));
                } else if (remain > nv && remain < pv) {
                    if (sizes[remain - minValue] > 0) result.add(Arrays.asList(nv, remain, pv));
                } else if (remain < nv) break; // 寻找绝对值更大的负数
            }
        }
        return result;
    }

    /**
     * 时间复杂度:O() - 84.33%
     * 空间复杂度:O() - 80.59%
     * 优点: 加入了剪枝和优化
     * 缺点: 需要重复的加加加
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            } else if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                if (nums[i] + nums[low] + nums[high] < 0) {
                    low++;
                } else if (nums[i] + nums[low] + nums[high] > 0) {
                    high--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    high--;
                    low++;
                    while (low < high && nums[low] == nums[low - 1]) low++;
                    while (low < high && nums[high] == nums[high + 1]) high--;
                }
            }
        }
        return result;
    }


    /**
     * 时间复杂度:O() - 69.62%
     * 空间复杂度:O() - 78.88%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int low = i + 1;
                int high = nums.length - 1;
                int sum = 0 - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] > sum) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 时间复杂度:O() - 69.62%
     * 空间复杂度:O() - 72.50%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum5(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
}
