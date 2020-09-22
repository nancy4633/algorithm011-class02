package com.xunlianying2;

import java.util.*;

// 第三遍 - 重点！！！大顶堆
// 自定义类MyHashMap，实现大顶堆
// PriorityQueue内部int[]  ：  PriorityQueue<int[]> queue = new PriorityQueue<int[]>((n1, n2) -> (n1[1] - n2[1]));
// PriorityQueue内部HashMap  PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));
// 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
// priorityqueue的poll()是移除第一个元素。所以这里面一定是用小顶堆。好生气哦。
public class TopKFrequent347 {
    /**
     * 二叉搜索树： 优点队列 + 自定义类MyHashMap（二叉搜索树）
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 28.06%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent1(int[] nums, int k) {
        MyHashMap map = new MyHashMap(nums.length);
        for (int num : nums) {
            map.increase(num);
        }
        List<MyEntry> entries = map.entries;
        PriorityQueue<MyEntry> heap = new PriorityQueue<>(k, (n1, n2) -> (n1.value - n2.value));
        for (int i = 0; i < k; i++) {
            heap.add(entries.get(i));
        }
        for (int i = k; i < entries.size(); i++) {
            MyEntry entry = entries.get(i);
            if (entry.value > heap.peek().value) {
                heap.poll();
                heap.add(entry);
            }
        }
        int[] ans = new int[k];
        Iterator<MyEntry> iterator = heap.iterator();
        for (int i = 0; i < k; i++) {
            ans[i] = iterator.next().key;
        }
        return ans;
    }

    /**
     * 桶排序 (hashmap + list)
     * 时间复杂度：O(n) - 98.28%
     * 空间复杂度：O(n) - 69.43%
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap();
        for (int temp : nums) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        List<Integer>[] list = new List[nums.length + 1];
        for (int num : map.keySet()) {
            int count = map.get(num);
            if (list[count] == null) list[count] = new ArrayList();
            list[count].add(num);
        }
        for (int i = nums.length, j = 0; i >= 0 && j < k; i--) {
            if (list[i] == null) continue;
            for (int temp : list[i]) {
                result[j++] = temp;
            }
        }
        return result;
    }

    /**
     * 大顶堆 - 优先队列（内部是int[]）
     * 时间复杂度:O() - 87.76%
     * 空间复杂度:O() - 81.11%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((n1, n2) -> (n1[1] - n2[1]));
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (heap.size() == k) {
                if (heap.peek()[1] < count) {
                    heap.poll();
                    heap.offer(new int[]{num, count});
                }
            } else {
                heap.offer(new int[]{num, count});
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; ++i) {
            result[i] = heap.poll()[0];
        }
        return result;
    }

    /**
     * 大顶堆 - 优先队列(内部是hashmap)
     * 时间复杂度：O(nlogk) k是堆的数目 - 53.26%
     * 空间复杂度：O(n) - 97.59%
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent4(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (int n : map.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }
        int[] result = new int[k];
        for (int i = 0; i <= k && !heap.isEmpty(); i++) {
            result[i] = heap.poll();
        }
        return result;
    }
}

class MyEntry {
    public int key, value, hash;
    public MyEntry left;
    public MyEntry right;

    public MyEntry(int key) {
        this.key = key;
        this.hash = key < 0 ? -key : key;
        this.value = 1;
    }
}

class MyHashMap {
    public MyEntry[] buckets;
    public List<MyEntry> entries;

    public MyHashMap(int size) {
        buckets = new MyEntry[size < 17 ? 17 : size];
        entries = new ArrayList<>(size);
    }

    public void increase(int key) {
        int hash = key < 0 ? -key : key;// 使用key的绝对值作为哈希值
        int bucketIndex = hash % buckets.length;//哈希函数：哈希值 % 桶的数量
        MyEntry entry = buckets[bucketIndex];
        if (entry == null) {
            buckets[bucketIndex] = new MyEntry(key);
            entries.add(buckets[bucketIndex]);
            return;
        }
        MyEntry parent = null;
        while (entry != null) {
            if (entry.key == key) {
                entry.value += 1;
                return;
            }
            parent = entry;
            entry = hash < entry.hash ? entry.left : entry.right;
        }
        if (hash < parent.hash) {
            parent.left = new MyEntry(key);
            entries.add(parent.left);
        } else {
            parent.right = new MyEntry(key);
            entries.add(parent.right);
        }
    }
}