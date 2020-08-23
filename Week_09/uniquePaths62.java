package com.xunlianying9.zuoye1;

// 第一遍
// 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
// 问总共有多少条不同的路径？
public class uniquePaths62 {
    /**
     * 动态规划（自下而上） + 二维数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 72.96%
     * 优点:
     * 缺点:
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        int[][] result = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    result[i][j] = 1;
                } else {
                    System.out.println("i=" + i + "j = " + j);
                    result[i][j] = result[i - 1][j] + result[i][j - 1];
                }
            }
        }
        return result[m][n];
    }

    /**
     * 动态规划（自下而上） + 一维数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 36.30%
     * 优点:
     * 缺点:
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[] result = new int[n + 1];
        result[1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                result[j] += result[j - 1];
            }
        }
        return result[n];
    }

}
