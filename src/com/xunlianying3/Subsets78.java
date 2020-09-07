package com.xunlianying3;

import java.util.ArrayList;
import java.util.List;

// 第一遍
// 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
// 说明：解集不能包含重复的子集。
public class Subsets78 {

    /**
     * 回溯
     * 时间复杂度:O() - 99.37%
     * 空间复杂度:O() - 32.04%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        if (nums == null || nums.length == 0) return lists;
        List<Integer> list = new ArrayList<>();
        process1(list, nums, 0);
        return lists;
    }

    List<List<Integer>> lists = new ArrayList<>();

    private void process1(List<Integer> list, int[] nums, int start) {
        lists.add(new ArrayList(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            process1(list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 递归
     * 时间复杂度:O() - 99.37%
     * 空间复杂度:O() - 93.96%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());
        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr) {{
                    add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }

    /**
     * 回溯
     * 时间复杂度:O() - 99.37%
     * 空间复杂度:O() - 48.47%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack3(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }

    List<List<Integer>> output = new ArrayList();
    int n, k;

    public void backtrack3(int first, ArrayList<Integer> curr, int[] nums) {
        if (curr.size() == k) output.add(new ArrayList(curr));
        for (int i = first; i < n; ++i) {
            curr.add(nums[i]);
            backtrack3(i + 1, curr, nums);
            curr.remove(curr.size() - 1);
        }
    }

    /**
     * 字典排序（二进制排序）
     * 时间复杂度:O() - 29.09%
     * 空间复杂度:O() - 98.60%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;
        String bitmask;
        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
            bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }
}
