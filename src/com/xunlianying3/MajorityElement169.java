package com.xunlianying3;

import java.util.HashMap;
import java.util.Map;

// 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
// 思路：
// 递归
// 迭代
// 概念：
// 众数（Mode）是指在统计分布上具有明显集中趋势点的数值，代表数据的一般水平。 也是一组数据中出现次数最多的数值，有时众数在一组数中有好几个。用M表示。
public class MajorityElement169 {

    /***
     * 递归
     * 使用map存储
     *
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {


        return 0;
    }

    /***
     * 迭代 - hash
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElement11(int[] nums) {
        Map<Integer, Integer> result = new HashMap();
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i < nums.length; i++) {
            if (result.containsKey(nums[i])) {
                result.put(nums[i], (result.get(nums[i]) + 1));
            } else {
                result.put(nums[i], 1);
            }
        }
        // 这里的entry也可以用两个int变量代替，看上去没那么优雅，但是我觉得更容易写。
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    /***
     * Boyer-Moore 算法
     *
     * 时间复杂度：O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     * 空间复杂度：O(1)。Boyer-Moore 算法只需要常数级别的额外空间。
     *
     * @param nums
     * @return
     */
    public int majorityElement111(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            } else {
                count += (num == candidate) ? 1 : -1;
            }
        }
        return candidate;
    }
}
