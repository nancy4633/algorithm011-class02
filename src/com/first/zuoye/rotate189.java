package com.first.zuoye;

// 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
//
// 思路：
// 暴力求解：这个没想到，居然是一位一位的挪
// 临时数组：这个是自己想到的，由于数据量不大，所以效率很高
// 环状替换：模值相等的思路还是很巧妙的
// 反转数组：牛，这个思路完全吃惊到了，毫不夸张
public class rotate189 {

    /****
     * 暴力求解：
     * 每个数组移动一位，移动一圈后，再来k-1圈，所以，这种非一步到位的算法，时间复杂度就是高
     *
     * 时间复杂度：O(n^2) 两层for循环
     * 空间复杂度：O(n) 新建数组
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {

    }

    /**
     * 临时数组 - copy原来的数组，相当于移动的时候保留原始值
     * <p>
     * 时间复杂度：O(n) 一层for循环
     * 空间复杂度：O(n) 新建数组
     *
     * @param nums
     * @param k
     */
    public void rotate11(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0) {
            return;
        }
        int length = nums.length;
        int[] newnums = nums.clone();
        for (int i = 0; i < length; i++) {
            nums[(i + k) % length] = newnums[i];
        }
    }

    /***
     * 环状替换
     * 按照模值相等，来决定移动的目的地址，遍历整个数组。
     *
     * 时间复杂度：O(n) 一层for循环
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate111(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /***
     * 反转数组
     * 根据反转的数组大小，达到移动的目的
     *
     * 时间复杂度：O(n) - 执行n次交换操作
     * 空间复杂度：O(1) - 只有一个临时变量
     *
     * @param nums
     * @param k
     */
    public void rotate1111(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

    }
}
