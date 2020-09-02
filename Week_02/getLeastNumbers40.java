package com.xunlianying2;

import java.util.*;

// 第一遍
// 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
// 思路：
// 堆： 封装好的堆 ｜｜ 自己用数组实现二叉堆
public class getLeastNumbers40 {


    /**
     * 时间复杂度:O() - 99.49%
     * 空间复杂度:O() - 24.83%
     * 优点:
     * 缺点:
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        int j = partition(nums, lo, hi);
        if (j == k) return Arrays.copyOf(nums, j + 1);
        return j > k ? quickSearch(nums, lo, j - 1, k) : quickSearch(nums, j + 1, hi, k);
    }

    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v) ;
            while (--j >= lo && nums[j] > v) ;
            if (i >= j) break;
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

    /**
     * 大顶堆
     * 时间复杂度:O() - 36.65%
     * 空间复杂度:O() - 51.95%
     * 优点:
     * 缺点:
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers5(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;
    }

    /**
     * 计数排序
     * 时间复杂度:O() - 99.49%
     * 空间复杂度:O() - 83.76%
     * 优点:
     * 缺点:
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        int[] res = new int[k];
        int index = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && index < k) {
                res[index++] = num;
            }
            if (index == k) break;
        }
        return res;
    }

    /**
     * 二叉搜索树
     * 时间复杂度:O() - 12.39%
     * 空间复杂度:O() - 97.50%
     * 优点:
     * 缺点:
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers7(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        for (int num : arr) {
            if (cnt < k) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;
            }
            Map.Entry<Integer, Integer> entry = map.lastEntry();
            if (entry.getKey() > num) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (entry.getValue() == 1) {
                    map.pollLastEntry();
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }

        }
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            while (freq-- > 0) {
                res[idx++] = entry.getKey();
            }
        }
        return res;
    }
}
