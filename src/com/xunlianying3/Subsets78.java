package com.xunlianying3;

import java.util.ArrayList;
import java.util.List;

// 第六遍 - 还需要刻苦练习 // 习惯性的在dfs第一句就判断index， 这里为什么不用判断index呢？ index是控制result长度的？ 这里就不需要判断了吗？需要再理解！！！
// 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
// 说明：解集不能包含重复的子集。
public class Subsets78 {
    /**
     * 回溯 + DFS
     * 时间复杂度:O() - 99.37%
     * 空间复杂度:O() - 99.16%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) { // 不需要排序：没要求是否包含重复元素
        results1 = new ArrayList<>();
        if (nums == null || nums.length == 0) return results1;
        dfs1(nums, 0, new ArrayList<>());
        return results1;
    }

    List<List<Integer>> results1;

    private void dfs1(int[] nums, int index, List<Integer> result) {
        results1.add(new ArrayList(result)); // 不需要在写index的terminator了
        for (int i = index; i < nums.length; i++) {
            result.add(nums[i]);
            // 因为这里传递了result，所以传递i，不需要传递index，像permute没传递result，就需要传递index了。这是递归的规矩吧。
            dfs1(nums, i + 1, result); // 全排列用的是index， 子集用的是i。//
            result.remove(result.size() - 1);
        }
    }

    /**
     * 遍历
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
     * 回溯 - 利用了之前的n个数中取k个数的组合，解法一样，只是主函数加了一层for循环，保证每种解法都遍历到。
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
            backtrack3(0, new ArrayList<>(), nums);
        }
        return results2;
    }

    List<List<Integer>> results2 = new ArrayList();
    int n, k;

    public void backtrack3(int first, ArrayList<Integer> result, int[] nums) {
        if (result.size() == k) results2.add(new ArrayList(result));
        for (int i = first; i < n; ++i) {
            result.add(nums[i]);
            backtrack3(i + 1, result, nums);
            result.remove(result.size() - 1);
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
