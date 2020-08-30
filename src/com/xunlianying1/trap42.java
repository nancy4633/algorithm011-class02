package com.xunlianying1;

import java.util.Stack;

// 第二遍
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
// 思路：
// 按列
// 动态规划
// 双指针
// 栈
public class trap42 {

    /***
     * 按列求
     *
     * @param height
     * @return
     */
    public int trap11(int[] height) {
        int sum = 0;
        int max_left;
        int max_right;
        int min;
        for (int i = 1; i < height.length - 1; i++) {
            max_left = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) max_left = height[j];
            }
            max_right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) max_right = height[j];
            }
            min = Math.min(max_left, max_right);
            if (min > height[i]) sum = sum + (min - height[i]);
        }
        return sum;
    }

    /***
     * 动态规划
     *
     * @param height
     * @return
     */
    public int tra111(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /***
     * 双指针
     *
     * @param height
     * @return
     */
    public int trap1111(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    /***
     * 栈
     *
     * @param height
     * @return
     */
    public int trap11111(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

}
