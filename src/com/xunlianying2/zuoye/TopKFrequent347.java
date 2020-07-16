package com.xunlianying2.zuoye;

import java.util.*;

// 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
// 思路：
// 暴力求解
public class TopKFrequent347 {

    /****
     * 暴力求解
     * 遍历数组，key-整数元素，value-出现频次，取top？没有思路了，按照value排序？
     *
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent1(int[] nums, int k) {

        return null;
    }

    /****
     * 堆
     *
     * 时间复杂度：O(nlogk) k是堆的数目
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        int[] top_k = new int[k];
        for (int i = 0; i <= k && !heap.isEmpty(); i++) {
            top_k[i] = heap.poll();
        }
        return top_k;
    }

    /****
     * 桶排序
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent111(int[] nums, int k) {
        int[] ans = new int[nums.length];
        if (nums == null) {
            return ans;
        }
        // 记录每个元素的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        // 按照 map 中元素的频率来创建数组，高频率的元素位于数组最后边
        List<Integer>[] tmp = new List[nums.length + 1];
        for (int key : map.keySet()) {
            // 定义 i 来接收每个元素的频率值
            int i = map.get(key);
            if (tmp[i] == null) {
                tmp[i] = new ArrayList();
            }
            // 将对应频率的元素放入以频率为下标的数组中
            tmp[i].add(key);
        }
        // 逆向找出前 k 高频率的元素
        for (int i = tmp.length - 1, j = 0; i >= 0; i--) {
            if (tmp[i] == null) {
                continue;
            }
            for (int temp : tmp[i]) {
                ans[j++] = temp;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        topKFrequent111(nums, k);
    }
}
