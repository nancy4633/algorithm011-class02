package com.xunlianying3.zuoye;

import java.util.ArrayList;
import java.util.List;

// 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
// 思路：
// 回溯
public class Permute46 {

    /**
     * 回溯
     * 看到题目就想到用回溯，之前已经把递归和回溯练习了一个几天，以为很熟了，结果对于状态的回退还是找不准
     * 还是看了网上的解法才解出来
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 泛型递归
     * <p>
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute11(int[] nums) {
        return null;
    }
}
