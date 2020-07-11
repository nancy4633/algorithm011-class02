package com.third.zuoye;

import java.util.*;

// 给定一个可包含重复数字的序列，返回所有不重复的全排列。
// 思路：
// 回溯 + 剪枝， 目前看来剪枝已经是回溯的左膀右臂了
// 因为回溯过程中有很多不需要遍历到底的条件，所以加入剪枝可以减少很多不必要的递归。
public class PermuteUnique47 {

    /**
     * 回溯 + 剪枝
     * 第一遍的回溯算法都没写成功过。
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique1(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; ++i) {
            if (used[i]) {
                continue;
            }
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, used, path, res);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }
}
