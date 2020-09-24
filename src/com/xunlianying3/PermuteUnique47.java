package com.xunlianying3;

import java.util.*;

// 第三遍 先只看解法一。 因为有了flag所以不需要index， 因为有了result，所以递归条件也不是index的当前值，所以完全不需要index变量
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
        dfs1(new ArrayList<>(), new boolean[nums.length]);
        return results;
    }

    List<List<Integer>> results;
    int[] nums;

    public void dfs1(List<Integer> result, boolean[] flag) {
        if (result.size() == nums.length) { // 也可以写成：result.size() == nums.length 这样的话就可以删掉index了   // terminator
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) { // 遍历
            if (flag[i]) continue; // 剪枝
            if (i > 0 && nums[i - 1] == nums[i] && !flag[i - 1]) continue; // 剪枝 // 判断条件要理解含义。
            result.add(nums[i]); // process
            flag[i] = true; // process
            dfs1(result, flag); // drill down
            result.remove(result.size() - 1); // reverse state
            flag[i] = false; // reverse state
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
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length == 0) return results;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Deque<Integer> deque = new ArrayDeque<>(nums.length);
        dfs3(nums, 0, used, deque, results);
        return results;
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
