package com.xunlianying3;

import java.util.*;

// 第一遍 先只看解法一。
// 给定一个可包含重复数字的序列，返回所有不重复的全排列。
public class PermuteUnique47 {
    /**
     * 回溯
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.70%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        results = new ArrayList<>();
        Arrays.sort(nums);
        this.nums = nums;
        dfs2(new ArrayList<>(), new boolean[nums.length], 0);
        return results;
    }

    List<List<Integer>> results;
    int[] nums;

    public void dfs2(List<Integer> ll, boolean[] flag, int length) {
        if (length == nums.length) {
            results.add(new ArrayList<>(ll));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) continue;
            if (i > 0 && nums[i - 1] == nums[i] && flag[i - 1] == false) continue;
            ll.add(nums[i]);
            flag[i] = true;
            dfs2(ll, flag, length + 1);
            ll.remove(ll.size() - 1);
            flag[i] = false;
        }
    }

    /**
     * 回溯 + 剪枝 + Deque
     * 时间复杂度：O() - 75.80%
     * 空间复杂度：O() - 98.07%
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Deque<Integer> deque = new ArrayDeque<>(nums.length);
        dfs3(nums, 0, used, deque, res);
        return res;
    }

    private void dfs3(int[] nums, int depth, boolean[] used, Deque<Integer> deque, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(deque));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            deque.addLast(nums[i]);
            used[i] = true;
            dfs3(nums, depth + 1, used, deque, res);
            used[i] = false;
            deque.removeLast();
        }
    }
}
