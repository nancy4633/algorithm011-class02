package com.xunlianying2;

import java.util.*;

// 第四遍 - 重点！！！大顶堆，优先记住解法2、3、4，最后有时间再看看解法1。
// 虽然解法1效率最高，但是实现过于复杂，面试时候应该只需要讲解原理就可以了，先不去记。
// 解法2：List[]数组实现
// 解法3：PriorityQueue内部int[]  ：  PriorityQueue<int[]> queue = new PriorityQueue<int[]>((n1, n2) -> (n1[1] - n2[1]));
// 解法4：PriorityQueue内部HashMap的key值  PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
// 解法1：自定义类MyHashMap和MyEntry，实现大顶堆，不看不看！写的太繁琐了
// 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
// priorityqueue的poll()是移除第一个元素。所以这里面一定是用小顶堆。好生气哦。p开头的都是First。
public class TopKFrequent347 {
    /**
     * 桶排序 (hashmap + list) -
     * 相同计数的数字放到同一个桶里（数组的list里）数组相当于一系列的桶，数组由list<Integer>组成
     * list<Integer>就相当于是一个桶
     * 时间复杂度：O(n) - 98.28%
     * 空间复杂度：O(n) - 97.61%
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] listArray = new List[nums.length + 1];
        for (int num : map.keySet()) {
            int count = map.get(num);
            if (listArray[count] == null) listArray[count] = new ArrayList();
            listArray[count].add(num);
        }
        for (int m = nums.length, n = 0; m >= 0 && n < k; m--) {
            if (listArray[m] == null) continue;
            for (int temp : listArray[m]) {
                result[n++] = temp;
            }
        }
        return result;
    }

    /**
     * 大顶堆 - 优先队列（内部是int[]）  int[0]是num值， int[1]是出现的次数(count) 比较的是int[1] ，结果存的是int[0]
     * 时间复杂度:O() - 88.35%
     * 空间复杂度:O() - 99.93%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent3(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((n1, n2) -> (n1[1] - n2[1])); // 这里为什么是数组的第二个，不是很理解
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { // 也可以写成 for (int num : map.keySet())
            int count = entry.getValue();
            if (heap.size() == k) {
                if (heap.peek()[1] >= count) continue;
                heap.poll();
            }
            heap.offer(new int[]{entry.getKey(), count});
        }
        for (int i = 0; i < k; ++i) {
            result[i] = heap.poll()[0];
        }
        return result;
    }

    /**
     * 大顶堆 - 优先队列(内部是hashmap) - 先对比 比先入堆再出堆 更快，但是比较消耗存储空间。
     * 时间复杂度：O(nlogk) k是堆的数目 - 60.28%
     * 空间复杂度：O(n) - 97.59%
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent4(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (int num : map.keySet()) {
            if (heap.size() == k) {
                if (map.get(heap.peek()) > map.get(num)) continue;
                heap.poll();
            }
            heap.offer(num);
        }
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }
        return result;
    }

    /**
     * 二叉搜索树： 优点队列 + 自定义类MyHashMap（二叉搜索树），这个方法过于复杂，暂时回避。
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