package com.xunlianying3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 第一遍
// 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
public class Permute46 {

    /**
     * 回溯
     * 时间复杂度:O() - 99.73%
     * 空间复杂度:O() - 95.15%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute0(int[] nums) {
        p0(0, nums);
        return listNums;
    }

    private List<List<Integer>> listNums = new ArrayList<>();

    public void p0(int start, int[] nums) {
        if (start >= nums.length - 1) {
            List<Integer> nnnn = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                nnnn.add(nums[i]);
            }
            listNums.add(nnnn);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap0(i, start, nums);
            p0(start + 1, nums);
            swap0(i, start, nums);
        }
    }

    public void swap0(int source, int target, int nums[]) {
        int temp = nums[target];
        nums[target] = nums[source];
        nums[source] = temp;
    }

    /**
     * 回溯 - 构建一棵解空间树
     * 时间复杂度:O() - 99.73%
     * 空间复杂度:O() - 75.75%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backTrack1(0, list, nums);
        return list;
    }

    public void backTrack1(int t, List<List<Integer>> list, int[] x) {
        if (t == x.length) {
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < x.length; i++) {
                arr.add(x[i]);
            }
            list.add(arr);
        } else {
            for (int i = t; i < x.length; i++) {
                swap1(x, i, t);
                backTrack1(t + 1, list, x);
                swap1(x, i, t);
            }
        }
    }

    public void swap1(int[] x, int m, int n) {
        int temp = x[m];
        x[m] = x[n];
        x[n] = temp;
    }

    /**
     * 回溯
     * 时间复杂度：O() - 82.93%
     * 空间复杂度：O() - 98.01%
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack2(res, nums, new ArrayList<Integer>(), visited);
        return res;
    }

    private void backtrack2(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack2(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 递归 + DFS
     * 时间复杂度：O() - 82.93%
     * 空间复杂度：O() - 88.10%
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute3(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs3(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs3(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) continue;
            path.addLast(nums[i]);
            used[i] = true;
            dfs3(nums, len, depth + 1, path, used, res);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 回溯 + DFS
     * 时间复杂度:O() - 82.93%
     * 空间复杂度:O() - 71.49%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute4(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        dfs4(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs4(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(path);
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) continue;
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(nums[i]);
            boolean[] newUsed = new boolean[len];
            System.arraycopy(used, 0, newUsed, 0, len);
            newUsed[i] = true;
            dfs4(nums, len, depth + 1, newPath, newUsed, res);
        }
    }
}
