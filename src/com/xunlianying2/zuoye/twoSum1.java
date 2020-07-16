package com.xunlianying2.zuoye;

import java.util.Arrays;
import java.util.HashMap;

// 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

// 给定 nums = [2, 7, 11, 15], target = 9
// 因为 nums[0] + nums[1] = 2 + 7 = 9
// 所以返回 [0, 1]

// 因为不排除负整数的情况，所以并没办法提前过滤小于该和的情况
// 所以如果想在加法之前做优化，只能是确定好值，去做相等比较
// 毕竟做等法判断把加法之后再等法判断要节省时间

// 暴力求解：遍历遍历，求和，判断
// 锁定目标值： 遍历，减法，遍历，判断
public class twoSum1 {
    /**
     * 暴力求解：遍历遍历，求和，判断
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        // 初始化返回的数组
        int[] result = {0, -1};
        // 判断空的情况
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 遍历
        int temp = 0;
        for (result[0] = 0; result[0] < nums.length; result[0]++) {
            temp = target - nums[result[0]];
            for (result[1] = result[0] + 1; result[1] < nums.length; result[1]++) {
                if (temp == nums[result[1]]) {
                    return result;
                }
            }
        }
        return result;
    }

    /***
     * 使用哈希函数，把时间复杂度减小
     * 第一次真正的用hashmap写代码，还挺激动的，开心。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum111(int[] nums, int target) {
        // 使用hashmap存储数组，查找的时间复杂度会降低为O(1)，整个算法的时间复杂度会变为O(n)，空间复杂度会变为O(n)s
        HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int num = 0; num < length; num++) {
            if (tracker.containsKey(nums[num])) {
                int left = tracker.get(nums[num]);
                return new int[]{left, num};
            } else {
                tracker.put(target - nums[num], num);
            }
        }
        return new int[2];
    }

    /***
     * 这个题解看着很眼晕，太长了，为了锻炼自己，总要做一些自己不喜欢的，就试试吧。
     * 其实一开始看到题目，我也想到先用排序，然后再计算
     * 对于大数据量并且频繁需要查找未知数的，先做排序是最优解，然后再使用hash效率就会提升很多
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1111(int[] nums, int target) {
        if (nums == null)
            return null;
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        // 利用的是java数字的排序，先进行排序，就不需要遍历的去找和是9的了，可以使用技巧。
        Arrays.sort(nums2);
        int a = 0, b = 0;
        int start = 0, end = nums2.length - 1;
        //find two nums
        while (start < end) {
            int sum = nums2[start] + nums2[end];
            if (sum < target)
                start++;
            else if (sum > target)
                end--;
            else {
                a = nums2[start];
                b = nums2[end];
                break;
            }
        }
        //find the index of two numbers
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == a) {
                res[0] = i;
                break;
            }
        }
        if (a != b) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == b) {
                    res[1] = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == b && i != res[0]) {
                    res[1] = i;
                    break;
                }
            }
        }
        return res;
    }

    /***
     * 有点儿晕
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum11111(int[] nums, int target) {
        if (nums == null)
            return null;
        int length = nums.length;
        int[] nums2 = Arrays.copyOf(nums, length);
        // 利用的是java数字的排序，先进行排序，就不需要遍历的去找和是9的了，可以使用技巧。
        Arrays.sort(nums2);
        int a = -1, b = -1;
        int start = 0, end = nums2.length - 1;
        //find two nums
        while (start < end) {
            int sum = nums2[start] + nums2[end];
            if (sum < target)
                start++;
            else if (sum > target)
                end--;
            else {
                a = nums2[start];
                b = nums2[end];
                break;
            }
        }
        HashMap<Integer, Integer> test = new HashMap<Integer, Integer>();
        for (int num = 0; num < length; num++) {
            test.put(nums[num], num);
        }
        int re1 = test.get(a).intValue();
        test.remove(a, re1);
        int re2 = test.get(b).intValue();
        return new int[]{re1, re2};
    }

    public static void main(String[] args) {
        int[] nums = {3, 3};
        int target = 6;
        int[] result = {-1, -1};
        result = twoSum11111(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
