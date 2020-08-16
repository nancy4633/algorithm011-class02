package com.xunlianying8.zuoye1;

// n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后彼此之间不能相互攻击。
// 给定一个整数n，返回n皇后不同的解决方案的数量。
// 提示：
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走1或N-1步，可进可退。
// 思路：
//
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
        total = 0;
        limit = (1 << n) - 1;
        solve(0, 0, 0);
        return total;
    }

    private int limit, total;

    private void solve(int row, int left, int right) {
        if (row == limit) {
            total++;
            return;
        }
        int pos = limit & (~(row | left | right));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p; // pos &= pos - 1;
            solve(row | p, (left | p) << 1, (right | p) >> 1);
        }
    }

    /**
     * 位运算
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:同样是使用位运算，但是变量都作为行参传递，并没有作为全局变量。对于外部函数调用会比较友好
     * 缺点:
     *
     * @param n
     * @return
     */
    public int totalNQueens2(int n) {
        return backtrack(0, 0, 0, 0, 0, n);
    }

    public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
        int columns = (1 << n) - 1;
        if (row == n) {
            count++;// 如果已经放置了 n 个皇后  // 累加可行解
        } else {
            int free_columns = columns & ~(hills | next_row | dales);
            while (free_columns != 0) {
                int curr_column = -free_columns & free_columns;
                free_columns ^= curr_column;
                count = backtrack(row + 1, (hills | curr_column) << 1, next_row | curr_column, (dales | curr_column) >> 1, count, n);
            }
        }
        return count;
    }
}
