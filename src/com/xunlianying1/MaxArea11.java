package com.xunlianying1;

// 第五遍 - 思路可以瞬间理清，还是很有进步的，但是具体实现容易和其它题目混淆。
// 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
// 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai)和(i,0)。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
// 说明：你不能倾斜容器，且 n 的值至少为 2。
public class MaxArea11 {

    /**
     * 爽之针法：和解法2相似，都用双指针，只是这个解法没有用到Math.max
     * 时间复杂度:O() - 99.19%
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int maxArea11(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                result = result > height[left] * (right - left) ? result : height[left] * (right - left);
                Math.max(left, right);
                // 上面的语句比这样写要快： result = Math.max(result, (right - left) * height[left]);
                // Math.max() 调用的就是(a >= b) ? a : b; 为什么会更快呢？ 估计是少了一层调用吧。
                left++;
            } else {
                result = result > height[right] * (right - left) ? result : height[right] * (right - left);
                right--;
            }
        }
        return result;
    }

    /***
     * 双指针法 + 三元运算
     * 时间复杂度:O() - 92.82%
     * 空间复杂度:O() - 86.48%
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            // max = Math.max((height[left] < height[right] ? Math.min(height[left++], height[right]) : Math.min(height[left], height[right--])) * (right - left), max);
            // 死记硬背！！！这个公式的实现方式，比我写的强了十万八千里，同样是三元运算符
            // 相较于方法一，主要就慢在Math.max
            max = Math.max(max, (right - left) * (height[left] < height[right] ? height[left++] : height[right--]));
        }
        return max;
    }

    /***
     * 双指针 + 二元运算符
     * 时间复杂度:O(n) - 67.50%
     * 空间复杂度:O(1) - 52.61%
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int max = 0, left = 0, right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if ((height[left] < height[right])) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
