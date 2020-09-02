package com.xunlianying1;

import java.util.ArrayDeque;
import java.util.LinkedList;

// 第一遍
// 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
// 返回滑动窗口中的最大值。
// 进阶：
// 你能在线性时间复杂度内解决此题吗？
public class maxSlidingWindow239 {
    /**
     * 效率很高的原因猜测可能是没有使用比较复杂的工具类如linkedList等，简单操作消耗的时间少；
     * 如果不考虑这个原因，其实这个算法如果每次都要完全遍历k个元素，时间开销应该不会很低
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 77.02%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int length = nums.length;
        int[] result = new int[length - k + 1];
        int max = (int) Double.NEGATIVE_INFINITY;
        int max_index = -1, left = 0, right = k - 1;
        while (right < length) {
            if (max_index < left) {
                max = (int) Double.NEGATIVE_INFINITY;
                for (int i = left; i <= right; i++) {
                    if (max < nums[i]) {
                        max = nums[i];
                        max_index = i;
                    }
                }
            } else {
                if (max < nums[right]) { // 剪枝 // 新窗口，只需要用max与right节点对比就可以了。
                    max = nums[right];
                    max_index = right;
                }
            }
            result[left] = max;
            left++;
            right++;
        }
        return result;
    }

    /**
     * 时间复杂度:O() - 94.58%
     * 空间复杂度:O() - 73.24%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len - k + 1];
        int start = 0;
        int end = Math.min(start + k, len) - 1;
        int max = getMax(nums, start, end);
        int i = 0;
        result[i++] = max;
        while (end < len - 1) {
            end = end + 1;
            start = start + 1;
            if (nums[end] > max) {
                max = nums[end];
            } else if (nums[start - 1] == max) {
                max = getMax(nums, start, end);
            }
            result[i++] = max;
        }
        return result;
    }

    private int getMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(nums[i], max);
        }
        return max;
    }


    /**
     * 时间复杂度:O() - 92.87%
     * 空间复杂度:O() - 54.59%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return nums;
        int leftMax[] = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i % k == 0) max = Integer.MIN_VALUE;
            if (max < nums[i]) max = nums[i];
            leftMax[i] = Math.max(nums[i], max);
        }
        int rightMax[] = new int[n];
        max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (i % k == 0) max = Integer.MIN_VALUE;
            if (max < nums[i]) max = nums[i];
            rightMax[i] = Math.max(nums[i], max);
        }
        int result[] = new int[n - k + 1];
        for (int i = 0; i < result.length; i++) {
            int j = i + k - 1;
            result[i] = Math.max(rightMax[i], leftMax[j]);
        }
        return result;
    }

    /**
     * 动态规划
     * 时间复杂度:O(n) - 90.13%
     * 空间复杂度:O(n) - 57.59%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        int j;
        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            j = n - i - 1;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            output[i] = Math.max(left[i + k - 1], right[i]);
        }
        return output;
    }

    /**
     * 双向队列
     * 时间复杂度:O() -
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow6(int[] nums, int k) {
        int lo = 0, hi = 0;
        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        while (hi < nums.length) {
            if (hi - lo < k) {
                offer(q, nums, hi++);
            } else {
                res[lo] = nums[q.getFirst()];
                if (q.getFirst() == lo++) q.removeFirst();
            }
        }
        res[lo] = nums[q.getFirst()];
        return res;
    }

    private void offer(ArrayDeque<Integer> q, int[] nums, int i) {
        while (!q.isEmpty() && nums[q.getLast()] < nums[i]) {
            q.removeLast();
        }
        q.offer(i);
    }

    /**
     * 双向队列
     * 时间复杂度:O(n) - 82.37%
     * 空间复杂度:O(n) - 36.47%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow7(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int[] nums;

    public void clean_deque(int i, int k) {
        if (!deq.isEmpty() && deq.getFirst() == i - k) deq.removeFirst();
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) deq.removeLast();
    }

    /**
     * 双向队列
     * 时间复杂度:O() - 71.67%
     * 空间复杂度:O() - 85.12%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow8(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        LinkedList<Integer> queue = new LinkedList();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peek() <= i - k) queue.poll();
            if (i + 1 >= k) result[i + 1 - k] = nums[queue.peek()];
        }
        return result;
    }

    /**
     * 暴力法
     * 时间复杂度:O() - 8.26%
     * 空间复杂度:O() - 68.39%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow9(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int result[] = new int[n - k + 1];
        for (int i = 0; i < result.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                max = Math.max(max, nums[i + j]);
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * 大顶堆（自定义大顶堆）
     * 时间复杂度:O() - 84.55%
     * 空间复杂度:O() - 88.03%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow10(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) return nums;
        IndexMaxHeap indexMaxHeap = new IndexMaxHeap(k);
        for (int i = 0; i < k; i++) {
            indexMaxHeap.insert(i, nums[i]);
        }
        int[] res = new int[len - k + 1];
        for (int i = k; i < len; i++) {
            res[i - k] = indexMaxHeap.peekMaxValue();
            indexMaxHeap.change(i % k, nums[i]);
        }
        res[len - k] = indexMaxHeap.peekMaxValue();
        return res;
    }

    class IndexMaxHeap {
        private int[] data;
        private int count;
        private int capacity;
        private int[] indexes;
        private int[] reverse;

        public IndexMaxHeap(int capacity) {
            data = new int[capacity + 1];
            indexes = new int[capacity + 1];
            reverse = new int[capacity + 1];
            count = 0;
            this.capacity = capacity;
        }

        public int getSize() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public void insert(int i, int item) {
            assert count + 1 <= capacity;
            assert i + 1 >= 1 && i + 1 <= capacity;
            i += 1;
            data[i] = item;

            indexes[count + 1] = i;
            reverse[i] = indexes[count + 1];

            count++;
            shiftUp(count);
        }


        private void shiftUp(int k) {
            while (k > 1 && data[indexes[k / 2]] < data[indexes[k]]) {
                swap(indexes, k / 2, k);
                // 注意分析这行代码，即使上一行 indexes 的两个元素交换了位置，但是并没有改变他们的值
                // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
                swap(reverse, indexes[k / 2], indexes[k]);
                k /= 2;
            }
        }

        private void shiftUp1(int k) {
            while (k > 1 && data[indexes[k / 2]] < data[indexes[k]]) {
                swapIndexes(k / 2, k);
                k /= 2;
            }
        }

        private void swapIndexes(int index1, int index2) {
            if (index1 == index2) {
                return;
            }
            int temp = indexes[index1];
            indexes[index1] = indexes[index2];
            indexes[index2] = temp;

            reverse[indexes[index1]] = index2;
            reverse[indexes[index2]] = index1;
        }

        private void swap(int[] data, int index1, int index2) {
            if (index1 == index2) {
                return;
            }
            int temp = data[index1];
            data[index1] = data[index2];
            data[index2] = temp;
        }

        public int extractMax() {
            // 将此时二叉堆中的最大的那个数据删除（出队），返回的是数据，不是返回索引
            assert count > 0;
            int ret = data[indexes[1]];
            // 只要设计交换的操作，就一定是索引数组交换
            // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
            swap(indexes, 1, count);
            swap(reverse, indexes[1], indexes[count]);
            count--;
            shiftDown(1);
            return ret;
        }

        public int extractMax1() {
            // 将此时二叉堆中的最大的那个数据删除（出队），返回的是数据，不是返回索引
            assert count > 0;
            int ret = data[indexes[1]];
            // 只要设计交换的操作，就一定是索引数组交换
            // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
            swapIndexes(1, count);
            count--;
            shiftDown(1);
            return ret;
        }

        private void shiftDown(int k) {
            while (2 * k <= count) {
                int j = 2 * k;
                if (j + 1 <= count && data[indexes[j + 1]] > data[indexes[j]]) {
                    j = j + 1;
                }
                if (data[indexes[k]] >= data[indexes[j]]) {
                    break;
                }
                // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
                swap(indexes, k, j);
                swap(reverse, indexes[k], indexes[j]);

                k = j;
            }
        }

        private void shiftDown1(int k) {
            while (2 * k <= count) {
                int j = 2 * k;
                if (j + 1 <= count && data[indexes[j + 1]] > data[indexes[j]]) {
                    j = j + 1;
                }
                if (data[indexes[k]] >= data[indexes[j]]) {
                    break;
                }
                // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
                swapIndexes(k, j);
                k = j;
            }
        }

        public int extractMaxIndex() {
            assert count > 0;
            int ret = indexes[1] - 1;
            // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
            swap(indexes, 1, count);
            swap(reverse, indexes[1], indexes[count]);

            count--;
            shiftDown(1);
            return ret;
        }

        public int extractMaxIndex1() {
            assert count > 0;
            int ret = indexes[1] - 1;
            // 每一次交换了 indexes 索引以后，还要把 reverse 索引也交换
            swapIndexes(1, count);
            count--;
            shiftDown(1);
            return ret;
        }

        public int getItem(int i) {
            return data[i + 1];
        }

        public void change(int i, int item) {
            i = i + 1;
            data[i] = item;
            // 原先遍历的操作，现在就变成了这一步，是不是很酷
            int j = reverse[i];
            shiftDown(j);
            shiftUp(j);
        }

        public int peekMaxIndex() {
            if (this.count == 0) {
                throw new RuntimeException("堆里没有可以取出的元素");
            }
            // 注意：与用户认为的索引值有一个偏差
            return indexes[1] - 1;
        }

        public int peekMaxValue() {
            if (this.count == 0) {
                throw new RuntimeException("堆里没有可以取出的元素");
            }
            return data[indexes[1]];
        }
    }
}
