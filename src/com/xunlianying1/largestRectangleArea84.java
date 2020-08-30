package com.xunlianying1;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

// 第一遍
// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
public class largestRectangleArea84 {
    /**
     * 双指针
     * 时间复杂度:O() - 99.58%
     * 空间复杂度:O() - 39.30%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = -1;
        right[len - 1] = len;
        int res = 0, tmp;
        for (int i = 1; i < len; i++) {
            tmp = i - 1;
            while (tmp >= 0 && heights[tmp] >= heights[i]) {
                tmp = left[tmp];
            }
            left[i] = tmp;
        }
        for (int i = len - 2; i >= 0; i--) {
            tmp = i + 1;
            while (tmp < len && heights[tmp] >= heights[i]) {
                tmp = right[tmp];
            }
            right[i] = tmp;
        }
        for (int i = 0; i < len; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

    /**
     * Deque(ArrayDeque)
     * 时间复杂度:O() - 90.58%
     * 空间复杂度:O() - 75.54%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int res = 0, len = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] new_heights = new int[len + 2];
        for (int i = 1; i < len + 1; i++) {
            new_heights[i] = heights[i - 1];
        }
        for (int i = 0; i < len + 2; i++) {
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * Deque(ArrayDeque)
     * 时间复杂度:O() - 90.58%
     * 空间复杂度:O() - 81.89%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int res = 0;
        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;
        Deque<Integer> deque = new ArrayDeque<>(len);
        deque.addLast(0);
        int curHeight = 0, curWidth = 0;
        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[deque.peekLast()]) {
                curHeight = heights[deque.pollLast()];
                curWidth = i - deque.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            deque.addLast(i);
        }
        return res;
    }

    /**
     * Stack
     * 时间复杂度:O() - 83.94%
     * 空间复杂度:O() - 59.50%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 栈
     * 时间复杂度:O(n) - 67.32%
     * 空间复杂度:O(n) - 51.74%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea5(int[] heights) {
        int len = heights.length;
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }
                int curWidth;
                if (stack.isEmpty()) {
                    curWidth = i;
                } else {
                    curWidth = i - stack.peekLast() - 1;
                }
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                stack.pollLast();
            }
            int curWidth;
            if (stack.isEmpty()) {
                curWidth = len;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }
        return res;
    }

    /**
     * 暴力解法
     * 时间复杂度:O() - 18.22%
     * 空间复杂度:O() - 16.83%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea6(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int res = 0, left, right, curHeight;
        for (int i = 0; i < len; i++) {
            left = i;
            curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }
            right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }
            res = Math.max(res, (right - left + 1) * curHeight);
        }
        return res;
    }
}
