package com.xunlianying3;

import java.util.*;

// 第一遍
// 给定一个可包含重复数字的序列，返回所有不重复的全排列。
public class PermuteUnique47 {

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
    public List<List<Integer>> permuteUnique2(int[] nums) {
        this.nums = nums;
        list = new ArrayList<>();
        //切记必须要先排序啊！！！！！！这样只有相邻的才可能相等，才可以判断去除！！！！！
        Arrays.sort(nums);
        List<Integer> ll = new ArrayList<>();
        boolean[] flag = new boolean[nums.length];
        dfs2(ll, flag, 0);
        return list;
    }

    List<List<Integer>> list;
    int[] nums;

    public void dfs2(List<Integer> ll, boolean[] flag, int length) {
        if (length == nums.length) {
            list.add(new ArrayList<>(ll));
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
     * 回溯 + 剪枝
     * 时间复杂度：O() - 75.87%
     * 空间复杂度：O() - 50.49%
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique3(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs3(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs3(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; ++i) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            path.addLast(nums[i]);
            used[i] = true;
            dfs3(nums, len, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }
}
