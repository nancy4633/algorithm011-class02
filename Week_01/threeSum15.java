package com.xunlianying1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 第一遍 - 重点
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
        int length = nums.length, i = 0;
        if (length < 3) return result;
        Arrays.sort(nums);
        while (i < length) {
            if (nums[i] > 0) break;
            int target = -nums[i], low = i + 1, high = length - 1; // 因为忘记重新初始化high = length-1，排查了好久，昨天就终止了，类似的问题一定要记下来，可以下次复习的时候再看。
            while (low < high) {
                int sum = nums[low] + nums[high];
                if (sum > target) {
                    high--;
                } else if (sum < target) {
                    low++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    do {
                        low++;
                    } while (low < high && nums[low] == nums[low - 1]);
                    do {
                        high--;
                    } while (low < high && nums[high] == nums[high + 1]);
                }
            }
            while (i < length && -target == nums[i]) {
                ++i;
            }
        }
        return result;
    }

    /**
     * 把不相等的if判断条件先判断，这样提高这道题的速度，因为这个题目，多数的情况是不等，所以先判断速度可以更快
     * 时间复杂度:O() - 99.04%
     * 空间复杂度:O() - 29.86%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int low = i + 1, high = length - 1, target = -nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] < target) {
                        low++;
                    } else if (nums[low] + nums[high] > target) {
                        high--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
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
