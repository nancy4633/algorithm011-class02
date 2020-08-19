package com.xunlianying8.zuoye1;

// 第二次
// n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后彼此之间不能相互攻击。
// 给定一个整数n，返回n皇后不同的解决方案的数量。
// 提示：
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走1或N-1步，可进可退。
public class totalNQueens52 {
    /**
     * 位运算
     * 时间复杂度:O(1) - 100.00%
     * 空间复杂度:O(1) - 80.04%
     * 优点:代码简洁易懂
     * 缺点:作为工程代码，全局变量需要在构造方法中设置默认值，否则容易exception。
     *
     * @param n
     * @return
     */
    public int totalNQueens1(int n) {
        count = 0;
        limit = (1 << n) - 1;
        dfs(0, 0, 0);
        return count;
    }

    int limit;
    int count;

    private void dfs(int row, int left, int right) {
        if (row == limit) {
            count++;
            return;
        }
        int position = limit & (~(row | left | right));
        while (position != 0) {
            int temp = position & (-position);
            position -= temp; // position = position - temp;
            dfs(row | temp, (left | temp) << 1, (right | temp) >> 1);
        }
    }
}
