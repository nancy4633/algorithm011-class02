package com.xunlianying3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 第二遍
// n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
public class SolveNQueens51 {
    /**
     * DFS + 回溯 + 剪枝
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.20%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens1(int n) {
        results = new ArrayList<>();
        chessBoard = new char[n][n];
        for (char[] ch : chessBoard) {
            Arrays.fill(ch, '.');
        }
        cols = new boolean[n];
        mainDiag = new boolean[2 * n + 1];
        deputyDiag = new boolean[2 * n - 1];
        dfs1(n, 0);
        return results;
    }

    List<List<String>> results;
    char[][] chessBoard;
    boolean[] cols, mainDiag, deputyDiag;

    public void dfs1(int n, int row) {
        if (row == n) {
            List<String> result = new ArrayList<>();
            for (char[] ch : chessBoard) {
                result.add(new String(ch));
            }
            results.add(result);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (cols[i] || mainDiag[row + (n - i)] || deputyDiag[row + i]) continue;
            chessBoard[row][i] = 'Q';
            cols[i] = true;
            mainDiag[n + row - i] = true;
            deputyDiag[row + i] = true;
            dfs1(n, row + 1);
            chessBoard[row][i] = '.';
            cols[i] = false;
            mainDiag[n + row - i] = false;
            deputyDiag[row + i] = false;
        }
    }
}
