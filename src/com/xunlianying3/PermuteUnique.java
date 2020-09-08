package com.xunlianying3;

import java.util.*;

// 第一遍 先只看解法一。
// 给定一个可包含重复数字的序列，返回所有不重复的全排列。
public class PermuteUnique {
    /**
     * 回溯
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 98.36%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        dfs1(nums, new boolean[nums.length], 0, new ArrayList<>());
        return results;
    }

    List<List<Integer>> results;

    public void dfs1(int[] nums, boolean[] flag, int length, List<Integer> result) {
        if (length == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) continue; // terminator
            if (i > 0 && nums[i - 1] == nums[i] && flag[i - 1] == false) continue; // terminator
            result.add(nums[i]); // process
            flag[i] = true; // process
            dfs1(nums, flag, length + 1, result); // drill down
            result.remove(result.size() - 1); // reverse state， 在回溯中经常会看到。
            flag[i] = false; // reverse state， 在回溯中经常会看到。
        }
    }
}
