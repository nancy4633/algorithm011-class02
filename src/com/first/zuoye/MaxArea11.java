package com.first.zuoye;

// 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
// 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai)和(i,0)。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

// 说明：你不能倾斜容器，且 n 的值至少为 2。

// 思路
// 求面积最大值。min值怎么求啊？
// 一维数组的坐标变换
public class MaxArea11 {

    /***
     * 暴力求解法，直接两层for循环去遍历得到最大的面积。
     * 毕竟是自己写的，第二天也只能记住这一个了哈哈哈哈，加油，还会更好的。
     *
     * @param height
     * @return
     */
    public static int maxArea1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if (max < area) {
                    max = area;
                }
            }
        }
        return max;
    }

    /***
     * 双指针法 - 3 - 时间复杂度和空间复杂度都是最好的。
     * 每一轮循环：先求面积，后选择下一轮的移动方向。跟我的习惯性思维不太一样。
     * 这里的亮点，而且也是最节省时间的部分就是，那个三元运算符，简直太赞了，完美。
     *
     * @param height
     * @return
     */
    public static int maxArea11(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while (i < j) {
            // 这里太赞了，牛！
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    /***
     * 双指针法 - 2
     * 每一轮循环：先求面积，后选择下一轮的移动方向。跟我的习惯性思维不太一样。
     *
     * @param height
     * @return
     */
    public static int maxArea111(int[] height) {
        int i = 0, j = height.length - 1, res = j * Math.min(height[i], height[j]);
        while (i < j) {
            res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
            // 唯一差一点儿的就是这个if判断，看起来就多，但是如果不知道？的人，确实就写不出完美的代码
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    public static int maxArea1111(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int nextHeight = height[i] < height[j] ?
                    height[i++] :
                    height[j--];
            max = Math.max(max, nextHeight*(j-i-1));
        }
        return max;
    }


    public static void main(String[] args) {
        int[] height = {1, 3, 4, 56, 7};
        System.out.println(maxArea11(height));
    }

}
