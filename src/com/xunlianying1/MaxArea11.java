package com.xunlianying1;

// 第三遍 - 重点
// 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
// 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai)和(i,0)。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
// 说明：你不能倾斜容器，且 n 的值至少为 2。

// 思路
// 求面积最大值。min值怎么求啊？
// 一维数组的坐标变换
public class MaxArea11 {
    /***
     * 双指针法 + 三元运算
     * 时间复杂度:O() - 92.58%
     * 空间复杂度:O() - 5.29%
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int max = 0, left = 0, right = height.length - 1;
        while (left < right) {
            // max = Math.max((height[left] < height[right] ? Math.min(height[left++], height[right]) : Math.min(height[left], height[right--])) * (right - left), max);
            // 死记硬背！！！这个公式的实现方式，比我写的强了十万八千里，同样是三元运算符
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
